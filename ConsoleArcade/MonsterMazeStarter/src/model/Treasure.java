package model;

public class Treasure {

    private int prize;
    private String optionMessage;

    public Treasure(int prize) {
        this.optionMessage = "Claim your treasure!";
        this.prize = prize;
    }

    //EFFECTS: prints a message representing this possible next choice
    public void printOptionMessage() {
        System.out.println(optionMessage);
    }

    //EFFECTS: prints the result of choosing this choice
    public void printOutcome() {
        System.out.println("Your prize is " + prize + " spendibees.");
    }

}
