package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;
import java.util.List;

/** Formats differences as JSON. */
public final class Json {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private Json() {}

  public static String format(List<DiffEntry> differences) {
    try {
      return MAPPER.writeValueAsString(differences);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Could not serialize differences as JSON", e);
    }
  }
}
