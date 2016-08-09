package com.gmail.elephant454.typingrpg;

import java.util.ArrayDeque;

public class Player {
    private ArrayDeque<Character> charQueue;
    private Word currentWord;

    public Player() {
        setCharQueue(new ArrayDeque<Character>());
        setCurrentWord(new Word(200, 200, ""));
    }
    public Player(ArrayDeque<Character> charQueue, Word currentWord) {
        setCharQueue(charQueue);
        setCurrentWord(currentWord);
    }

    public Word getCurrentWord() {
        return currentWord;
    }
    public void setCurrentWord(Word currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayDeque<Character> getCharQueue() {
        return charQueue;
    }
    public void setCharQueue(ArrayDeque<Character> charQueue) {
        this.charQueue = charQueue;
    }

    public void doNextCharAttack() {
        
    }
}
