package threading;

class CPUWaster extends Thread {
  @Override
  public void run() {
    while (true) {
    }
  }
}

public class ThreadDemo {
  public static void main(final String args[]) throws InterruptedException {

    // display current information about this process
    final Runtime rt=Runtime.getRuntime();
    long usedKB=(rt.totalMemory()-rt.freeMemory())/1024;
    System.out.format("  Process ID: %d\n",ProcessHandle.current().pid());
    System.out.format("Thread Count: %d\n",Thread.activeCount());
    System.out.format("Memory Usage: %d KB\n",usedKB);

    // start 6 new threads
    System.out.println("\nStarting 6 CPUWaster threads...\n");
    for (int i=0; i<6; i++) {
      new CPUWaster().start();
    }

    // display current information about this process
    usedKB=(rt.totalMemory()-rt.freeMemory())/1024;
    System.out.format("  Process ID: %d\n",ProcessHandle.current().pid());
    System.out.format("Thread Count: %d\n",Thread.activeCount());
    System.out.format("Memory Usage: %d KB\n",usedKB);
  }
}
