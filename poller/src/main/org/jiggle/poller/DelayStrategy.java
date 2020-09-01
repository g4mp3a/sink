package org.jiggle.poller;

/**
 * Hook to provide custom delay strategy to control the time delay between consecutive retires.
 */
public interface DelayStrategy {

  /**
   * Introduces a certain amount of time delay between consecutive retries based on the strategy
   * provided by the implementing class.
   *
   * @param ctx provides contextual information to the {@code Poller} so it can introduce a
   *        certain amount of time delay between consecutive retries. @see PollerContext
   * @throws InterruptedException
   */
  void introduceDelayBeforeNextRetryAttempt(PollerContext ctx) throws InterruptedException;
}
