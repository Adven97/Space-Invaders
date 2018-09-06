package sample;

import java.awt.*;

public class PowerUp extends Bullet {

    final int WIDTH = 1800, HEIGHT = 1400;
    double y,x, yVel;

    public PowerUp(){
        y=-400;
        x=WIDTH/2;
        yVel=0;
    }
    public void draw(Graphics g) {

        g.setColor(new Color(148,0,211));
        g.fillOval((int)x,(int)y,30,30 );

    }
    public void move(){
        y+=yVel;
    }
    public void showPu(double xD, double yD){
        y=yD;
        x=xD;
        yVel=5;
    }

    public void hide(){
        y=-150;
        yVel=0;
    }

    public int getY() { return (int)y; }
    public int getX() { return (int)x; }

}

