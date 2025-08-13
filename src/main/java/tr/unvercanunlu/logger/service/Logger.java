package tr.unvercanunlu.logger.service;

import java.util.function.Supplier;

public interface Logger {

  void info(String message, Object... parameters);

  void debug(String message, Object... parameters);

  void error(String message, Object... parameters);

  void error(String message, Throwable exception, Object... parameters);

  void info(Supplier<String> messageSupplier);

  void debug(Supplier<String> messageSupplier);

  void error(Supplier<String> messageSupplier);

  void error(Supplier<String> messageSupplier, Throwable exception);

  boolean isDebugEnabled();

  boolean isErrorEnabled();

}
