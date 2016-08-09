package com.gmail.elephant454.typingrpg;

public class Word {
    private int x;
    private int y;
    private String text;

    public Word() {
        setX(100);
        setY(100);
        setText("foobar");
    }

    public Word(int x, int y, String text) {
        setX(x);
        setY(y);
        setText(text);
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

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public char getNextChar() {
        return text.charAt(0);
    }
    public void removeChar() {
        text = text.substring(1);
    }

    public boolean isEmpty() {
        return text.length() < 1;
    }
}
