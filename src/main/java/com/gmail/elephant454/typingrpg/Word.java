package com.gmail.elephant454.typingrpg;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.Color;

public class Word {
    private String text;
    private int x;
    private int y;

    private Color color;

    private int progress = 0;
    private ArrayList<WordList> parentWordLists = new ArrayList<>();

    public Word() {
        setX(100);
        setY(100);
        setText("foobar");
    }

    public Word(String text, int x, int y) {
        setX(x);
        setY(y);
        setText(text);
    }
    public Word(String text, int x, int y, Color color) {
        setX(x);
        setY(y);
        setText(text);
        setColor(color);
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text.substring(progress);
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public char getNextChar() {
        if(isDefeated()) return 0;
        else return text.charAt(progress);
    }
    public void removeChar() {
        text = text.substring(1);
    }

    public int getProgress() {
        return progress;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
    public void incrementProgress() {
        progress++;
    }
    public boolean isDefeated() {
        return progress >= text.length();
    }

    public void addToParentWordList(WordList wordList) {
        parentWordLists.add(wordList);
        wordList.getWords().add(this);
    }
}
