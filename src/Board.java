import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {
    private int[][] grid;
    private int score = 0; // track score
    private Random random;

    public Board() {
        grid = new int[4][4];
        random = new Random();
        addRandomTile(); // first tile
        addRandomTile(); // second tile
    }

    // Add a new tile (2 or 4) at a random empty spot
    private void addRandomTile() {
        int emptyCount = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (grid[i][j] == 0) emptyCount++;

        if (emptyCount == 0) return;

        int index = random.nextInt(emptyCount);
        int value = random.nextInt(10) < 9 ? 2 : 4; // 90% 2, 10% 4

        emptyCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    if (emptyCount == index) {
                        grid[i][j] = value;
                        repaint();
                        return;
                    }
                    emptyCount++;
                }
            }
        }
    }

    // Merge a single line (row or column) for left/up moves
    private int[] merge(int[] line) {
        int[] newLine = new int[4];
        int index = 0;

        // Slide non-zero values to the front
        for (int i = 0; i < 4; i++) {
            if (line[i] != 0) newLine[index++] = line[i];
        }

        // Merge tiles and update score
        for (int i = 0; i < 3; i++) {
            if (newLine[i] != 0 && newLine[i] == newLine[i + 1]) {
                newLine[i] *= 2;
                score += newLine[i];       // add merged value to score
                newLine[i + 1] = 0;
                i++; // skip next tile
            }
        }

        // Slide again after merge
        int[] finalLine = new int[4];
        index = 0;
        for (int i = 0; i < 4; i++) {
            if (newLine[i] != 0) finalLine[index++] = newLine[i];
        }

        return finalLine;
    }

    // Move tiles left
    public void moveLeft() {
        for (int i = 0; i < 4; i++) {
            grid[i] = merge(grid[i]);
        }
        addRandomTile();
        repaint();
    }

    // Move tiles right
    public void moveRight() {
        for (int i = 0; i < 4; i++) {
            int[] reversed = new int[4];
            for (int j = 0; j < 4; j++) reversed[j] = grid[i][3 - j];
            reversed = merge(reversed);
            for (int j = 0; j < 4; j++) grid[i][3 - j] = reversed[j];
        }
        addRandomTile();
        repaint();
    }

    // Move tiles up
    public void moveUp() {
        for (int j = 0; j < 4; j++) {
            int[] col = new int[4];
            for (int i = 0; i < 4; i++) col[i] = grid[i][j];
            col = merge(col);
            for (int i = 0; i < 4; i++) grid[i][j] = col[i];
        }
        addRandomTile();
        repaint();
    }

    // Move tiles down
    public void moveDown() {
        for (int j = 0; j < 4; j++) {
            int[] col = new int[4];
            for (int i = 0; i < 4; i++) col[i] = grid[3 - i][j];
            col = merge(col);
            for (int i = 0; i < 4; i++) grid[3 - i][j] = col[i];
        }
        addRandomTile();
        repaint();
    }

    // Get the current score
    public int getScore() {
        return score;
    }

    // Draw the board
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        int size = getWidth() / 4;

        // Draw tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = j * size;
                int y = i * size;
                int val = grid[i][j];

                g.setColor(val == 0 ? Color.GRAY : Color.ORANGE);
                g.fillRect(x + 5, y + 5, size - 10, size - 10);

                if (val != 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 36));
                    g.drawString(String.valueOf(val), x + size / 2 - 10, y + size / 2 + 12);
                }
            }
        }

        // Draw grid lines
        g.setColor(Color.GRAY);
        for (int i = 1; i < 4; i++) {
            g.drawLine(i * size, 0, i * size, getHeight());
            g.drawLine(0, i * size, getWidth(), i * size);
        }
    }
}
