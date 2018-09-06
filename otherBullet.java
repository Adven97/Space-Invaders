package sample;

import java.awt.*;

public class otherBullet extends Bullet{

    double y,x, yVel;

    public otherBullet(double vel, double xx, double yy){
        y=yy;
        x=xx;
        yVel=vel;
    }
    public void draw(Graphics g) {

        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,10,30);
    }


    public void move(){
        y+=yVel;
    }
    public void setPos(double xD, double yD){
        y=yD;
        x=xD;
       // yVel=4;
    }

    public int getY() { return (int)y; }
    public int getX() { return (int)x; }

}
