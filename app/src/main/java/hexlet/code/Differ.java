package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

  /** Generates a stylish difference between two configuration files. */
  public static String generate(final String filepath1, final String filepath2) throws Exception {
    return generate(filepath1, filepath2, "stylish");
  }

  /** Generates a difference between two configuration files. */
  public static String generate(final String filepath1, final String filepath2, final String format)
      throws Exception {
    Map<String, Object> data1 = read(filepath1);
    Map<String, Object> data2 = read(filepath2);
    var keys = new TreeSet<String>();
    keys.addAll(data1.keySet());
    keys.addAll(data2.keySet());

    var differences = new ArrayList<DiffEntry>();
    for (String key : keys) {
      boolean in1 = data1.containsKey(key);
      boolean in2 = data2.containsKey(key);
      Object value1 = data1.get(key);
      Object value2 = data2.get(key);
      if (!in2) {
        differences.add(new DiffEntry(key, "removed", value1));
      } else if (!in1) {
        differences.add(new DiffEntry(key, "added", value2));
      } else if (Objects.equals(value1, value2)) {
        differences.add(new DiffEntry(key, "unchanged", value1));
      } else {
        differences.add(new DiffEntry(key, "changed", value1, value2));
      }
    }

    return Formatter.format(differences, format);
  }
}
