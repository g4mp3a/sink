package producerconsumer;

/**
 * Producers serving soup for Consumers to eat
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class SoupProducer extends Thread {

  private final BlockingQueue<String> servingLine;

  public SoupProducer(final BlockingQueue<String> servingLine) {
    this.servingLine=servingLine;
  }

  @Override
  public void run() {
    for (int i=0; i<20; i++) { // serve 20 bowls of soup
      try {
        this.servingLine.add("Bowl #"+i);
        System.out.format("Served Bowl #%d - remaining capacity: %d\n",i,
            this.servingLine.remainingCapacity());
        Thread.sleep(200); // time to serve a bowl of soup
      } catch (final Exception e) {
        e.printStackTrace();
      }
    }
    this.servingLine.add("no soup for you!");
  }
}

class SoupConsumer extends Thread {

  private final BlockingQueue<String> servingLine;

  public SoupConsumer(final BlockingQueue<String> servingLine) {
    this.servingLine=servingLine;
  }

  @Override
  public void run() {
    while (true) {
      try {
        final String bowl=this.servingLine.take();
        if (bowl=="no soup for you!") {
          break;
        }
        System.out.format("Ate %s\n",bowl);
        Thread.sleep(300); // time to eat a bowl of soup
      } catch (final Exception e) {
        e.printStackTrace();
      }
    }
  }
}

public class ProducerConsumer101 {
  public static void main(final String args[]) {
    final BlockingQueue<String> servingLine=new ArrayBlockingQueue<>(5);
    new SoupConsumer(servingLine).start();
    // so that allow consumption rate >= production rate, use 2 consumers
    new SoupConsumer(servingLine).start();
    new SoupProducer(servingLine).start();
  }
}
