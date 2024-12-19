import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        Window window = new Window();
        JFrame frame = new JFrame(window.title);
        frame.setSize(window.width, window.height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(window.isResizable);
        //noinspection MagicConstant
        frame.setDefaultCloseOperation(window.closeOperation);

        PacMan pacmanGame = new PacMan();
        frame.add(pacmanGame);
        frame.pack();
        pacmanGame.requestFocus();
        frame.setVisible(true);
    }
}
