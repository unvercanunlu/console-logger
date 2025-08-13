package tr.unvercanunlu.logger.constant;

public enum LogLevel {

  ERROR(0),
  WARN(1),
  INFO(2),
  DEBUG(3);

  public static final LogLevel DEFAULT_LEVEL = INFO;

  private final int threshold;

  LogLevel(int threshold) {
    this.threshold = threshold;
  }

  public int getThreshold() {
    return threshold;
  }

}
