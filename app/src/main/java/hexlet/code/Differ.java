
package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {

  public static String generate(String filepath1, String filepath2)
          throws Exception {

    String content1 = Files.readString(Path.of(filepath1));
    String content2 = Files.readString(Path.of(filepath2));

    ObjectMapper mapper = new ObjectMapper();

    var data1 = mapper.readValue(content1, Map.class);
    var data2 = mapper.readValue(content2, Map.class);

    System.out.println(data1);
    System.out.println(data2);

    return "";
  }
}
