package tr.unvercanunlu.console_logger.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogLevel {

  ERROR(0),
  WARN(1),
  INFO(2),
  DEBUG(3);

  public static final LogLevel DEFAULT_LEVEL = INFO;

  private final int threshold;

}
