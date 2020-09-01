package org.jiggle.poller;

import org.jiggle.poller.FixedDelayStrategy;
import org.jiggle.poller.MaxAttemptsRetryStrategy;
import org.jiggle.poller.MaxDurationRetryStrategy;
import org.jiggle.poller.PollRetryException;
import org.jiggle.poller.Poller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Duration;
import java.util.Random;

public class PollerTest {

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  @Test
  public void simplePollerTest() {
    final int result =
        new Poller<Integer>()
        .withDelayStrategy(new FixedDelayStrategy(Duration.ofMillis(10)))
        .withRetryStrategy(new MaxDurationRetryStrategy(Duration.ofMillis(100)))
        .source(() -> new Random().nextInt(2))
        .until(i -> i == 0)
        .execute();
        assert (result == 0);
  }

  @Test
  public void testDurationExceededException() {
    this.expectedException.expect(PollRetryException.class);
    this.expectedException.expectMessage("Maximum poll duration exceeded. Poll timed out.");

    new Poller<Integer>()
    .withDelayStrategy(new FixedDelayStrategy(Duration.ofMillis(10)))
    .withRetryStrategy(new MaxDurationRetryStrategy(Duration.ofMillis(100)))
    .source(() -> new Random().nextInt(2))
    .until(i -> i == 2)
    .execute();
  }

  @Test
  public void testIllegalStateExceptionForMissingAllAttributes() {
    this.expectedException.expect(IllegalStateException.class);
    this.expectedException
    .expectMessage("Required attributes of the Poller not set [source, completionCondition]");
    new Poller<Integer>()
    .execute();
  }

  @Test
  public void testIllegalStateExceptionForMissingCompletionCondition() {
    this.expectedException.expect(IllegalStateException.class);
    this.expectedException
    .expectMessage("Required attributes of the Poller not set [completionCondition]");
    new Poller<Integer>()
    .source(() -> new Random().nextInt(2))
    .execute();
  }


  @Test
  public void testIllegalStateExceptionForMissingSource() {
    this.expectedException.expect(IllegalStateException.class);
    this.expectedException.expectMessage("Required attributes of the Poller not set [source]");
    new Poller<Integer>()
    .withDelayStrategy(new FixedDelayStrategy(Duration.ofMillis(10)))
    .withRetryStrategy(new MaxDurationRetryStrategy(Duration.ofMillis(100)))
    .until(i -> i == 1)
    .execute();

  }

  @Test
  public void testMaximumRetryAttemptsExceededException() {
    this.expectedException.expect(PollRetryException.class);
    this.expectedException.expectMessage("Maximum retry attempts exceeded.");

    new Poller<Integer>()
    .withDelayStrategy(new FixedDelayStrategy(Duration.ofMillis(10)))
    .withRetryStrategy(new MaxAttemptsRetryStrategy(3))
    .source(() -> new Random().nextInt(2))
    .until(i -> i == 2)
    .execute();
  }



  @Test(timeout = 9)
  public void testPollerPollsBeforeItWaits() {
    final int result =
        new Poller<Integer>()
        .withDelayStrategy(new FixedDelayStrategy(Duration.ofMillis(10)))
        .withRetryStrategy(new MaxDurationRetryStrategy(Duration.ofMillis(100)))
        .source(() -> 0)
        .until(i -> i == 0)
        .execute();
        assert (result == 0);
  }
}
