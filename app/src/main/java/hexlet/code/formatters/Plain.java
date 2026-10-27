package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;
import java.util.Map;

/** Formats differences as plain text. */
public final class Plain {

  private Plain() {}

  public static String format(List<DiffEntry> differences) {
    var result = new StringBuilder();
    for (DiffEntry difference : differences) {
      switch (difference.status()) {
        case "removed" ->
            result.append("Property '").append(difference.key()).append("' was removed\n");
        case "added" ->
            result
                .append("Property '")
                .append(difference.key())
                .append("' was added with value: ")
                .append(stringify(difference.oldValue()))
                .append("\n");
        case "changed" ->
            result
                .append("Property '")
                .append(difference.key())
                .append("' was updated. From ")
                .append(stringify(difference.oldValue()))
                .append(" to ")
                .append(stringify(difference.newValue()))
                .append("\n");
        case "unchanged" -> {}
        default ->
            throw new IllegalArgumentException("Unsupported diff status: " + difference.status());
      }
    }
    if (!result.isEmpty()) {
      result.setLength(result.length() - 1);
    }
    return result.toString();
  }

  private static String stringify(Object value) {
    if (value instanceof Map<?, ?> || value instanceof List<?>) {
      return "[complex value]";
    }
    if (value instanceof String string) {
      return "'" + string + "'";
    }
    return String.valueOf(value);
  }
}
