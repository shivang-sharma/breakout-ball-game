import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class SplashScreen {
    public void drawMainMenu(Graphics2D g) {
        // background
        GradientPaint redToBlue = new GradientPaint(5, 7, Color.red, 900, 7, Color.blue);
        g.setPaint(redToBlue);
        g.fillRect(1, 1, 800, 565);
        // border
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 565);
        g.fillRect(0, 0, 800, 3);
        g.fillRect(797, 0, 3, 565);
        g.fillRect(0, 560, 800, 3);
        // Logo
        GradientPaint whiteToBlack = new GradientPaint(5, 7, Color.white, 430, 7, Color.black);
        g.setPaint(whiteToBlack);
        g.fillOval(330, 40, 100, 100);
        // Menu
        GradientPaint redToWhite = new GradientPaint(5, 7, Color.MAGENTA, 550, 7, Color.LIGHT_GRAY);
        g.setPaint(redToWhite);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
        g.drawString("BREAKING BALL GAME", 126, 220);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
        g.drawString("> Press 'N' for noob mode", 170, 270);
        g.drawString("> Press 'E' for expert mode", 170, 300);
        g.drawString("> Press 'H' for hacker mode", 170, 330);
    }
}
