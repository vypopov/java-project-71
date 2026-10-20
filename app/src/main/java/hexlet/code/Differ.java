
package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;

/**
 * Compares two configuration files.
 */
public final class Differ {

  /**
   * JSON parser.
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private Differ() {
  }

  private static Map<String, Object> read(final String filepath)
          throws Exception {
    var mapType = MAPPER.getTypeFactory()
            .constructMapType(Map.class, String.class, Object.class);

    return MAPPER.readValue(
            Files.readString(Path.of(filepath)),
            mapType
    );
  }

  /**
   * Generates a difference between two JSON files.
   *
   * @param filepath1 path to the first file
   * @param filepath2 path to the second file
   * @return formatted difference
   * @throws Exception if reading or parsing fails
   */
  public static String generate(final String filepath1,
                                final String filepath2) throws Exception {
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
        result.append("  - ")
                .append(key)
                .append(": ")
                .append(v1)
                .append("\n");
      } else if (!in1) {
        result.append("  + ")
                .append(key)
                .append(": ")
                .append(v2)
                .append("\n");
      } else if (Objects.equals(v1, v2)) {
        result.append("    ")
                .append(key)
                .append(": ")
                .append(v1)
                .append("\n");
      } else {
        result.append("  - ")
                .append(key)
                .append(": ")
                .append(v1)
                .append("\n");
        result.append("  + ")
                .append(key)
                .append(": ")
                .append(v2)
                .append("\n");
      }
    }

    return result.append("}").toString();
  }
}


