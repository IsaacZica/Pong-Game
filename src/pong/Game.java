package pong;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Game extends Canvas implements Runnable{

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 240;
    private static final int HEIGHT = 120;
    private static final int SCALE = 3;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void run() {

    }

    public void tick() {

    }

    public void render() {

    }

    public synchronized void stop() {

    }
}