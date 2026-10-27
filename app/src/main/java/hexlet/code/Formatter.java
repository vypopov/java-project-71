package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;

/** Selects a formatter for generated differences. */
public final class Formatter {

  private Formatter() {}

  public static String format(List<DiffEntry> differences, String format) {
    return switch (format) {
      case "stylish" -> Stylish.format(differences);
      case "plain" -> Plain.format(differences);
      case "json" -> Json.format(differences);
      default -> throw new IllegalArgumentException("Unsupported format: " + format);
    };
  }
}
