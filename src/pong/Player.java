package pong;

import java.awt.*;

public class Player extends Rectangle{

    public boolean up, down;
    public int x, y;
    public int width;
    public int height;
    private int score;

    public void tick() {
        if (up) {
            y--;
        } else if (down) {
            y++;
        }
        if (y + height > Game.HEIGHT) {
            y = Game.HEIGHT - height;
        }
        if (y < 0) {
            y = 0;
        }
    }

    public Player(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x , y , width, height);
        g.setColor(Color.BLUE);
        g.drawString("Score: "+score, 185,10);
    }

    public void score() {
        score++;
    }

    public int getScore() {
        return score;
    }
}
