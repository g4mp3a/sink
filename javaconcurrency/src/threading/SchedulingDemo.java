package threading;

class VegetableChopper extends Thread {

  public int vegetable_count=0;
  public static boolean chopping=true;

  public VegetableChopper(final String name) {
    this.setName(name);
  }

  @Override
  public void run() {
    while (chopping) {
      System.out.println(this.getName()+" chopped a vegetable!");
      this.vegetable_count++;
    }
  }
}

public class SchedulingDemo {
  public static void main(final String args[]) throws InterruptedException {
    final VegetableChopper barron=new VegetableChopper("Barron");
    final VegetableChopper olivia=new VegetableChopper("Olivia");

    barron.start(); // Barron start chopping
    olivia.start(); // Olivia start chopping
    Thread.sleep(1000); // continue chopping for 1 second
    VegetableChopper.chopping=false; // stop chopping

    barron.join();
    olivia.join();
    System.out.format("Barron chopped %d vegetables.\n",barron.vegetable_count);
    System.out.format("Olivia chopped %d vegetables.\n",olivia.vegetable_count);
  }
}
