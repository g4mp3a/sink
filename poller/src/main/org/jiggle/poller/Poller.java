package org.jiggle.poller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * {@code Poller} provides for retrying API requests using declared retry and delay strategies.
 * The API request is provided using a {@link Supplier} closure called {@code source} and a
 * {@link Predicate} closure called {@code until} supplies the condition to determine when the
 * desired outcome has been achieved. It uses {@link MaxDurationRetryStrategy} as the default
 * retry strategy and {@link FixedDelayStrategy} as the default delay strategy.
 *
 * <p>
 * {@link MaxAttemptsRetryStrategy}, {@link NeverRetryStrategy}, and {@link AlwaysRetryStrategy}
 * are available.
 * </p>
 *
 * <p>
 * One can define custom retry and delay strategies by implementing {@link RetryStrategy} and
 * {@link DelayStrategy} respectively.
 * </p>
 *
 * Usage:
 *
 * With default retry and delay strategies.
 *
 * <pre>
 * {@code
 * String uuidThatStartsWithOneTwoThree =
 *     new Poller<String>()
 *         .source(() {@literal ->} UUID.randomUUID().toString())
 *         .until(s {@literal ->} s.startsWith({@literal "}123{@literal "}))
 *         .execute();
 * }
 * </pre>
 *
 * With default delay strategy and custom retry strategy.
 *
 * <pre>
 * {@code
 * String uuidThatStartsWithOneTwoThree =
 *     new Poller<String>()
 *         .withRetryStrategy(new CustomeRetryStrategy(3))
 *         .source(() {@literal ->} UUID.randomUUID().toString())
 *         .until(s {@literal ->} s.startsWith({@literal "}123{@literal "}))
 *         .execute();
 * }
 * </pre>
 *
 * With custom delay and retry strategies.
 *
 * <pre>
 * {@code
 * String uuidThatStartsWithOneTwoThree =
 *     new Poller<String>()
 *         .withRetryStrategy(new CustomeRetryStrategy())
 *         .withDelayStrategy(new CustomDelayStrategy())
 *         .source(() {@literal ->} UUID.randomUUID().toString())
 *         .until(s {@literal ->} s.startsWith({@literal "}123{@literal "}))
 *         .execute();
 * }
 * </pre>
 *
 */

public class Poller<T> {

  private enum Flag {
    final INIT_SOURCE,
    INIT_COMPLETION_CONDITION;

    static final EnumSet<Flag> ALL_OPTS = EnumSet.allOf(Flag.class);
  }
  private final EnumSet<Flag> flags = EnumSet.noneOf(Flag.class);

  private Supplier<T> source = null;
  private Predicate<T> completionCondition = null;
  private RetryStrategy retryStrategy = new MaxDurationRetryStrategy(Duration.ofMinutes(30));
  private DelayStrategy delayStrategy = new FixedDelayStrategy(Duration.ofSeconds(30));

  public Poller() {}

  public Poller<T> withRetryStrategy(final RetryStrategy retryStrategy) {
    this.retryStrategy = retryStrategy;
    return this;
  }

  public Poller<T> withDelayStrategy(final DelayStrategy delayStrategy) {
    this.delayStrategy = delayStrategy;
    return this;
  }

  public Poller<T> source(final Supplier<T> supplier) {
    this.source = supplier;
    this.flags.add(this.Flag.INIT_SOURCE);
    return this;
  }

  public Poller<T> until(final Predicate<T> condition) {
    this.completionCondition = condition;
    this.flags.add(this.Flag.INIT_COMPLETION_CONDITION);
    return this;
  }

  public T execute() {
    this.validatePollerProperlyInitialized();

    T result = null;
    boolean pollSucceeded = false;
    final PollerContext ctx = new PollerContext(0, Duration.ZERO);

    do {
      result = this.source.get();
      pollSucceeded = this.completionCondition.test(result);

      if (pollSucceeded)
        return result;

      try {
        this.delayStrategy.introduceDelayBeforeNextRetryAttempt(ctx);
      } catch (final InterruptedException e) {
        throw new PollRetryException("Exception encountered while waiting to retry.", e);
      }

    } while (this.retryStrategy.shouldRetry(ctx));

    return result;
  }

  private void validatePollerProperlyInitialized() {
    if (!this.Flag.ALL_OPTS.equals(this.flags))
      throw new IllegalStateException(this.formatRequiredAttributesMessage());
  }

  private String formatRequiredAttributesMessage() {
    final List<String> attributes = new ArrayList<>();

    if (!this.flags.contains(this.Flag.INIT_SOURCE)) {
      attributes.add("source");
    }
    if (!this.flags.contains(this.Flag.INIT_COMPLETION_CONDITION)) {
      attributes.add("completionCondition");
    }

    return "Required attributes of the Poller not set "
    + attributes;
  }
}
