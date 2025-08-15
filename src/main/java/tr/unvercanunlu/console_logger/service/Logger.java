package tr.unvercanunlu.console_logger.service;

import static tr.unvercanunlu.console_logger.config.LogConfig.isEnabled;
import static tr.unvercanunlu.console_logger.constant.LogLevel.ERROR;

import java.util.function.Supplier;
import tr.unvercanunlu.console_logger.constant.LogLevel;

public interface Logger {

  // eager
  void log(LogLevel level, String message, Object... parameters);

  // lazy
  void log(LogLevel level, Supplier<String> messageSupplier);

  default void printStackTrace(Throwable throwable) {
    if (isEnabled(ERROR) && (throwable != null)) {
      throwable.printStackTrace();
    }
  }

  default void info(String message, Object... parameters) {
    log(LogLevel.INFO, message, parameters);
  }

  default void warn(String message, Object... parameters) {
    log(LogLevel.WARN, message, parameters);
  }

  default void debug(String message, Object... parameters) {
    log(LogLevel.DEBUG, message, parameters);
  }

  default void error(String message, Object... parameters) {
    log(LogLevel.ERROR, message, parameters);
  }

  default void error(String message, Throwable throwable, Object... parameters) {
    log(ERROR, message, parameters);
    printStackTrace(throwable);
  }

  default void info(Supplier<String> messageSupplier) {
    log(LogLevel.INFO, messageSupplier);
  }

  default void warn(Supplier<String> messageSupplier) {
    log(LogLevel.WARN, messageSupplier);
  }

  default void debug(Supplier<String> messageSupplier) {
    log(LogLevel.DEBUG, messageSupplier);
  }

  default void error(Supplier<String> messageSupplier) {
    log(LogLevel.ERROR, messageSupplier);
  }

  default void error(Supplier<String> messageSupplier, Throwable throwable) {
    log(ERROR, messageSupplier);
    printStackTrace(throwable);
  }

}
