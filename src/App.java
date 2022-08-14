import javax.swing.JFrame;

public class App {
    private JFrame frame;
    private Gameplay gameplay;

    public App() {
        frame = new JFrame();
        gameplay = new Gameplay();
    }

    public void start() {
        gameplay.setup(0, 0, 800, 900);
        frame.add(gameplay);
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }
}
