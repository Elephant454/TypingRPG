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

    ArrayList<String> words = new ArrayList<>();
    int currentWordIndex = 0;

    public GraphicsPanel () {
        words.add("boop");
        words.add("kek");

        //setBackground(Color.GRAY);
        this.setSize(800, 600);

        setFocusable(true);
        addKeyListener(this);
        this.requestFocusInWindow(true);
        (new Thread(this)).start();
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

        g2.setFont(new Font("Inconsolata", Font.PLAIN, 12));
        int usableHeight = this.getHeight() - words.size()*g2.getFont().getSize();
        for(int i=0; i<words.size(); i++) {
            g2.drawString(words.get(i), 20, (usableHeight/words.size()*i) + g2.getFont().getSize());
        }

    }

    public void run() {
        handleInput();
        while(running) {
            currentTime = System.nanoTime()/1000000000;
            deltaTime = currentTime-oldTime;
            oldTime = currentTime;
            fps = 1/deltaTime;
            repaint();
        }
    }

    public void handleInput() {
        
    }

    public void keyTyped(KeyEvent e){
        for(int i=0; i<words.size(); i++) {
            if(words.get(i).length() > 0) {
                if(e.getKeyChar() == words.get(i).charAt(0)) {
                    words.set(i, words.get(i).substring(1));
                }
            } else {
                words.remove(i);
            }
        }
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
