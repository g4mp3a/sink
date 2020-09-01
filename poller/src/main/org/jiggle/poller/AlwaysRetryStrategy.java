package org.jiggle.poller;

/**
 * An implementation of the {@link RetryStrategy} that provides for any number of retry attempts
 * to be made by the {@link Poller}.
 */
public class AlwaysRetryStrategy implements RetryStrategy {

  /**
   * Always returns false.
   */
  @Override
  public boolean shouldRetry(final PollerContext ctx) throws PollRetryException {
    return true;
  }

}
