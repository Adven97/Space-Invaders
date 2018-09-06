package sample;

import java.awt.*;

public class Hero {

    final int WIDTH = 1800, HEIGHT = 1400;
    final int w = 200, h =50;

    double y,x, xVel,yp;
    boolean leftAc, rightAc;
    final double Grav= 0.75;


    public Hero()
    {
        leftAc =false;
        rightAc =false;
        xVel=0;
        y=HEIGHT -60;
        x=WIDTH/2 - w/2;
        yp=y;
    }

    public void draw(Graphics g) {

        g.setColor(Color.green);
        g.fillRect((int)x,(int)y,w,h);
        g.fillRoundRect((int)x+(w/2)-12,(int)y-30,30,50,20,20);
        g.fillRect((int)x,(int)y-30,10,70);
        g.fillRect((int)x+w-10,(int)y-30,10,70);
    }

    public void move() {
        if(leftAc){
            xVel -= 2.5;
        }
        else if(rightAc){
            xVel += 2.5;
        }
        else if(!leftAc && !rightAc) {
            xVel *= Grav;
        }

        if(xVel >=12.5)
            xVel=12.5;
        else if (xVel <= -12.5)        //ograniczenie predkosci
            xVel = -12.5;

        if(x > WIDTH-w-10) {
            x = WIDTH - w-10;
        }
        else if(x < 10) {
            x = 10;
        }
        x+=xVel;
    }

    public void setLeftAc(boolean input) {
        leftAc=input;
    }
    public void setRightAc(boolean input) {
        rightAc=input;
    }

    public int getY() {
        return (int)y;
    }
    public int getX() { return (int)x; }

}
