package challenging.application.global.exception.challenge;
import static challenging.application.global.exception.ExceptionMessage.*;

public class AlreadyReservedException extends RuntimeException {
  public AlreadyReservedException(String message) {
    super(message);
  }

  public AlreadyReservedException() {
    super(ALREADY_RESERVED_EXCEPTION);
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }
}