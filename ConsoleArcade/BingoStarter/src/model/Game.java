package model;

import model.observer_pattern.Observer;
import model.observer_pattern.Subject;
import model.random.BingoCall;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static int CARD_SIZE = 25;
    public static int SIDE_LENGTH = (int) Math.sqrt(CARD_SIZE);

    private BingoCall currentCall;
    private List<PlayerCard> cards;
    private boolean gameOver;

    public Game() {
        cards = new ArrayList<>();
        callNext();
    }

    //getters
    public BingoCall getCurrentCall(){
        return currentCall;
    }

    public boolean isGameOver(){
        checkGameOver();
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
        currentCall = new BingoCall();
    }

    //TODO: refactor this method
    //MODIFIES: this
    //EFFECTS: adds observer to list of observers
    public void addCard(PlayerCard pc) {
        cards.add(pc);
    }

    //EFFECTS: sets game over to true if one of the players has bingo
    private void checkGameOver(){
        for (PlayerCard pc : cards) {
            PlayerCard p = (PlayerCard) pc;
            if (p.hasBingo()) {
                gameOver = true;
                break;
            }
        }
    }
}
