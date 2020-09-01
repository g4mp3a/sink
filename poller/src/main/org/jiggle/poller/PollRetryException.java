package org.jiggle.poller;

/**
 * This exception is thrown when the {@link Poller} fails to retry the {@link Poller#source}.
 *
 */
@SuppressWarnings("serial")
public class PollRetryException extends RuntimeException {

  public PollRetryException() {
    super();
  }

  public PollRetryException(final String message) {
    super(message);
  }

  public PollRetryException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PollRetryException(final String message, final Throwable cause, final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public PollRetryException(final Throwable cause) {
    super(cause);
  }
}
