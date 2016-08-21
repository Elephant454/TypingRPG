package com.gmail.elephant454.typingrpg;

import java.util.ArrayList;

public class WordList {
    ArrayList<Word> words;
    boolean done = false;

    public WordList() {
        setWords(new ArrayList<Word>());
    }
    public WordList(ArrayList<Word> words) {
        setWords(words);
    }

    public ArrayList<Word> getWords() {
        return words;
    }
    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
