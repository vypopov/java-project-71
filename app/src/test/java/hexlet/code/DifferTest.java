package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class DifferTest {

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
    Path file2 = Path.of("src/test/resources/file2.ym;");
    Path expectedFile = Path.of("src/test/resources/expected.txt");

    String expected = Files.readString(expectedFile).replace("\r\n", "\n").stripTrailing();

    String actual = Differ.generate(file1.toString(), file2.toString());

    assertEquals(expected, actual);
  }
}
