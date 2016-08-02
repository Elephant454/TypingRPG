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

    ArrayList<Character> characterBuffer = new ArrayList<>();

    public GraphicsPanel () {
        this.setSize(800, 600);

        int usableHeight = this.getHeight() - words.size()*fontSize;
        int usableWidth = this.getWidth()/2;
        createRandomWord(usableHeight, usableWidth, "boop");
        createRandomWord(usableHeight, usableWidth, "kek");

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
        //int[] xpoints = {10, 0, 30, 40};
        //int[] ypoints = {0, 20, 20, 0};
        //Polygon poly = new Polygon(xpoints, ypoints, 4);
        //g2.setPaint(randomPalette.next());
        //g2.fill(poly);

        g2.clearRect(0, 0, getWidth(), getHeight());

        g2.setFont(new Font("Inconsolata", Font.PLAIN, fontSize));

        for(Word word : words) {
            g2.drawString(word.getText(), word.getX(), word.getY());
        }
    }

    public void run() {
        gameLogic();
        while(running) {
            currentTime = System.nanoTime()/1000000000;
            deltaTime = currentTime-oldTime;
            oldTime = currentTime;
            fps = 1/deltaTime;
            repaint();
        }
    }

    public void gameLogic() {
        for(int i=0; i<words.size(); i++) {
            if(words.get(i).getText().length() < 1) words.remove(i);
        }
        
    }

    public void keyTyped(KeyEvent e){
        if(words.size() > 0) {
            for(int i=0; i<words.size(); i++) {
                System.out.println(words.get(i).getText().length());
                if(words.get(i).getText().length() == 0) {
                    words.remove(i);
                    System.out.println("This happened!");
                }
                if(e.getKeyChar() == words.get(i).getText().charAt(0)) {
                    words.get(i).setText(words.get(i).getText().substring(1));
                }
            }
        }else {
            System.out.println("Level complete!");
        }

        //System.out.print("Size: " + words.size() + " Contents: ");
        //for(Word word : words) {
        //System.out.print(word.getText() + ", ");
        //}
        System.out.println("");
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
