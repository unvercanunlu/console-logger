**Logger Utility**  
A lightweight, extensible Java logging library with multiple log levels, lazy/eager logging, safe message parameterization, and circular reference protection.

**Features**

* Log levels: `ERROR`, `WARN`, `INFO`, `DEBUG`
* Eager & lazy logging (String or `Supplier<String>`)
* `{}` placeholder parameter substitution
* Circular reference-safe object stringification (`TextUtil`)
* Configurable log level, format, and timestamp (`LogConfig`)
* Console output (`ConsoleLogger`) with `System.out`/`System.err` separation

**Configuration (`LogConfig.java`)**

```java
SELECTED_LEVEL = LogLevel.DEBUG;
LOG_FORMAT = "%s - [%s] (%s): %s - %s";
TIMESTAMP_FORMAT = DateTimeFormatter.ISO_DATE_TIME;
```

**API**

```java
log(LogLevel level, String message, Object... parameters);
log(LogLevel level, Supplier<String> messageSupplier);
info(...), warn(...), debug(...), error(...);
error(message, throwable, ...);
```

**Example**

```java
Logger logger = new ConsoleLogger(Main.class);
logger.info("Started at {}", System.currentTimeMillis());
logger.debug(() -> "Debug mode active");
logger.error("Exception occurred", new RuntimeException("Test"));
```
