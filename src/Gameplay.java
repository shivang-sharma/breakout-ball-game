import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, KeyListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 10;
    private int playerX = 350;
    private int ballXPos = 120;
    private int ballYPos = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;


    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g) {
        // background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 800, 500);

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 500);
        g.fillRect(0, 0, 800, 3);
        g.fillRect(797, 0, 3, 500);

        // the paddle
        g.setColor(Color.white);
        g.fillRect(playerX, 455, 100, 10);

        // the ball
        g.fillOval(ballXPos, ballYPos, 20, 20);
        g.dispose();

    }
    
    public void setup(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (this.play) {
            if (new Rectangle(ballXPos, ballYPos, 20, 20).intersects(new Rectangle(playerX, 455, 100, 10))) {
                ballYDir = -ballYDir;
            }
            ballXPos += ballXDir;
            ballYPos += ballYDir;
            if(ballXPos <0) {
                ballXDir = -ballXDir;
            }
            if(ballYPos <0) {
                ballYDir = -ballYDir;
            }
            if(ballXPos > 780) {
                ballXDir = -ballXDir;
            }
        }
        repaint();
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.playerX >= 700) {
                this.playerX = 700;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.playerX <= 3) {
                this.playerX = 3;
            } else {
                moveLeft();
            }
        }
    }
    private void moveRight() {
        this.play = true;
        this.playerX+=20;
    }
    private void moveLeft() {
        this.play = true;
        this.playerX -=20;
    }
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}    
}
