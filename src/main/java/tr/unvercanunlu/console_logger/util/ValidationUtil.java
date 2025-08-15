package tr.unvercanunlu.console_logger.util;

import java.util.function.Supplier;
import tr.unvercanunlu.console_logger.constant.LogLevel;

public class ValidationUtil {

  // hidden
  private ValidationUtil() {
  }

  public static void checkLevel(LogLevel level) {
    if (level == null) {
      throw new IllegalArgumentException("Level missing!");
    }
  }

  public static void checkMessage(String message) {
    if ((message == null) || message.isBlank()) {
      throw new IllegalArgumentException("Message missing!");
    }
  }

  public static void checkMessageSupplier(Supplier<String> messageSupplier) {
    if (messageSupplier == null) {
      throw new IllegalArgumentException("Message supplier missing!");
    }
  }

  public static void checkSource(Class<?> source) {
    if (source == null) {
      throw new IllegalArgumentException("Source missing!");
    }
  }

}
