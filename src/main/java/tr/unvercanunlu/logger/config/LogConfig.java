package tr.unvercanunlu.logger.config;

import static tr.unvercanunlu.logger.constant.LogLevel.DEBUG;
import static tr.unvercanunlu.logger.constant.LogLevel.DEFAULT_LEVEL;
import static tr.unvercanunlu.logger.util.ValidationUtil.checkLevel;

import java.time.format.DateTimeFormatter;
import tr.unvercanunlu.logger.constant.LogLevel;

public class LogConfig {

  // selected
  public static final LogLevel SELECTED_LEVEL = DEBUG;

  // log format: timestamp - [LEVEL] (thread) : Source - Message
  public static final String LOG_FORMAT = "%s - [%s] (%s): %s - %s";

  // timestamp format
  public static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

  // hidden
  private LogConfig() {
  }

  public static boolean isEnabled(LogLevel level) {
    checkLevel(level);
    LogLevel current = SELECTED_LEVEL;
    if (current == null) {
      current = DEFAULT_LEVEL;
    }
    return current.getThreshold() >= level.getThreshold();
  }

}
