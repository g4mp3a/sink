package org.jiggle.poller;

import java.time.Duration;

/**
 * An implementation of {@link RetryStrategy} that allows polling for a maximum duration before
 * the Poller gives up trying to get a response.
 */
public class MaxDurationRetryStrategy implements RetryStrategy {

  /**
   * Represents default number of maximum attempts allowed for polling.
   */
  private final Duration maxPollDuration;

  /**
   * Constructs a new MaxAttemptsRetryStrategy with the given default number of attempts.
   *
   * @param defaultMaxAttempts
   */
  public MaxDurationRetryStrategy(final Duration maxPollDuration) {
    this.maxPollDuration = maxPollDuration;
  }

  /**
   * Returns {@code true} if the Poller should attempt another retry based on the time elapsed
   * since polling started being less than the supplied {@code maxPollDuration}. @see
   * #MaxDurationRetryStrategy(Duration maxPollDuration).
   *
   * @param ctx Provides the polling context required to make the retry decision. @see
   *        PollerContext
   * @return {@code true} or {@code false} depending on whether the time elapsed since polling
   *         started is less or greater than the supplied {@code maxPollDuration}
   */
  @Override
  public boolean shouldRetry(final PollerContext ctx) throws PollRetryException {
    if (this.maxPollDuration.minus(ctx.getElapsedPollDuration()).isNegative())
      throw new PollRetryException("Maximum poll duration exceeded. Poll timed out.");

    ctx.incrementNumOfRetriesAttempted();
    return true;
  }
}
