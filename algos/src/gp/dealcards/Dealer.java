package gp.dealcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

  public static <E> List<E> dealHand(final List<E> deck, final int handSize) {
    final int deckSize=deck.size();
    final List<E> handView=deck.subList(deckSize-handSize,deckSize);
    final List<E> hand=new ArrayList<>(handView);
    handView.clear();
    return hand;

  }

  public static <E> void shuffleDeck(final List<E> deck) {
    Collections.shuffle(deck);
  }

  public static <E> void shuffleDeck(final List<E> l, final Shuffler shuffler) {
    shuffler.shuffle(l);
  }

}
