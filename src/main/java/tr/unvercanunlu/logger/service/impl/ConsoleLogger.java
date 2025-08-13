package tr.unvercanunlu.logger.service.impl;

import static tr.unvercanunlu.logger.config.LogConfig.LOG_FORMAT;
import static tr.unvercanunlu.logger.constant.LogLevel.DEBUG;
import static tr.unvercanunlu.logger.constant.LogLevel.ERROR;
import static tr.unvercanunlu.logger.constant.LogLevel.INFO;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;
import tr.unvercanunlu.logger.constant.LogLevel;

public class ConsoleLogger extends BaseLogger {

  public ConsoleLogger(Class<?> source) {
    super(source);
  }

  private String formatLogMessage(LogLevel level, Supplier<String> messageSupplier) {
    return LOG_FORMAT.formatted(
        LocalDateTime.now(),
        Optional.ofNullable(level).orElse(INFO).name(),
        Optional.ofNullable(source).map(Class::getSimpleName).orElse(""),
        Optional.ofNullable(messageSupplier).map(Supplier::get).orElse("")
    );
  }

  @Override
  protected void doInfo(Supplier<String> messageSupplier) {
    String formattedMessage = formatLogMessage(INFO, messageSupplier);
    System.out.println(formattedMessage);
  }

  @Override
  protected void doDebug(Supplier<String> messageSupplier) {
    String formattedMessage = formatLogMessage(DEBUG, messageSupplier);
    System.out.println(formattedMessage);
  }

  @Override
  protected void doError(Supplier<String> messageSupplier, Throwable exception) {
    String formattedMessage = formatLogMessage(ERROR, messageSupplier);
    System.err.println(formattedMessage);

    Optional.ofNullable(exception)
        .ifPresent(Throwable::printStackTrace);
  }

}
