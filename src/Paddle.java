import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
    private int playerX;

    public Paddle() {
        this.playerX = 320;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.playerX, 550, 100, 10);
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.playerX, 550, 100, 10);
    }

    public void moveRight() {
        if (this.playerX >= 700) {
            this.playerX = 700;
        } else {
            this.playerX += 20;
        }
    }

    public void moveLeft() {
        if (this.playerX <= 3) {
            this.playerX = 3;
        } else {
            this.playerX -= 20;
        }
    }

    public void reset() {
        this.playerX = 320;
    }
}
