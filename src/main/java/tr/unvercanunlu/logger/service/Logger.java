package tr.unvercanunlu.logger.service;

import java.util.function.Supplier;
import tr.unvercanunlu.logger.constant.LogLevel;

public interface Logger {

  void log(LogLevel level, String message, Object... parameters);

  void log(LogLevel level, Supplier<String> messageSupplier);

}
