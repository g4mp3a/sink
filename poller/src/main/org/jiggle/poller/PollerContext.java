package org.jiggle.poller;

import java.time.Duration;

/**
 * Contextual information about the Poller.
 */
public class PollerContext {

  /**
   * Number of retries made so far.
   */
  int numOfRetriesAttempted;

  /**
   * Time elapsed since polling began.
   */
  Duration pollDuration;


  /**
   * Constructs a PollerContext with the provided inputs.
   *
   * @param numOfRetriesAttempted
   * @param pollDuration
   */
  public PollerContext(final int numOfRetriesAttempted, final Duration pollDuration) {
    this.numOfRetriesAttempted = numOfRetriesAttempted;
    this.pollDuration = pollDuration;
  }

  /**
   * Returns the time elapsed since polling began.
   *
   * @return pollDuration
   */
  public Duration getElapsedPollDuration() {
    return this.pollDuration;
  }

  /**
   * Returns the number of retries made so far.
   *
   * @return numOfRetriesAttempted
   */
  public int getNumOfRetriesAttempted() {
    return this.numOfRetriesAttempted;
  }

  /**
   * Adds by the supplied {@code loopDuration} to the {@code pollDuration}
   *
   * @param loopDuration
   */
  public void incrementElapsedPollduration(final Duration loopDuration) {
    this.pollDuration = this.pollDuration.plus(loopDuration);
  }

  /**
   * Increments the {@code numOfRetriesAttempted} by 1.
   */
  public void incrementNumOfRetriesAttempted() {
    this.numOfRetriesAttempted++;
  }
}
