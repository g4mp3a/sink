package threading;

class ChefOliviaRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println("Olivia started & waiting for sausage to thaw...");
    try {
      Thread.sleep(3000);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Olivia is done cutting sausage.");
  }
}

public class RunnableDemo {
  public static void main(final String args[]) throws InterruptedException {
    System.out.println("Barron started & requesting Olivia's help.");
    // Remember to synchronize access to instance variables as all threads can
    // affect them
    // when using the same runnable instance between multiple threads
    final Thread olivia=new Thread(new ChefOliviaRunnable());

    System.out.println("Barron tells Olivia to start.");
    olivia.start();

    System.out.println("Barron continues cooking soup.");
    Thread.sleep(500);

    System.out.println("Barron patiently waits for Olivia to finish and join...");
    olivia.join();

    System.out.println("Barron and Olivia are both done!");
  }
}
