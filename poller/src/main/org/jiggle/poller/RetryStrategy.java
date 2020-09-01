package org.jiggle.poller;

/**
 * Hook to provide custom condition on whether polling should be retried.
 */
public interface RetryStrategy {

  /**
   * Returns true if polling should be retried; otherwise, throws a {@code PollRetryException}.
   *
   * @param ctx provides contextual information to the {@code Poller} to help with making the
   *        decision whether to retry or not. @see PollerContext
   *
   * @return {@code true} if another retry attempt should be made.
   */
  boolean shouldRetry(PollerContext ctx) throws PollRetryException;

}
