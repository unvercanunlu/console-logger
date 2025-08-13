package tr.unvercanunlu.logger.service.impl;

import static tr.unvercanunlu.logger.config.LogConfig.VALUE_PLACEHOLDER;
import static tr.unvercanunlu.logger.config.LogConfig.isEnabled;

import java.util.function.Supplier;
import tr.unvercanunlu.logger.constant.LogLevel;
import tr.unvercanunlu.logger.service.Logger;
import tr.unvercanunlu.logger.util.ValidationUtil;

public abstract class BaseLogger implements Logger {

  protected final Class<?> source;

  protected BaseLogger(Class<?> source) {
    ValidationUtil.checkSource(source);
    this.source = source;
  }

  protected static String fillMessageWithParameters(String message, Object... parameters) {
    if (message == null) {
      return "";
    }

    if ((parameters == null) || (parameters.length == 0)) {
      return message;
    }

    String formattedMessage = message.replaceAll(VALUE_PLACEHOLDER, "%s");

    return String.format(formattedMessage, parameters);
  }

  protected abstract void doLog(LogLevel level, Supplier<String> messageSupplier);

  @Override
  public void log(LogLevel level, String message, Object... parameters) {
    ValidationUtil.checkLevel(level);
    ValidationUtil.checkMessage(message);
    if (isEnabled(level)) {
      doLog(level, () -> fillMessageWithParameters(message, parameters));
    }
  }

  @Override
  public void log(LogLevel level, Supplier<String> messageSupplier) {
    ValidationUtil.checkLevel(level);
    ValidationUtil.checkMessageSupplier(messageSupplier);
    if (isEnabled(level)) {
      doLog(level, messageSupplier);
    }
  }

}
