package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class DifferTest {

  @TempDir Path tempDir;

  @Test
  public void testGenerate() throws Exception {
    Path file1 = Path.of("src/test/resources/file1.json");
    Path file2 = Path.of("src/test/resources/file2.json");
    Path expectedFile = Path.of("src/test/resources/expected.txt");

    String expected = Files.readString(expectedFile).replace("\r\n", "\n").stripTrailing();

    String actual = Differ.generate(file1.toString(), file2.toString());

    assertEquals(expected, actual);
  }

  @Test
  void testYaml() throws Exception {
    Path file1 = Path.of("src/test/resources/file1.yml");
    Path file2 = Path.of("src/test/resources/file2.yml");
    Path expectedFile = Path.of("src/test/resources/expected.txt");

    String expected = Files.readString(expectedFile).replace("\r\n", "\n").stripTrailing();

    String actual = Differ.generate(file1.toString(), file2.toString());

    assertEquals(expected, actual);
  }

  @Test
  void testNestedValuesAndDefaultFormat() throws Exception {
    Path file1 = tempDir.resolve("before.json");
    Path file2 = tempDir.resolve("after.json");
    Files.writeString(file1, "{\"array\":[1,2],\"object\":{\"nested\":true},\"value\":1}");
    Files.writeString(file2, "{\"array\":[1,2],\"object\":{\"nested\":false},\"value\":2}");

    String actual = Differ.generate(file1.toString(), file2.toString());

    assertEquals(
        "{\n"
            + "    array: [1, 2]\n"
            + "  - object: {nested=true}\n"
            + "  + object: {nested=false}\n"
            + "  - value: 1\n"
            + "  + value: 2\n"
            + "}",
        actual);
  }
}
