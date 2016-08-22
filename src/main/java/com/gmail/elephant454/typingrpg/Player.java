package com.gmail.elephant454.typingrpg;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Player {
    private ArrayDeque<Character> charQueue;
    private Word currentWord;

    public Player() {
        setCharQueue(new ArrayDeque<Character>());
        setCurrentWord(new Word("", 200, 200));
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

    public void searchForNextWord(WordList wordList) {
        Character currentPlayerCharacter = charQueue.pollLast();
        if (currentPlayerCharacter != null) {
            Iterator<Word> wordsIterator = wordList.getWords().iterator();
            while(wordsIterator.hasNext()) {
                Word word = wordsIterator.next();

                if(!word.isDefeated() && currentPlayerCharacter == word.getNextChar()) {
                    if(charQueue.size() != 0) currentPlayerCharacter = charQueue.pollLast();
                    else currentPlayerCharacter = 0;
                    setCurrentWord(word);
                    word.incrementProgress();
                }
            }
        }
    }

    public void attackCurrentWord() {

        Character currentPlayerCharacter = charQueue.pollLast();
        if(currentPlayerCharacter != null) {
            Character currentWordCharacter = currentWord.getNextChar();

            currentWordCharacter = currentWord.getNextChar();
            if(currentWordCharacter == currentPlayerCharacter) {
                currentWord.incrementProgress();
            }else {
                // add some sort of penalty for getting it wrong
            }
        }
    }
}
