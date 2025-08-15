package tr.unvercanunlu.console_logger.service.impl;

import static tr.unvercanunlu.console_logger.config.LogConfig.isEnabled;
import static tr.unvercanunlu.console_logger.util.ValidationUtil.checkLevel;
import static tr.unvercanunlu.console_logger.util.ValidationUtil.checkMessage;
import static tr.unvercanunlu.console_logger.util.ValidationUtil.checkMessageSupplier;
import static tr.unvercanunlu.console_logger.util.ValidationUtil.checkSource;

import java.util.function.Supplier;
import tr.unvercanunlu.console_logger.constant.LogLevel;
import tr.unvercanunlu.console_logger.service.Logger;
import tr.unvercanunlu.console_logger.util.TextUtil;

public abstract class BaseLogger implements Logger {

  protected final Class<?> source;

  protected BaseLogger(Class<?> source) {
    checkSource(source);
    this.source = source;
  }

  protected static String fillMessageWithParameters(String message, Object... parameters) {
    if (message == null) {
      return "";
    }

    if ((parameters == null) || (parameters.length == 0)) {
      return message;
    }

    StringBuilder builder = new StringBuilder();

    boolean opened = false;

    int i = 0;
    int j = 0;

    while (i < message.length()) {
      char current = message.charAt(i);

      if (!opened) {
        if (current != '{' && current != '}') {
          builder.append(current);
          i++;
        } else if (current == '{') {
          opened = true;
          i++;
        } else {
          builder.append('}');
          i++;
        }
      } else {
        if (current == '}') {
          if (j < parameters.length) {
            String value = TextUtil.stringify(parameters[j++]);
            builder.append(value);
          } else {
            builder.append("{}");
          }
          opened = false;
          i++;
        } else {
          builder.append('{');
          opened = false;
        }
      }
    }

    if (opened) {
      builder.append('{');
    }

    return builder.toString();
  }

  protected abstract void doLog(LogLevel level, Supplier<String> messageSupplier);

  @Override
  public void log(LogLevel level, String message, Object... parameters) {
    checkLevel(level);
    checkMessage(message);
    if (isEnabled(level)) {
      doLog(level, () -> fillMessageWithParameters(message, parameters));
    }
  }

  @Override
  public void log(LogLevel level, Supplier<String> messageSupplier) {
    checkLevel(level);
    checkMessageSupplier(messageSupplier);
    if (isEnabled(level)) {
      doLog(level, messageSupplier);
    }
  }

}
