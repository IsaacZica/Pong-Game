package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable, KeyListener {

    @Serial
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 120;
    private static final int SCALE = 3;
    public Random rnd;
    private boolean isRunning;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public Enemy enemy;
    public Player player;
    public Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        isRunning = true;
        this.addKeyListener(this);
        rnd = new Random();
        player = new Player(0, HEIGHT / 2 - 20, 5, 40);
        enemy = new Enemy(235, HEIGHT / 2 - 20, 5, 40);
        ball = new Ball(5, 5, WIDTH / 2, HEIGHT / 2);
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(game);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

        new Thread(game).start();


    }

    public void tick() {
        player.tick();
        enemy.tick(ball);
        ball.tick(player, enemy, ball);

        if (player.getScore() == 5) {
            BufferStrategy bs = this.getBufferStrategy();
            if (bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            g.setColor(Color.BLUE);
            g.setFont(new Font("arial", Font.BOLD, 80));
            g.drawString("You Won!",190,100);
            bs.show();
            stop();
        } else if (enemy.getScore() == 5) {
            BufferStrategy bs = this.getBufferStrategy();
            if (bs == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            g.setColor(Color.RED);
            g.setFont(new Font("arial", Font.BOLD, 80));
            g.drawString("You Lost!",190,100);
            bs.show();
            stop();
        }

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        player.render(g);
        enemy.render(g);
        ball.render(g);
        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        //int frames = 0;
        //double timer = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                //frames++;
                delta--;
            }
            /*if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS "+ frames);
                frames = 0;
                timer = System.currentTimeMillis();
            }*/
        }
        stop();
    }

    public synchronized void stop() {
        isRunning = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}