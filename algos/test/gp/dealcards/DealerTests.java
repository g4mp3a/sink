package gp.dealcards;

import org.junit.Test;
import java.util.List;
import java.util.Random;

public class DealerTests {

  @Test
  public void test1() {
    final List<String> playingCardDeck=DeckFactory.INSTANCE.getStandard52CardDeck();
    Dealer.shuffleDeck(playingCardDeck);
    final List<String> hand=Dealer.dealHand(playingCardDeck,5);

    assert (hand.size()==5);
    System.out.println(hand);
  }

  @Test
  public void test2() {
    final List<String> playingCardDeck=DeckFactory.INSTANCE.getStandard52CardDeck();
    Dealer.shuffleDeck(playingCardDeck,new Shuffler() {
      @Override
      public <E> void shuffle(final List<E> l) {
        this.shuffle(l,new Random());
      }

      @Override
      public <E> void shuffle(final List<E> l, final Random rnd) {
        for (int i=l.size(); i>1; i--) {
          this.swap(l,i-1,rnd.nextInt(i));
        }
      }

      private <E> void swap(final List<E> l, final int i, final int j) {
        final E tmp=l.get(i);
        l.set(i,l.get(j));
        l.set(j,tmp);
      }
    });

    final List<String> hand=Dealer.dealHand(playingCardDeck,5);

    assert (hand.size()==5);
    System.out.println(hand);

  }

}
