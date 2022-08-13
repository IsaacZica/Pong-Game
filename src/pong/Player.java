package pong;

import java.awt.*;

public class Player {

    public boolean up, down;
    public int x, y;
    private int width;
    private int height;

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
    }
}
