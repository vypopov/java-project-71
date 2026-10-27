package hexlet.code;

import java.util.List;

/** Formats differences in the stylish representation. */
public final class Stylish {

  private Stylish() {}

  public static String format(List<DiffEntry> differences) {
    var result = new StringBuilder("{\n");
    for (DiffEntry difference : differences) {
      switch (difference.status()) {
        case "removed" -> append(result, "  - ", difference.key(), difference.oldValue());
        case "added" -> append(result, "  + ", difference.key(), difference.oldValue());
        case "unchanged" -> append(result, "    ", difference.key(), difference.oldValue());
        case "changed" -> {
          append(result, "  - ", difference.key(), difference.oldValue());
          append(result, "  + ", difference.key(), difference.newValue());
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
}
