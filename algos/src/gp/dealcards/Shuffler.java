package gp.dealcards;

import java.util.List;
import java.util.Random;

public interface Shuffler {

  public <T> void shuffle(List<T> l);

  public <T> void shuffle(List<T> l, Random rnd);

}
