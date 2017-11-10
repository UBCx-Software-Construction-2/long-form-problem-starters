package model;

import java.util.List;

public class SillyWordGame {

    private List<Phrase> phrases;
    //TODO: remove these fields
    private int currentPhraseIndex;
    private int numWordsNeeded;

    public SillyWordGame(List<Phrase> phrases) {
        this.phrases = phrases;
        for(Phrase p : phrases) {
            if (p.needsWord())
                numWordsNeeded++;
        }
    }

    //EFFECTS: returns all phrases in this game
    public List<Phrase> getAllPhrases() {
        return phrases;
    }

    //MODIFIES: this
    //EFFECTS: returns the next phrase in this game that needs a word
    //TODO: remove this method
    public Phrase getNextPhraseNeedingWord() {
        for (int i = currentPhraseIndex; i < phrases.size(); i++){
            if (!phrases.get(currentPhraseIndex).needsWord()){
                currentPhraseIndex++;
            } else {
                numWordsNeeded--;
                return phrases.get(currentPhraseIndex++);
            }
        }
        throw new IllegalStateException();
    }

    //EFFECTS: returns true if more words are needed
    //TODO: remove this method
    public boolean needMoreWords() {
        return numWordsNeeded > 0;
    }
}
