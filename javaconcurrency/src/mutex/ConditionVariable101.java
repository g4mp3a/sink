package mutex;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPerson extends Thread {

  private final int personID;
  private static Lock slowCookerLid=new ReentrantLock();
  private static int servings=11;
  private static Condition soupTaken=slowCookerLid.newCondition();

  public HungryPerson(final int personID) {
    this.personID=personID;
  }

  /**
   * This version results in busy waiting.
   */
  // @Override
  // public void run() {
  // while (servings > 0) {
  // slowCookerLid.lock();
  // try {
  // if ((this.personID != (servings % 2)) && (servings > 0)) { // check if it's
  // your turn
  // servings--; // it's your turn - take some soup!
  // System.out.println(String.format("Person %d took some soup! Servings left:
  // %d%n",
  // this.personID, servings));
  // } else { // not your turn - put the lid back
  // System.out.format("Person %d checked... then put the lid back.%n",
  // this.personID);
  // }
  // } catch (final Exception e) {
  // e.printStackTrace();
  // } finally {
  // slowCookerLid.unlock();
  // }
  // }
  // }

  @Override
  public void run() {
    while (servings>0) {
      slowCookerLid.lock();
      try {
        while ((this.personID!=(servings%5))&&(servings>0)) { // check if it's not your turn
          System.out.format("Person %d checked... then put the lid back.\n",this.personID);
          soupTaken.await();
        }
        if (servings>0) { // can only take a serving while one is still to be had
          servings--; // it's your turn - take some soup!
          System.out.format("Person %d took some soup! Servings left: %d\n",this.personID,servings);
          // soupTaken.signal(); // works when only 2 threads are eating soup
          soupTaken.signalAll();
        }
      } catch (final Exception e) {
        e.printStackTrace();
      } finally {
        slowCookerLid.unlock();
      }
    }
  }

}

public class ConditionVariable101 {
  public static void main(final String args[]) {
    for (int i=0; i<5; i++) {
      new HungryPerson(i).start();
    }
  }
}
