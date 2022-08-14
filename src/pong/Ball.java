package pong;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    public double x, y;
    public int width;
    public int height;
    private double XACELERATION = 1;
    private double YACELERATION = new Random().nextGaussian(-1,1);

    public void tick(Player player, Enemy enemy, Ball ball) {
        y += YACELERATION;
        x += XACELERATION;

        if (x <= 0) {
            enemy.score();
            ball.x = Game.WIDTH / 2;
            ball.y = Game.HEIGHT / 2;
            ball.YACELERATION = new Random().nextGaussian(-1,1);
        } else if (x > Game.WIDTH ) {
            player.score();
            ball.x = Game.WIDTH / 2;
            ball.y = Game.HEIGHT / 2;
            ball.YACELERATION = new Random().nextGaussian(-1,1);
        }

        if (y + height > Game.HEIGHT) {
            YACELERATION *= -1;
        } else if (y < 0) {
            YACELERATION *= -1;
        }

//        Rectangle bounds = new Rectangle((int) (x + (x + XACELERATION)), (int) (y + (y + YACELERATION)), width, height);
        Rectangle bounds = new Rectangle((int) x, (int) y, width, height);
        Rectangle boundsPlayer = new Rectangle(player.x, player.y, player.width, player.height);
        Rectangle boundsEnemy = new Rectangle((int) enemy.x,(int) enemy.y, enemy.width, enemy.height);

        System.out.println(bounds.x + " - " + bounds.y + " - " + bounds.height + " - " + bounds.width);
        System.out.println(x + " - " + y + " - " + height + " - " + width);


        if (bounds.intersects(boundsEnemy)) {
            System.out.println("colidiu");
            XACELERATION *= -1;
        } else if (bounds.intersects(boundsPlayer)) {
            System.out.println("colidiu");
            XACELERATION *= -1;
        }
    }

    public double getXACELERATION() {
        return XACELERATION;
    }

    public double getYACELERATION() {
        return YACELERATION;
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y, width, height);
    }

    public Ball(int width, int height, double x, double y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
}
