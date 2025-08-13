package tr.unvercanunlu.logger.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogConfig {

  public static final boolean DEBUG_ENABLED = false;
  public static final boolean ERROR_ENABLED = true;
  public static final String LOG_FORMAT = "%s - %s: %s - %s";
  public static final String VALUE_PLACEHOLDER = "\\{}";

}
