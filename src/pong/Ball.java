package pong;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    public double x, y;
    public int width;
    public int height;
    private Color color = Color.WHITE;
    private double XACELERATION = 1;
    private double YACELERATION = new Random().nextGaussian(-1, 1);


    public void tick(Player player, Enemy enemy, Ball ball) {
        y += YACELERATION;
        x += XACELERATION;

        if (x <= 0) {
            enemy.score();
            color = Color.WHITE;
            ball.x = Game.WIDTH / 2;
            ball.y = Game.HEIGHT / 2;
            ball.YACELERATION = new Random().nextInt(-1, 1);
            ball.XACELERATION = 1;
        } else if (x > Game.WIDTH) {
            player.score();
            color = Color.WHITE;
            ball.x = Game.WIDTH / 2;
            ball.y = Game.HEIGHT / 2;
            ball.YACELERATION = new Random().nextDouble(-1.25, 1.25);
            ball.XACELERATION = 1;
        }

        if (y + height > Game.HEIGHT) {

            if (YACELERATION > 0 && YACELERATION < 1.25) {
                YACELERATION += 0.02;
            } else if (YACELERATION < 0 && YACELERATION > -1.25) {
                YACELERATION -= 0.02;
            }

            YACELERATION *= -1;
        } else if (y < 0) {
            if (YACELERATION > 0 && YACELERATION < 1.25) {
                YACELERATION += 0.02;
            } else if (YACELERATION < 0 && YACELERATION > -1.25) {
                YACELERATION -= 0.02;
            }

            YACELERATION *= -1;
        }

//        Rectangle bounds = new Rectangle((int) (x + (x + XACELERATION)), (int) (y + (y + YACELERATION)), width, height);
        Rectangle bounds = new Rectangle((int) x + (int) XACELERATION, (int) y + (int) YACELERATION, width, height);
        Rectangle boundsPlayer = new Rectangle(player.x, player.y, player.width, player.height);
        Rectangle boundsEnemy = new Rectangle((int) enemy.x, (int) enemy.y, enemy.width, enemy.height);


        if (bounds.intersects(boundsEnemy)) {
            color = Color.RED;
            if (XACELERATION < 5) {
                XACELERATION += 0.2;
            }
            if (YACELERATION == 0) {
                YACELERATION++;
            }
            if (YACELERATION < 0 && YACELERATION > -1.25) {
                YACELERATION -= 0.02;
            } else if (YACELERATION > 0 && YACELERATION < 1.25) {
                YACELERATION += 0.02;
            }
            if (ball.x > enemy.x + enemy.getWidth()) {
                System.out.println("bugou");
                YACELERATION *= -1;
                y += YACELERATION;
            }
            XACELERATION *= -1;
        } else if (bounds.intersects(boundsPlayer)) {
            color = Color.BLUE;

            if (XACELERATION < 0 && XACELERATION > -5) {
                XACELERATION -= 0.2;
            }
            if (YACELERATION == 0) {
                YACELERATION--;
            }
            if (YACELERATION > 0 && YACELERATION < 1.25) {
                YACELERATION += 0.02;
            } else if (YACELERATION < 0 && YACELERATION > -1.25) {
                YACELERATION -= 0.02;
            }

            if (player.down || enemy.down) {
                YACELERATION += 0.4;
            } else if (player.up || enemy.up) {
                YACELERATION -= 0.4;
            }

            if (ball.x < player.getWidth()) {
                System.out.println("bugou");
                YACELERATION *= -1;
                y += YACELERATION;
            }
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
        g.setColor(color);
        g.fillRoundRect((int) x, (int) y, width, height,5,5);
        g.setColor(color);
        g.drawLine((int) x + (width / 2), (int) y + (height / 2), (int) ((x + width / 2) - XACELERATION * 5), (int) ((y + height / 2) - YACELERATION * 5));
    }

    public Ball(int width, int height, double x, double y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }


}
