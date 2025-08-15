package tr.unvercanunlu.console_logger.service.impl;

import static tr.unvercanunlu.console_logger.config.LogConfig.LOG_FORMAT;
import static tr.unvercanunlu.console_logger.config.LogConfig.TIMESTAMP_FORMAT;
import static tr.unvercanunlu.console_logger.constant.LogLevel.ERROR;

import java.time.ZonedDateTime;
import java.util.function.Supplier;
import tr.unvercanunlu.console_logger.constant.LogLevel;

public class ConsoleLogger extends BaseLogger {

  public ConsoleLogger(Class<?> source) {
    super(source);
  }

  private String formatLogMessage(LogLevel level, Supplier<String> messageSupplier) {
    String timestamp = ZonedDateTime.now().format(TIMESTAMP_FORMAT);
    String levelName = level.name();
    String threadName = Thread.currentThread().getName();
    String sourceName = source.getSimpleName();
    String message = messageSupplier.get();
    return LOG_FORMAT.formatted(timestamp, levelName, threadName, sourceName, message);
  }

  @Override
  protected void doLog(LogLevel level, Supplier<String> messageSupplier) {
    String formattedMessage = formatLogMessage(level, messageSupplier);
    if (level.equals(ERROR)) {
      System.err.println(formattedMessage);
    } else {
      System.out.println(formattedMessage);
    }
  }

}
