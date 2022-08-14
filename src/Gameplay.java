import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements ActionListener, KeyListener {
    private boolean play = false;
    private boolean menu = true;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 10;
    private MapGenerator map;
    private Ball ball;
    private Paddle paddle;
    private SplashScreen splashScreen;

    public Gameplay() {
        map = new MapGenerator(3, 7);
        ball = new Ball();
        paddle = new Paddle();
        splashScreen = new SplashScreen();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (menu) {
            splashScreen.drawMainMenu((Graphics2D) g);
        } else {
            // background
            GradientPaint redToBlue = new GradientPaint(5, 7, Color.red, 900, 7, Color.blue);
            g2.setPaint(redToBlue);
            g.fillRect(1, 1, 800, 565);

            // draw map
            map.draw((Graphics2D) g);

            // borders
            g.setColor(Color.yellow);
            g.fillRect(0, 0, 3, 565);
            g.fillRect(0, 0, 800, 3);
            g.fillRect(797, 0, 3, 565);

            // the socre
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("SCORE : " + score, 565, 40);

            // the paddle
            paddle.draw(g);

            // the ball
            ball.draw(g);
        }
        GradientPaint whiteToBlack = new GradientPaint(5, 7, Color.green, 430, 7, Color.white);
        if (this.totalBricks <= 0) {
            play = false;
            ball.freeze();
            g.setColor(Color.PINK);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Congratualtion! You completed the level: " + score, 40, 250);

            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Press ENTER to restart", 200, 290);
            g.drawString("Press 'M' to return to menu", 200, 330);
        }
        if (ball.getBallPositionY() > 540) {
            play = false;
            ball.freeze();
            g2.setPaint(whiteToBlack);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("Game Over, Score : " + score, 80, 230);

            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Press ENTER to restart", 130, 280);
            g.drawString("Press 'M' to return to menu", 90, 315);
        }
        g.dispose();

    }

    public void setup(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (this.play) {
            if (ball.intersects(paddle.getRectangle())) {
                ball.changeYDirection();
            }
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickXPos = j * map.brickWidth + 40;
                        int brickYPos = i * map.brickHeight + 80;
                        int brickHeight = map.brickHeight;
                        int brickWidth = map.brickWidth;
                        Rectangle brickRect = new Rectangle(brickXPos, brickYPos, brickWidth, brickHeight);
                        if (ball.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 10;
                            if (ball.getBallPositionX() + 19 <= brickRect.x
                                    || ball.getBallPositionX() + 1 >= brickRect.x + brickRect.width) {
                                ball.changeXDirection();
                            } else {
                                ball.changeYDirection();
                            }
                            break A;
                        }
                    }
                }
            }
            ball.updatePositionX();
            ball.updatePositionY();
            if (ball.getBallPositionX() < 0) {
                ball.changeXDirection();
                ;
            }
            if (ball.getBallPositionY() < 0) {
                ball.changeYDirection();
            }
            if (ball.getBallPositionX() > 780) {
                ball.changeXDirection();
                ;
            }
        }
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (menu) {
            if (e.getKeyCode() == KeyEvent.VK_N) {
                this.menu = false;
                this.play = true;
            } else if (e.getKeyCode() == KeyEvent.VK_E) {
                this.delay = 8;
                this.timer = new Timer(delay, this);
                timer.start();
                this.menu = false;
                this.play = true;
            } else if (e.getKeyCode() == KeyEvent.VK_H) {
                this.delay = 7;
                timer = new Timer(delay, this);
                timer.start();
                this.menu = false;
                this.play = true;
            }
        } else {
            if (this.play) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveLeft();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                restart();
            } else if (e.getKeyCode() == KeyEvent.VK_M) {
                returnToMainMenu();
            }
        }
    }

    private void moveRight() {
        this.play = true;
        this.paddle.moveRight();
    }

    private void moveLeft() {
        this.play = true;
        this.paddle.moveLeft();
    }

    private void restart() {
        this.play = true;
        this.score = 0;
        this.totalBricks = 21;
        this.ball.reset();
        this.paddle.reset();
        map.resetMap();
        repaint();
    }

    private void returnToMainMenu() {
        this.play = false;
        this.menu = true;
        this.score = 0;
        this.totalBricks = 21;
        this.ball.reset();
        this.paddle.reset();
        map.resetMap();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
