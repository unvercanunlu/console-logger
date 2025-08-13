package tr.unvercanunlu.logger.config;

import static tr.unvercanunlu.logger.constant.LogLevel.DEBUG;
import static tr.unvercanunlu.logger.constant.LogLevel.DEFAULT_LEVEL;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import tr.unvercanunlu.logger.constant.LogLevel;
import tr.unvercanunlu.logger.util.ValidationUtil;

public class LogConfig {

  // selected
  public static final LogLevel SELECTED_LEVEL = DEBUG;

  // timestamp - [LEVEL] (thread) : Source - Message
  public static final String LOG_FORMAT = "%s - [%s] (%s): %s - %s";

  public static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ISO_DATE_TIME;
  public static final String VALUE_PLACEHOLDER = "{}";

  // hidden
  private LogConfig() {
  }

  public static boolean isEnabled(LogLevel level) {
    ValidationUtil.checkLevel(level);
    LogLevel current = Optional.ofNullable(SELECTED_LEVEL).orElse(DEFAULT_LEVEL);
    return current.getThreshold() >= level.getThreshold();
  }

}
