package extrator;

public class Main {

  public static void main(String[] args) {
    Thread runnerThread = new Thread(new ExtractorRunner());
    runnerThread.start();
  }
}
