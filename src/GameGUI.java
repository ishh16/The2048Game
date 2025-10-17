import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("2048 Game 🧩");
        Board board = new Board();

        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(board, BorderLayout.CENTER);

        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Key listener for arrow keys
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> board.moveLeft();
                    case KeyEvent.VK_RIGHT -> board.moveRight();
                    case KeyEvent.VK_UP -> board.moveUp();
                    case KeyEvent.VK_DOWN -> board.moveDown();
                }
                scoreLabel.setText("Score: " + board.getScore()); // update score
            }
        });
    }
}
