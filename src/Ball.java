import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private int ballXPos;
    private int ballYPos;
    private int ballXDir;
    private int ballYDir;

    public Ball() {
        ballXPos = 120;
        ballYPos = 350;
        ballXDir = -1;
        ballYDir = -2;
    }

    public int getBallPositionX() {
        return this.ballXPos;
    }

    public int getBallPositionY() {
        return this.ballYPos;
    }

    public void updatePositionX() {
        ballXPos += ballXDir;
    }

    public void updatePositionY() {
        ballYPos += ballYDir;
    }

    public void changeXDirection() {
        this.ballXDir = -this.ballXDir;
    }

    public void changeYDirection() {
        this.ballYDir = -this.ballYDir;
    }

    public void reset() {
        this.ballXDir = -1;
        this.ballYDir = -2;
        this.ballXPos = 120;
        this.ballYPos = 350;
    }

    public void freeze() {
        this.ballXDir = 0;
        this.ballYDir = 0;
    }

    public boolean intersects(Rectangle rectangle) {
        Rectangle ballRectangle = new Rectangle(this.ballXPos, this.ballYPos, 20, 20);
        return ballRectangle.intersects(rectangle);
    }

    public void draw(Graphics g) {
        g.fillOval(this.ballXPos, this.ballYPos, 20, 20);
    }
}
