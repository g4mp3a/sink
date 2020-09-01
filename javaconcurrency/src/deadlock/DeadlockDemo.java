package deadlock;

/**
 * Three philosophers, thinking and eating sushi
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {

  private Lock firstChopstick, secondChopstick;
  private static int sushiCount=500000; // 5;

  public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
    this.setName(name);
    this.firstChopstick=firstChopstick;
    this.secondChopstick=secondChopstick;
  }

  @Override
  public void run() {
    while (sushiCount>0) { // eat sushi until it's all gone

      // pick up chopsticks
      firstChopstick.lock();
      secondChopstick.lock();

      // take a piece of sushi
      if (sushiCount>0) {
        sushiCount--;
        System.out.println(this.getName()+" took a piece! Sushi remaining: "+sushiCount);
      }

      // put down chopsticks
      secondChopstick.unlock();
      firstChopstick.unlock();
    }
  }
}

public class DeadlockDemo {
  public static void main(String[] args) {
    Lock chopstickA=new ReentrantLock();
    Lock chopstickB=new ReentrantLock();
    Lock chopstickC=new ReentrantLock();

    /*
     * Chopstick A first priority
     * Chopstick B has second priority
     * Chopstick C has third priority
     */
    new Philosopher("Barron",chopstickA,chopstickB).start();
    new Philosopher("Olivia",chopstickB,chopstickC).start();
    // new Philosopher("Steve",chopstickC,chopstickA).start();
    new Philosopher("Steve",chopstickA,chopstickC).start();
  }
}
