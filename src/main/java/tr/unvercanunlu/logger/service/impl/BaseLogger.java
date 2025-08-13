package tr.unvercanunlu.logger.service.impl;

import static tr.unvercanunlu.logger.config.LogConfig.DEBUG_ENABLED;
import static tr.unvercanunlu.logger.config.LogConfig.ERROR_ENABLED;
import static tr.unvercanunlu.logger.config.LogConfig.VALUE_PLACEHOLDER;

import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import tr.unvercanunlu.logger.service.Logger;

@RequiredArgsConstructor
public abstract class BaseLogger implements Logger {

  protected final Class<?> source;

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

  protected abstract void doInfo(Supplier<String> messageSupplier);

  protected abstract void doDebug(Supplier<String> messageSupplier);

  protected abstract void doError(Supplier<String> messageSupplier, Throwable exception);

  @Override
  public boolean isDebugEnabled() {
    return DEBUG_ENABLED;
  }

  @Override
  public boolean isErrorEnabled() {
    return ERROR_ENABLED;
  }

  @Override
  public void info(String message, Object... parameters) {
    doInfo(() -> fillMessageWithParameters(message, parameters));
  }

  @Override
  public void debug(String message, Object... parameters) {
    if (isDebugEnabled()) {
      doDebug(() -> fillMessageWithParameters(message, parameters));
    }
  }

  @Override
  public void error(String message, Object... parameters) {
    if (isErrorEnabled()) {
      doError(() -> fillMessageWithParameters(message, parameters), null);
    }
  }

  @Override
  public void error(String message, Throwable exception, Object... parameters) {
    if (isErrorEnabled()) {
      doError(() -> fillMessageWithParameters(message, parameters), exception);
    }
  }

  @Override
  public void info(Supplier<String> messageSupplier) {
    doInfo(messageSupplier);
  }

  @Override
  public void debug(Supplier<String> messageSupplier) {
    if (isDebugEnabled()) {
      doDebug(messageSupplier);
    }
  }

  @Override
  public void error(Supplier<String> messageSupplier) {
    if (isErrorEnabled()) {
      doError(messageSupplier, null);
    }
  }

  @Override
  public void error(Supplier<String> messageSupplier, Throwable exception) {
    if (isErrorEnabled()) {
      doError(messageSupplier, exception);
    }
  }

}
