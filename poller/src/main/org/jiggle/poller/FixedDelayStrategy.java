package org.jiggle.poller;

import java.time.Duration;

/**
 * An implementation of {@code DelayStrategy} that introduces a fixed time delay between
 * consecutive retries.
 */
public class FixedDelayStrategy implements DelayStrategy {

  /**
   * Time delay between polls.
   */
  private Duration delay = null;

  /**
   * Constructs a new FixedDelayStrategy with the given time delay.
   *
   * @param defaultDelayInSeconds
   */
  public FixedDelayStrategy(final Duration delay) {
    this.delay = delay;
  }

  /**
   * Introduces a time delay between polls by putting the {@code Thread} to sleep for a time
   * period equal to the supplied {@code delay}. @see #FixedDelayStrategy(Duration delay).
   *
   * @param ctx provides contextual information to the {@code Poller} so it can determine the
   *        time delay between retries. @see PollerContext
   * @throws InterruptedException
   */
  @Override
  public void introduceDelayBeforeNextRetryAttempt(final PollerContext ctx)
      throws InterruptedException {
    Thread.sleep(this.delay.toMillis());
    ctx.incrementElapsedPollduration(this.delay);
  }
}
