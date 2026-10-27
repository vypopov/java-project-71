package hexlet.code;

public record DiffEntry(String key, String status, Object oldValue, Object newValue) {
  public DiffEntry(String key, String status, Object value) {
    this(key, status, value, null);
  }
}
