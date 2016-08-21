package com.gmail.elephant454.typingrpg;

import javax.swing.JPanel;

import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Rectangle2D;

public class GraphicsPanel extends JPanel implements KeyListener, Runnable {
    private static final long serialVersionUID = 42L;

    boolean running = true;

    float oldTime = System.nanoTime()/1000000000;
    float currentTime = oldTime;
    float deltaTime = 0;
    float fps = 0;

    RandomPalette randomPalette = new RandomPalette(0.5f, 0.6f);

    int fontSize = 12;

    WordList firstWords = new WordList();

    Player player = new Player();


    public GraphicsPanel () {
        this.setSize(800, 600);

        int minX = this.getWidth()/2;
        int maxX = this.getWidth();
        int minY = fontSize;
        int maxY = this.getHeight() - fontSize;
        new Word("boop", minX, minY, maxX, maxY, randomPalette.next()).addToParentWordList(firstWords);
        new Word("kek", minX, minY, maxX, maxY, randomPalette.next()).addToParentWordList(firstWords);
        new Word("idunno", minX, minY, maxX, maxY, randomPalette.next()).addToParentWordList(firstWords);
        for(Word word : firstWords.getWords()) {
            System.out.println(word.getText() + "(" + word.getX() + "," + word.getY() + ")");
        }

        setFocusable(true);
        addKeyListener(this);
        this.requestFocusInWindow(true);
        (new Thread(this)).start();
    }

    @Override
    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0, 0, getWidth(), getHeight());

        g2.setFont(new Font("Inconsolata", Font.PLAIN, fontSize));

        for(Word word : firstWords.getWords()) {
            if(!word.isDefeated()) {
                g2.setPaint(word.getColor());
                g2.drawString(word.getText(), word.getX(), word.getY());
            }
        }
    }

    public void run() {
        while(running) {
            currentTime = System.nanoTime()/1000000000;
            deltaTime = currentTime-oldTime;
            oldTime = currentTime;
            fps = 1/deltaTime;
            gameLogic();
            repaint();
        }
    }

    public void gameLogic() {
        Character currentPlayerCharacter = player.getCharQueue().pollLast();
        Character currentWordCharacter = player.getCurrentWord().getNextChar();
        if(player.getCurrentWord().isDefeated()) {
            Iterator<Word> wordsIterator = firstWords.getWords().iterator();
            while(wordsIterator.hasNext()) {
                Word word = wordsIterator.next();

                if(!word.isDefeated() && currentPlayerCharacter != null && currentPlayerCharacter == word.getNextChar()) {
                    currentPlayerCharacter = player.getCharQueue().pollLast();
                    player.setCurrentWord(word);
                    word.incrementProgress();
                }
            }
        }

        if(currentPlayerCharacter != null) {
            currentWordCharacter = player.getCurrentWord().getNextChar();
            if(currentWordCharacter == currentPlayerCharacter) {
                player.getCurrentWord().incrementProgress();
                currentPlayerCharacter = player.getCharQueue().pollLast();
            }else {
                // add some sort of penalty for getting it wrong
                currentPlayerCharacter = player.getCharQueue().pollLast();
            }
        }
    }

    public void keyTyped(KeyEvent e){
        player.getCharQueue().addFirst(e.getKeyChar());
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
