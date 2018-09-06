package sample;

import java.awt.*;

public class Bullet extends Hero{

    final int WIDTH = 1800, HEIGHT = 1400;
    double y,x, yVel;
    Hero h;

    public Bullet(){
        y=HEIGHT - 100;
        x=WIDTH/2 - 100;
        yVel=0;
    }
    public void draw(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect((int)x+100-12,(int)y-90,15,60);
    }

    public void move() {

    y-=yVel;
    }
    public void fire(Hero p, int fireSpeed) {
        yVel = fireSpeed;
        if (x != p.getX() && y != p.getY()) {
            x = p.getX() + 10;
            y = p.getY() - 10;
        }
    }
    public void hide(){
        y=-19;

    }
    public int getY() {
        return (int)y;
    }
    public int getX() { return (int)x; }
}
