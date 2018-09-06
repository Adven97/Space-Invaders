package sample;

import java.applet.Applet;
import java.awt.*;

public class Hero2 extends Applet {

    Image pic;
    Game g;
    final int WIDTH = 1800, HEIGHT = 1400;
    final int w = 220, h =20;

    double y,x, xVel;
    boolean leftAc, rightAc;
    final double Grav= 0.75;

    public Hero2()  //konstruktor
    {
        leftAc =false;
        rightAc =false;
        xVel=0;
        y=HEIGHT - 300;
        x=500;
    }

    public void init()
    {

        pic = getImage(getDocumentBase(), "ufo.png");
    }

    public void paint(Graphics g)
    {

        g.drawImage(pic, (int)x, (int)y, this);
    }
    public void move() {
        if(leftAc){
            xVel -= 2.2;
        }
        else if(rightAc){
            xVel += 2.2;
        }
        else if(!leftAc && !rightAc) {
            xVel *= Grav;
        }

        if(xVel >=9)
            xVel=9;
        else if (xVel <= -9)        //ograniczenie predkosci
            xVel = -9;

        if(x > WIDTH-w) {           //granica lewa
            x = WIDTH - w;
        }
        else if(x < 0) {     //granica górna
            x = 0;
        }

        x+=xVel;
    }

    public void setLeftAc(boolean input) {
        leftAc=input;
    }
    public void setRightAc(boolean input) {
        rightAc=input;
    }
}
