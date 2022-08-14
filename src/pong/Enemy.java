package pong;

import java.awt.*;

public class Enemy extends Rectangle{
    public boolean up, down;
    public double x, y;
    public int width;
    public int height;
    private int score;

    public void tick(Ball ball) {
        if (ball.y < this.y+20) {
            this.y -= 0.57;
        }
        if (ball.y > this.y+20) {
            this.y += 0.57;
        }
        if (y + height > Game.HEIGHT) {
            y = Game.HEIGHT - height;
        }
        if (y < 0) {
            y = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public Enemy(double x, double y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x ,(int) y , width, height);
        g.setColor(Color.RED);
        g.drawString("Score: "+score, 185,10);
    }

    public int getEWidth() {
        return width;
    }

    public int getEHeight() {
        return height;
    }

    public void score() {
        score++;
    }
}
