package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/** Compares two configuration files. */
public final class Differ {

  private Differ() {}

  private static Map<String, Object> read(final String filepath) throws Exception {
    String content = Files.readString(Path.of(filepath));

    String format = filepath.substring(filepath.lastIndexOf('.') + 1).toLowerCase();

    return Parser.parse(content, format);
  }

  /**
   * Generates a difference between two configuration files.
   *
   * @param filepath1 path to the first file
   * @param filepath2 path to the second file
   * @return formatted difference
   * @throws Exception if reading or parsing fails
   */
  public static String generate(final String filepath1, final String filepath2) throws Exception {
    Map<String, Object> data1 = read(filepath1);
    Map<String, Object> data2 = read(filepath2);

    var keys = new TreeSet<String>();
    keys.addAll(data1.keySet());
    keys.addAll(data2.keySet());

    var result = new StringBuilder("{\n");

    for (String key : keys) {
      boolean in1 = data1.containsKey(key);
      boolean in2 = data2.containsKey(key);
      Object v1 = data1.get(key);
      Object v2 = data2.get(key);

      if (!in2) {
        result.append("  - ").append(key).append(": ").append(v1).append("\n");
      } else if (!in1) {
        result.append("  + ").append(key).append(": ").append(v2).append("\n");
      } else if (Objects.equals(v1, v2)) {
        result.append("    ").append(key).append(": ").append(v1).append("\n");
      } else {
        result.append("  - ").append(key).append(": ").append(v1).append("\n");
        result.append("  + ").append(key).append(": ").append(v2).append("\n");
      }
    }

    return result.append("}").toString();
  }
}
