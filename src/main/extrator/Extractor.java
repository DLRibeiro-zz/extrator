package extrator;

public interface Extractor<T> {

  public Metrics extract(T t);
}
