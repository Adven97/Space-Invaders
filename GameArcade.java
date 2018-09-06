package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.applet.*;
import java.util.Random;

public class GameArcade extends Applet implements Runnable, KeyListener {

    final int WIDTH = 1800, HEIGHT = 1500;
    int r=5,c=11;
    int enemies;
    Font myFont = new Font ("Monospaced",Font.BOLD, 40);
    Font myFont2 = new Font ("Monospaced",Font.BOLD, 80);
    Thread t;
    int i,j,points,lives,level, vel,temp, fireSpeed;
    boolean ifFire=false;
    int vSpeed=18;

    boolean gamestart;
    boolean pause;
    int map[][];
    int bossHp=6;
    Hero hero;
    Bullet b;
    otherBullet en;
    otherBullet en2;
    otherBullet en3;
    drawEnemy enemy;
    PowerUp powerUp;

    Graphics gfx;
    Image img;
    Image pic;
    Image coin;
    Image heart;
    AudioClip music;
    AudioClip laser;

    public void init()
    {
        pic=getImage(getDocumentBase(),"catt.gif");
        coin=getImage(getDocumentBase(),"coin.gif");
        System.out.println(getDocumentBase());
        heart=getImage(getDocumentBase(),"heart.png");
        //pic=getImage(getDocumentBase(),"catt.gif");
        music=getAudioClip(getDocumentBase(),"takeon.wav");
        laser=getAudioClip(getDocumentBase(),"laser.wav");
        music.loop();
        this.resize(WIDTH,HEIGHT);
        this.addKeyListener(this);
        img = createImage(WIDTH,HEIGHT);
        gfx = img.getGraphics();
        vel=vSpeed;
        points=0;
        lives=3;
        level=1;
        fireSpeed=10;
        temp=0;
        enemies=r*c;
        gamestart=false;
        pause=false;
        hero= new Hero();
        b=new Bullet();
        Random rand=new Random();
        // Random rand2=new Random();
        //  Random rand3=new Random();
        en=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,60);
        en2=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-20);
        en3=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-100);
        enemy=new drawEnemy(r,c);
        powerUp = new PowerUp();

        t = new Thread(this);
        t.start();
    }

    public void init0(int vSpeed)
    {
        pic=getImage(getDocumentBase(),"catt.gif");
        // music.stop();
        // music=getAudioClip(getDocumentBase(),"takeon.wav");
        // music.play();
        this.resize(WIDTH,HEIGHT);
        this.addKeyListener(this);
        img = createImage(WIDTH,HEIGHT);
        gfx = img.getGraphics();
        vel=vSpeed;
        //   points=0;
        lives=3;
        level=1;
        fireSpeed=10;
        enemies=r*c;
        //gamestart=false;
        hero= new Hero();
        b=new Bullet();
        Random rand=new Random();
        en=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-140);
        en2=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-220);
        en3=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-300);
        enemy=new drawEnemy(r,c);
    }

    public void ufoHit(int j, int i){

        enemy.setBrickValue(0, j, i);
        points += 10;
        enemies--;
        ifFire = false;
        b.hide();
    }

    public void drawUfo(int r, int c,int xPosition, int yPosition, int wid, int hei){

        int xpos=xPosition;
        int ypos=yPosition;
        map =new int[r][c];
        gfx.setColor(Color.black);
        for(int j=0;j<r;j++) {
            for (int i = 0; i < c; i++) {

                if (enemy.map[j][i] > 0) {
                    // gfx.setColor(Color.green);
                    Rectangle rect = new Rectangle(xpos, ypos, wid, hei);   //dobre ustawienie dla 3x7
                    // gfx.fillRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
                    // gfx.setColor(Color.orange);
                    Rectangle ballRect = new Rectangle(b.getX() + 88, b.getY() - 90, 15, 60);
                    //gfx.fillRect((int) ballRect.getX(), (int) ballRect.getY(), (int) ballRect.getWidth(), (int) ballRect.getHeight());
                    Rectangle brickRect = rect;

                    if (brickRect.getBounds().intersects(ballRect)) {
                        ufoHit(j,i);
                    }
                    gfx.drawImage(pic, xpos, ypos, wid, hei, this);
                }
                xpos += 150;
            }
            ypos += 120;
            xpos=90;
        }
    }

    public void RespawnOtherBullet(otherBullet en, double xx, double yy) {
        Random r = new Random();
        if (en.getY() > 1600) {
            // en.op(100 + (1600 - 100) * r.nextDouble() , 150 + (800 - 150) * r.nextDouble() );
            en.setPos(xx,yy);
        }

        if (en.getY() >= HEIGHT - 200 && en.getY() <= HEIGHT - 100) {
            if (en.getX() >= hero.getX() && en.getX() <= hero.getX() + 200) {
                //en.op(100 + (1600 - 100) * r.nextDouble() , 150 + (800 - 150) * r.nextDouble() );
                en.setPos(xx,yy);
                lives--;
                points -= 10;
            }
        }
    }

    public void RespawnPu() {
        Random r = new Random();
        if (enemies % 16 == 0) {
            powerUp.showPu(100 + (1600 - 100) * r.nextDouble(), -100);
        }
        if (powerUp.getY() >= HEIGHT - 200 && en.getY() <= HEIGHT - 100) {
            if (powerUp.getX() >= hero.getX() && powerUp.getX() <= hero.getX() + 200) {
                powerUp.hide();
                if(lives<3) {
                    lives++;
                    fireSpeed=10;
                }
                else{
                    fireSpeed +=12;
                }
                points += 25;
            }
        }
    }

    public void setStartScreen(){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0,0,WIDTH,HEIGHT);
        gfx.setColor(Color.green);
        gfx.setFont(myFont2);
        gfx.drawString("PRESS ENTER TO START",420,330);
    }

    public void checkGameState(){
        //int v=18;
        if(enemies==0 && level==1){
            // level++;
            vSpeed+=2;
            init0(vSpeed);
            //gamestart=false;
        }

        if(lives==0){
            temp=points;
            points=0;
            gamestart=false;
            level=1;
            init0(vSpeed);
        }
        if(!gamestart && level==1 ){
            setStartScreen();
            gfx.drawString("SPACE INVADERS",580,250);
            if (temp !=0)
            {
                gfx.drawString("YOUR SCORE: "+temp,540,750);
            }
        }
    }

    public void drawHearts(int ile){
        int x=1600;
        for(int i=0;i<ile;i++){
            gfx.drawImage(heart,x,7,70,70,this);
            x-=100;
        }
    }
    public void drawStars() {
        Random rand = new Random();
        for (int i = 0; i < 25; i++)
            gfx.fillOval(rand.nextInt(WIDTH) + 60, rand.nextInt(HEIGHT) + 60, 5, 5);    ///gwiazdki
    }
    public void paint(Graphics g)
    {
        gfx.setColor(Color.BLACK);          ///tło
        gfx.fillRect(0,0,WIDTH,HEIGHT);
        gfx.setColor(Color.white);
        if(!pause) {
            drawStars();
        }

        en.draw(gfx);
        en2.draw(gfx);
        en3.draw(gfx);
        drawUfo(r,c,90,75,100,70);
        Random rand = new Random();
        hero.draw(gfx);
        //RespawnOtherBullet(en,hero.getX() + ((hero.getX() + 200) - (hero.getX())) * rand.nextDouble() , 60 );
        RespawnOtherBullet(en,550 + (1000) * rand.nextDouble() , 60 );
        RespawnOtherBullet(en2,90 + (650) * rand.nextDouble() , -30);
        RespawnOtherBullet(en3,700 + (1500) * rand.nextDouble() , -50);
        //enemy.draw(gfx);

        if(ifFire) {
            b.draw(gfx);
        }
        if(b.getY()<0) {
            ifFire = false;
        }
       // powerUp.draw(gfx,coin,this);

        gfx.setColor(Color.blue);                             ////obramowanie
        gfx.fillRect(0,0,10,HEIGHT);
        gfx.fillRect(0,0,WIDTH,75);
        gfx.fillRect(WIDTH-10,0,10,HEIGHT);
        gfx.fillRect(0,HEIGHT-10,WIDTH,10);

        gfx.setColor(Color.black);
        gfx.setFont(myFont);
        gfx.drawString("Points: "+points,60,50);
        gfx.drawString("Vel: "+vel,300,50);
        //       gfx.drawString("ycor: "+powerUp.getY(),1500,50);
        if(pause)
            gfx.drawString("Pause",1150,50);

        gfx.setColor(Color.red);
        if(lives==3) {
            drawHearts(3);
        }
        else if(lives==2) {
            drawHearts(2);
        }
        else if(lives==1) {
            drawHearts(1);
        }
        RespawnPu();
        checkGameState();

        g.drawImage(img,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void run() {

        for(;;) {
            if(gamestart) {
                if(!pause) {
                    hero.move();
                    b.move();
                    en.move();
                    en2.move();
                    en3.move();
                    powerUp.move();
                }
            }

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException w) {
                w.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            hero.setLeftAc(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            hero.setRightAc(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(!ifFire) {
                ifFire = true;
                b.fire(hero,fireSpeed);
                laser.play();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!gamestart) {
                gamestart = true;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_P)
        {
            if(!pause) {
                pause= true;
            }
            else if(pause) {
                pause= false;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            hero.setLeftAc(false);
        }

        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            hero.setRightAc(false);
        }

    }
    public void keyTyped(KeyEvent e){}

}