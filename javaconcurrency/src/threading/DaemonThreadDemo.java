package threading;

class KitchenCleaner extends Thread {
  @Override
  public void run() {
    while (true) {
      System.out.println("Olivia cleaned the kitchen.");
      try {
        Thread.sleep(1000);
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class DaemonThreadDemo {
  public static void main(final String[] args) throws InterruptedException {
    final Thread olivia=new KitchenCleaner();
    olivia.setDaemon(true); // If not, the program will never exit
    olivia.start();

    System.out.println("Barron is cooking...");
    Thread.sleep(600);
    System.out.println("Barron is cooking...");
    Thread.sleep(600);
    System.out.println("Barron is cooking...");
    Thread.sleep(600);
    System.out.println("Barron is done!");
  }
}
