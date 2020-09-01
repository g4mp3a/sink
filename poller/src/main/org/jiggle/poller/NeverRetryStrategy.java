package org.jiggle.poller;

/**
 * An implementation of the {@link RetryStrategy} that provides for 0 retry attempts to be made
 * by the {@link Poller}.
 */
public class NeverRetryStrategy implements RetryStrategy {

  /**
   * Always returns false.
   */
  @Override
  public boolean shouldRetry(final PollerContext ctx) throws PollRetryException {
    return false;
  }
}
