package ui;

import model.Monster;
import model.Room;
import model.Treasure;

import java.util.Scanner;

public class Game {

    private static final String INVALID_CHOICE = "You have entered an invalid choice. Please try again.";
    private static final String ANOTHER_ROUND = "Y";
    private static final String STOP = "N";
    private static final String QUIT = "Q";

    private Room current;
    private Room start;
    private Scanner scanner;
    private boolean gameOver;
    private boolean roundOver;

    public Game(Room r) {
        start = r;
        current = start;
        scanner = new Scanner(System.in);
        gameOver = false;
        playGame();
    }

    //MODIFIES: this
    //EFFECTS: runs gameplay loop including parsing user input and displaying options
    private void playGame() {
        printInstructions();

        while(!gameOver) {
            parsePlayerNextInstruction();
            if (gameOver) break;
            if (roundOver())
                offerAnotherRound();
            if (gameOver || roundOver()) break;
            current.printNextChoices();
        }

        System.out.println("You have escaped...");
        scanner.close();

    }

    //EFFECTS: prints options for player to try again
    private void offerAnotherRound() {
        System.out.println("\nThere are no more choices; you have reached the end of the maze.");
        System.out.println("Would you like to play again? enter Y (Yes) or N (No): ");
        parsePlayerNextInstruction();
    }

    //EFFECTS: waits for & parses player's choice
    private void parsePlayerNextInstruction() {
        String str = "";
        while (str.length() == 0)  {
            if (scanner.hasNext())
                str += scanner.nextLine();
        }
        str = str.trim();
        handleInput(str);
    }

    //EFFECTS: selects next choice if s is a number, or quits/continues if s is a valid string
    private void handleInput(String s) {
        if (s.length() > 0) {
            try {
                int input = Integer.parseInt(s);
                if (input > 0) {
                    printNextChoiceById(input - 1);
                } else {
                    System.out.println(INVALID_CHOICE);
                }
            } catch (NumberFormatException e){
                updateGameState(s.toUpperCase());
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: displays the next option chosen from the options displayed by current room
    private void printNextChoiceById(int id) {
        int monsterRange = current.getMonsterRange();
        if (id < monsterRange) {
            Monster m = current.getMonster(id);
            m.printOutcome();
            roundOver = true;
            return;
        }

        id -= monsterRange;
        int treasureRange = current.getTreasureRange();

        if (id < treasureRange) {
            Treasure t = current.getTreasure(id);
            t.printOutcome();
            roundOver = true;
            return;
        }

        id -= treasureRange;
        int roomRange = current.getRoomRange();

        if (id < roomRange) {
            current = current.getRoom(id);
        } else {
            System.out.println(INVALID_CHOICE);
        }
    }

    //EFFECTS: interprets user command to continue or quit game
    private void updateGameState(String command) {
        switch(command) {
            case QUIT:
            case STOP:
                gameOver = true;
                break;
            case ANOTHER_ROUND:
                current = start;
                roundOver = false;
                break;
            default:
                System.out.println(INVALID_CHOICE);
                break;
        }
    }

    //EFFECTS: prints initial instructions for the game
    private void printInstructions() {
        System.out.println("Welcome to Monster Maze, a dangerous 'choose your own path' game.");
        System.out.println("You will travel through the maze by selecting a choice out of every set of options.");
        System.out.println("Once you make a choice, you cannot go backwards.");
        System.out.println("Enter q (Quit) at any time to escape the maze.");
        System.out.println("Good luck!\n");

        System.out.println("For each set of options, enter the number corresponding to your choice.\n");

        current.printNextChoices();
    }

    //EFFECTS: produces true if the current choice has no more options
    private boolean roundOver() {
        return roundOver;
    }

}
