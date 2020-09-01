package org.jiggle.poller;

/**
 * An implementation of {@link RetryStrategy} that allows for a maximum number of retry attempts
 * to be made before the Poller gives up trying to get a response.
 */
public class MaxAttemptsRetryStrategy implements RetryStrategy {

  /**
   * Represents the number of maximum retry attempts allowed.
   */
  private final int maxRetryAttempts;

  /**
   * Constructs a new MaxAttemptsRetryStrategy with the given number of maximum attempts.
   *
   * @param maxAttempts
   */
  public MaxAttemptsRetryStrategy(final int maxAttempts) {
    this.maxRetryAttempts = maxAttempts;
  }

  /**
   * Returns {@code true} if polling should be retried based on the number of retries attempted
   * so far being less than the supplied {@code maxAttempts}.
   *
   * @param ctx Provides the polling context required to make the retry decision. @see
   *        PollerContext
   * @return {@code true} or {@code false} depending on whether the number of retry attempts so
   *         far being less than {@code maxAttempts}.
   */
  @Override
  public boolean shouldRetry(final PollerContext ctx) throws PollRetryException {
    if (!(ctx.getNumOfRetriesAttempted() < this.maxRetryAttempts))
      throw new PollRetryException("Maximum retry attempts exceeded.");

    ctx.incrementNumOfRetriesAttempted();
    return true;
  }
}
