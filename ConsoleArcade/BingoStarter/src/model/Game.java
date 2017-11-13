package model;

import model.random.BingoNumber;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int CARD_SIZE = 25;
    public static final int SIDE_LENGTH = (int) Math.sqrt(CARD_SIZE);

    private BingoNumber currentCall;
    private List<PlayerCard> cards;
    private boolean gameOver;

    public Game() {
        cards = new ArrayList<>();
        callNext();
    }

    //getters
    public BingoNumber getCurrentCall(){
        return currentCall;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public List<PlayerCard> getCards() {
        List<PlayerCard> playerCards = new ArrayList<>();
        for (PlayerCard o : cards) { //NOTE: refactor this line ONLY.
            if (o.getClass().getSimpleName().equals("PlayerCard"))
                playerCards.add((PlayerCard) o);
        }
        return playerCards;
    }

    //TODO: refactor this method
    //EFFECTS: generates the next bingo call and notifies observers
    public void callNext() {
        currentCall = new BingoNumber();
    }

    //TODO: refactor this method
    //MODIFIES: this
    //EFFECTS: adds observer to list of observers
    public void addCard(PlayerCard pc) {
        cards.add(pc);
    }

    //EFFECTS: sets game over to true if one of the players has bingo
    public void refreshGameOver(){
        for (PlayerCard pc : cards) {
            PlayerCard p = (PlayerCard) pc;
            if (p.hasBingo()) {
                gameOver = true;
                break;
            }
        }
    }
}
