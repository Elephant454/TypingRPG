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

    ArrayList<Word> words = new ArrayList<>();
    int currentWordIndex = 0;

    ArrayDeque<Character> charqueue = new ArrayDeque<>();

    public GraphicsPanel () {
        this.setSize(800, 600);

        int usableHeight = this.getHeight() - words.size()*fontSize;
        int usableWidth = this.getWidth()/2;
        createRandomWord(usableHeight, usableWidth, "boop");
        createRandomWord(usableHeight, usableWidth, "kek");
        createRandomWord(usableHeight, usableWidth, "idunno");

        setFocusable(true);
        addKeyListener(this);
        this.requestFocusInWindow(true);
        (new Thread(this)).start();
    }

    public void createRandomWord(int usableHeight, int usableWidth, String text) {
        words.add(new Word((int) (Math.random()*this.getWidth()/2) + usableWidth, (int) Math.random()*usableHeight + fontSize, text));
    }

    @Override
    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0, 0, getWidth(), getHeight());

        g2.setFont(new Font("Inconsolata", Font.PLAIN, fontSize));

        for(Word word : words) {
            g2.setPaint(randomPalette.next());
            g2.drawString(word.getText(), word.getX(), word.getY());
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
        System.out.println(charqueue);
        if(words.size() > 0 && !charqueue.isEmpty()) {
            for(int i=0; i<words.size(); i++) {
                if(words.get(i).getText().length() < 1) words.remove(i);
                if(charqueue.getLast() == words.get(i).getText().charAt(0)) {
                    words.get(i).setText(words.get(i).getText().substring(1));
                    charqueue.removeLast();
                }
            }
        //}else {
        //System.out.println("Level complete!");
        }
    }

    public void keyTyped(KeyEvent e){
        charqueue.addFirst(e.getKeyChar());
        System.out.println(charqueue);
        //System.out.println(!charqueue.isEmpty());

        //System.out.print("Size: " + words.size() + " Contents: ");
        //for(Word word : words) {
        //System.out.print(word.getText() + ", ");
        //}
        //System.out.println("");
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
