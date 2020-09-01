package gp.dealcards;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {
  public static final DeckFactory INSTANCE=new DeckFactory();

  String[] suits= { "HEARTS", "SPADES", "CLUBS", "DIAMONDS" };
  String[] ranks= { "ACE", "KING", "QUEEN", "JACK", "TEN", "NINE", "EIGHT", "SEVEN", "SIX", "FIVE", "FOUR", "THREE",
      "TWO" };

  public List<String> getStandard52CardDeck() {
    final List<String> deck=new ArrayList<>();

    for (final String s : this.suits) {
      for (final String r : this.ranks) {
        deck.add(r+" of "+s);
      }
    }

    return deck;
  }

}
