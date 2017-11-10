package model;

import model.observer_pattern.Observer;
import model.random.BingoCall;
import model.random.NumberSquare;

import java.util.ArrayList;
import java.util.List;

import static model.Game.CARD_SIZE;
import static model.Game.SIDE_LENGTH;

//TODO: implement Observer pattern
public class PlayerCard {

    private List<NumberSquare> numbers;
    private int numberSquaresStamped;
    private boolean hasBingo;

    public PlayerCard(){
        numbers = new ArrayList<>();

        for(int i=0; i < CARD_SIZE; i++){
            numbers.add(new NumberSquare());
        }
    }

    // TODO: refactor this method
    //MODIFIES: this
    //EFFECTS: checks whether bingo call matches a square in this card, stamps if so, and updates hasBingo
    public void checkCallMatch(Object o){
        BingoCall bc = (BingoCall) o;
        int i = numberSquaresMatch(bc);
        for (int j=0; j < i; j++) {
            int index = getSquareIndexOfNextUnstamped(bc);
            stampSquare(index);
            checkIfBingo(index);
        }
    }

    //EFFECTS: returns true if player has column, row or diagonal stamped
    public boolean hasBingo(){
        return hasBingo;
    }

    //EFFECTS: returns all number squares in this card
    public List<NumberSquare> getSquares() { return numbers; }

    //EFFECTS: returns number of squares stamped
    public int getNumberOfSquaresStamped() { return numberSquaresStamped; }

    //EFFECTS: returns true if player has a fully stamped column, row or diagonal
    private void checkIfBingo(int i){
        hasBingo = columnBingo(i) || rowBingo(i) || diagonalBingo(i);
    }

    //EFFECTS: checks all indices in column of index to check whether player has bingo in column
    private boolean columnBingo(int index){
        int colIndices[] = generateColumnIndices(index);
        boolean bingo = true;
        for (int i=0; i < SIDE_LENGTH; i++) {
            if (!numbers.get(colIndices[i]).isStamped()) {
                bingo = false;
                break;
            }
        }
        return bingo;
    }

    //EFFECTS: checks all indices in row of index to check whether player has bingo in row
    private boolean rowBingo(int index) {
        int rowIndices[] = generateRowIndices(index);
        boolean bingo = true;
        for (int i=0; i < SIDE_LENGTH; i++)
            if (!numbers.get(rowIndices[i]).isStamped()){
                bingo = false;
                break;
            }
        return bingo;
    }

    //EFFECTS: if index is on a diagonal, checks whether diagonal bingo is achieved
    private boolean diagonalBingo(int index) {
        int diagonalIndices[] = getDiagonalIndices(index);
        if (diagonalIndices.length != 0) {
            boolean bingo = true;
            for (int diagonalIndex : diagonalIndices) {
                if (!numbers.get(diagonalIndex).isStamped()) {
                    bingo = false;
                    break;
                }
            }
            return bingo;
        } else {
            return false;
        }
    }

    //EFFECTS: returns the number of squares in bc's column that match bc's number
    private int numberSquaresMatch(BingoCall bc){
        int i = 0;
        int match = bc.getNumber();
        for (int j = getColumnFromBC(bc); j < CARD_SIZE; j+=5) {
            if (numbers.get(j).getNumber().equals(match) && !numbers.get(j).isStamped()){
                i++;
            }
        }
        return i;
    }

    //EFFECTS: finds index of next unstamped square that matches column and number of bc
    //         throws IllegalArgumentException if there is no NumberSquare with same number as bc
    private int getSquareIndexOfNextUnstamped(BingoCall bc) {
        int column = getColumnFromBC(bc);
        int match = bc.getNumber();
        for (int i=column; i < CARD_SIZE; i += 5){
            NumberSquare sqr = numbers.get(i);
            if ((!sqr.isStamped()) && sqr.getNumber().equals(match)){
                return i;
            }
        }
        throw new IllegalArgumentException("Number of matching squares exceeded unstamped squares.");
    }

    //MODIFIES: this
    //EFFECTS: stamps the square at index
    private void stampSquare(int index) {
        numbers.get(index).stamp();
        numberSquaresStamped++;
    }

    //EFFECTS: returns the column index of bc
    private int getColumnFromBC(BingoCall bc){
        switch(bc.getLetter()) {
            case 'B':
                return 0;
            case 'I':
                return 1;
            case 'N':
                return 2;
            case 'G':
                return 3;
            default:
                return 4;
        }
    }

    //EFFECTS: generates indices of other squares in same row as index
    private int[] generateRowIndices(int index){
        int rowIndices[] = new int[SIDE_LENGTH];
        int arrayIndex = 0;

        switch (index % SIDE_LENGTH) {
            case 0:
                for (int i=0; i < SIDE_LENGTH; i++) { rowIndices[arrayIndex] = index+i; arrayIndex++; }
                break;
            case 1:
                for (int i=-1; i < SIDE_LENGTH-1; i++) { rowIndices[arrayIndex] = index+i; arrayIndex++; }
                break;
            case 2:
                for (int i=-2; i < SIDE_LENGTH-2; i++) { rowIndices[arrayIndex] = index+i; arrayIndex++; }
                break;
            case 3:
                for (int i=-3; i < SIDE_LENGTH-3; i++) { rowIndices[arrayIndex] = index+i; arrayIndex++; }
                break;
            case 4:
                for (int i=-4; i < SIDE_LENGTH-4; i++) { rowIndices[arrayIndex] = index+i; arrayIndex++; }
                break;
        }
        return rowIndices;
    }

    //EFFECTS: generates indices of other squares in same column as index
    private int[] generateColumnIndices(int index){
        int colIndices[] = new int[SIDE_LENGTH];
        int arrayIndex = 0;

        if (index < 5)
            for (int i=0; i < SIDE_LENGTH; i++) { colIndices[arrayIndex] = index+(i*5); arrayIndex++; }
        else if (index < 10)
            for (int i=-1; i < SIDE_LENGTH-1; i++) { colIndices[arrayIndex] = index+(i*5); arrayIndex++; }
        else if (index < 15)
            for (int i = -2; i < SIDE_LENGTH - 2; i++) { colIndices[arrayIndex] = index + (i * 5); arrayIndex++; }
        else if (index < 20)
            for (int i=-3; i < SIDE_LENGTH-3; i++) { colIndices[arrayIndex] = index+(i*5); arrayIndex++; }
        else
            for (int i=-4; i < SIDE_LENGTH-4; i++) { colIndices[arrayIndex] = index+(i*5); arrayIndex++; }

        return colIndices;
    }

    //EFFECTS: returns true if index is a diagonal index
    private boolean isDiagonalIndex(int index) {
        return (index % (SIDE_LENGTH+1)) == 0 || (index % (SIDE_LENGTH-1)) == 0;
    }

    //EFFECTS: generates indices of other squares in same diagonal as index (0 indices if not diagonal)
    private int[] getDiagonalIndices(int index) {
        int diagonalIndices[] = new int[0];

        if (isDiagonalIndex(index)) {
            diagonalIndices = new int[SIDE_LENGTH];

            int arrayIndex = 0;
            int modFactor = (index % SIDE_LENGTH + 1) == 0 ? SIDE_LENGTH + 1 : SIDE_LENGTH - 1;

            if (modFactor == SIDE_LENGTH - 1) {
                for (int i = modFactor; i < CARD_SIZE; i++)
                    if (i % modFactor == 0)
                        diagonalIndices[arrayIndex] = i;
            } else {
                for (int i = 0; i < CARD_SIZE; i++)
                    if (i % modFactor == 0)
                        diagonalIndices[arrayIndex] = i;
            }
        }

        return diagonalIndices;
    }

}
