package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class DifferTest {

  private static final Path RESOURCES = Path.of("src/test/resources");

  @Test
  void testStylishFormat() throws Exception {
    assertDiff(
        "stylish",
        "fixtures/stylish/before.json",
        "fixtures/stylish/after.json",
        "fixtures/stylish/expected.txt");
  }

  @Test
  void testYamlInputs() throws Exception {
    assertDiff(
        "stylish",
        "fixtures/yaml/before.yml",
        "fixtures/yaml/after.yml",
        "fixtures/yaml/expected.txt");
  }

  @Test
  void testNestedValuesAndDefaultFormat() throws Exception {
    assertDiff(
        "fixtures/nested/before.json",
        "fixtures/nested/after.json",
        "fixtures/nested/expected.txt");
  }

  @Test
  void testPlainFormat() throws Exception {
    assertDiff(
        "plain",
        "fixtures/plain/before.json",
        "fixtures/plain/after.json",
        "fixtures/plain/expected.txt");
  }

  @Test
  void testJsonFormat() throws Exception {
    assertDiff(
        "json",
        "fixtures/json/before.json",
        "fixtures/json/after.json",
        "fixtures/json/expected.json");
  }

  private void assertDiff(String format, String file1, String file2, String expectedFile)
      throws Exception {
    String expected = readResource(expectedFile);
    String actual =
        Differ.generate(
            RESOURCES.resolve(file1).toString(), RESOURCES.resolve(file2).toString(), format);

    assertEquals(expected, actual);
  }

  private void assertDiff(String file1, String file2, String expectedFile) throws Exception {
    String expected = readResource(expectedFile);
    String actual =
        Differ.generate(RESOURCES.resolve(file1).toString(), RESOURCES.resolve(file2).toString());

    assertEquals(expected, actual);
  }

  private String readResource(String filepath) throws Exception {
    return Files.readString(RESOURCES.resolve(filepath)).replace("\r\n", "\n").stripTrailing();
  }
}
