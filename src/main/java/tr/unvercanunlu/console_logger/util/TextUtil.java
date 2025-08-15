package tr.unvercanunlu.console_logger.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextUtil {

  private TextUtil() {
  }

  public static String stringify(Object object) {
    Set<Object> seen = new HashSet<>();
    return stringifyHelper(object, seen);
  }

  private static String stringifyHelper(Object object, Set<Object> seen) {
    if (object == null) {
      return "null";
    }

    try {
      // cyclic potential
      if (!isTypeNonCyclic(object) && isTypePotentialCyclic(object)) {
        // cyclic check
        if (seen.contains(object)) {
          return "(circular)";
        }

        seen.add(object);
      }

      return switch (object) {
        // object array
        case Object[] objects -> stringifyObjectArray.apply(objects, seen);

        // primitive array
        case int[] intArray -> Arrays.toString(intArray);
        case long[] longArray -> Arrays.toString(longArray);
        case double[] doubleArray -> Arrays.toString(doubleArray);
        case float[] floatArray -> Arrays.toString(floatArray);
        case boolean[] booleanArray -> Arrays.toString(booleanArray);
        case char[] charArray -> Arrays.toString(charArray);
        case byte[] byteArray -> Arrays.toString(byteArray);

        // optional
        case OptionalInt optionalInt -> optionalInt.isPresent()
            ? String.valueOf(optionalInt.getAsInt())
            : "null";
        case OptionalLong optionalLong -> optionalLong.isPresent()
            ? String.valueOf(optionalLong.getAsLong())
            : "null";
        case OptionalDouble optionalDouble -> optionalDouble.isPresent()
            ? String.valueOf(optionalDouble.getAsDouble())
            : "null";
        case Optional<?> optional -> stringifyOptional.apply(optional, seen);

        // collection
        case Collection<?> collection -> stringifyCollection.apply(collection, seen);

        // map
        case Map<?, ?> map -> stringifyMap.apply(map, seen);

        // throwable
        case Throwable throwable -> stringifyThrowable.apply(throwable);

        // default
        default -> String.valueOf(object);
      };
    } catch (Throwable e) {
      return safeFallback(object);
    }
  }

  private static boolean isTypeNonCyclic(Object object) {
    return (object instanceof String)
        || (object instanceof Number)
        || (object instanceof Boolean)
        || (object instanceof Character)
        || (object instanceof Enum<?>);
  }

  private static boolean isTypePotentialCyclic(Object object) {
    return object.getClass().isArray()
        || (object instanceof Collection<?>)
        || (object instanceof Map<?, ?>)
        || (object instanceof Optional<?>)
        || (object instanceof OptionalInt)
        || (object instanceof OptionalLong)
        || (object instanceof OptionalDouble);
  }

  private static String safeFallback(Object object) {
    try {
      String safe = "null";
      if (object != null) {
        safe = object.getClass().getName();
      }
      return safe;
    } catch (Throwable ignore) {
      return "<unprintable>";
    }
  }

  private static final BiFunction<Map<?, ?>, Set<Object>, String> stringifyMap = (map, seen) ->
      "{" + map.entrySet()
          .stream()
          .map(entry -> stringifyHelper(entry.getKey(), seen) + "=" + stringifyHelper(entry.getValue(), seen))
          .collect(Collectors.joining(", ")) + "}";

  private static final BiFunction<Collection<?>, Set<Object>, String> stringifyCollection = (collection, seen) ->
      "[" + collection.stream()
          .map(element -> stringifyHelper(element, seen))
          .collect(Collectors.joining(", ")) + "]";

  private static final BiFunction<Optional<?>, Set<Object>, String> stringifyOptional = (optional, seen) ->
      optional.map(value -> stringifyHelper(value, seen))
          .orElse("null");

  private static final BiFunction<Object[], Set<Object>, String> stringifyObjectArray = (objectArray, seen) ->
      "[" + Arrays.stream(objectArray)
          .map(e -> stringifyHelper(e, seen))
          .collect(Collectors.joining(", ")) + "]";

  private static final Function<Throwable, String> stringifyThrowable = throwable ->
      throwable.getClass().getName() +
          Optional.ofNullable(throwable.getMessage())
              .map(message -> ": " + message)
              .orElse("");

}
