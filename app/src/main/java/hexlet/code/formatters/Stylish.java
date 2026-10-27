package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;

/** Formats differences in the stylish representation. */
public final class Stylish {

  private Stylish() {}

  public static String format(List<DiffEntry> differences) {
    var result = new StringBuilder("{\n");
    for (DiffEntry difference : differences) {
      switch (difference.status()) {
        case "removed" ->
            append(result, replicate(" ", 2) + "- ", difference.key(), difference.oldValue());
        case "added" ->
            append(result, replicate(" ", 2) + "+ ", difference.key(), difference.oldValue());
        case "unchanged" ->
            append(result, replicate(" ", 4), difference.key(), difference.oldValue());
        case "changed" -> {
          append(result, replicate(" ", 2) + "- ", difference.key(), difference.oldValue());
          append(result, replicate(" ", 2) + "+ ", difference.key(), difference.newValue());
        }
        default ->
            throw new IllegalArgumentException("Unsupported diff status: " + difference.status());
      }
    }
    return result.append("}").toString();
  }

  private static void append(StringBuilder result, String prefix, String key, Object value) {
    result.append(prefix).append(key).append(": ").append(value).append("\n");
  }

  private static String replicate(String value, int count) {
    return value.repeat(count);
  }
}
