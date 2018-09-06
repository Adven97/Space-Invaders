package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.applet.*;
import java.util.Random;

public class Game extends Applet implements Runnable, KeyListener{

    final int WIDTH = 1800, HEIGHT = 1500;
    int r,c;
    int enemies;

    Font myFont = new Font ("Pixel LCD7",Font.BOLD, 35);
    Font myFont2 = new Font ("Press Start 2P",Font.BOLD, 55);

    int points,lives,level, vel,temp, fireSpeed,fsTmp;
    int vSpeed=18;
    int fSpeed=11;
    boolean ifFire=false;

    boolean gamestart;
    boolean hideLasers;
    boolean triumphMusic;

    boolean upperDot;
    boolean lowerDot;

    boolean pause;
    boolean win;

    boolean endGame;

    boolean ArcadeGamestart;
    int map[][];
    int bossHp;
    Hero hero;
    Bullet b;        ////hero's bullet
    otherBullet en;   ///// enemy's bullets
    otherBullet en2;
    otherBullet en3;
    drawEnemy enemy;
    PowerUp powerUp;
    saveScoreToFile s;

    Graphics gfx;
    Image img;
    Image pic;
    Image heart;
    AudioClip music;
    AudioClip laser;

    Random rand=new Random();
    Thread t;

    public void init()
    {
        bossHp=10;
        ArcadeGamestart=false;
        upperDot=true;
        lowerDot=false;

        r=5;
        c=11;
        pic=getImage(getDocumentBase(),"inv.gif");
        heart=getImage(getDocumentBase(),"heart.png");
        music=getAudioClip(getDocumentBase(),"blusky.wav");
        laser=getAudioClip(getDocumentBase(),"laser.wav");

        music.play();
        this.resize(WIDTH,HEIGHT);
        this.addKeyListener(this);
        img = createImage(WIDTH,HEIGHT);
        gfx = img.getGraphics();
        vel=18;
        points=0;
        lives=3;
        level=1;
    //    fsTmp=fSpeed;
        fireSpeed=fSpeed;
        temp=0;
        enemies=0;
        gamestart=false;

        hideLasers=false;
        triumphMusic=false;

        pause=false;
        hero= new Hero();
        b=new Bullet();

        en=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,60);
        en2=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-20);
        en3=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-100);
        enemy=new drawEnemy(r,c);
        powerUp = new PowerUp();
        s = new saveScoreToFile();


        t = new Thread(this);
        t.start();
}
    public void initNextLevel(int rows, int cols, String picName, String musicName, int velSpeed, int fs, int lvl, int scr,boolean gs){
        endGame=true;
        r=rows;
        c=cols;
        hideLasers=false;
       // pic=getImage(getDocumentBase(),"cat2.gif");
        pic=getImage(getDocumentBase(),picName);
        music.stop();
        music=getAudioClip(getDocumentBase(),musicName);
        music.play();
        vel=velSpeed;

        level=lvl;
        points=scr;

        lives=3;
        fsTmp=fs;
        fireSpeed=fsTmp;
        enemies=r*c;
        gamestart=gs;

        hero= new Hero();
        //b=new Bullet();
        powerUp = new PowerUp();
        en=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,60);
        enemy=new drawEnemy(r,c);
    }
    public void checkGameState(){

        if(enemies==0 && level==1 && !ArcadeGamestart){

            hideLasers=true;
            hero.y -=8;
            if(hero.y <-30){
                //  level++;
                initNextLevel(5,11,"catt.gif","takeon.wav",21,fSpeed+1,2,points,false);
                gamestart=false;
            }
        }
        if(enemies==0 && level==1 && ArcadeGamestart){

            hideLasers=true;
            hero.y -=8;
            if(hero.y <-30){
                vSpeed++;
                fSpeed++;
                initNextLevel(5,11,getRandomEnemy(),getRandomMusic(),vSpeed,fSpeed+1,1,points,true);

            }
        }
        if(enemies==0 && level==2){
            hideLasers=true;
            hero.y -=9;
            if(hero.y <-30) {
                level++;
                //  init3();
                initNextLevel(6,11,"cat2.gif","danger.wav",24,fSpeed+3,3,points,false);
                gamestart = false;
            }
        }

        if(enemies==0 && level==3){
            hideLasers=true;
            hero.y -=9;
            if(hero.y <-30) {
                level++;
                //  init3();
                initNextLevel(7,10,"cat3.gif","guard.wav",28,fSpeed+4,4,points,false);
                gamestart = false;
            }
        }
        if(enemies==0 && level==4){
            hideLasers=true;
            hero.y -=10;
            if(hero.y <-30) {
                //   level++;
                initNextLevel(1,4,"luigi.gif","alive.wav",35,fSpeed+5,5,points,false);
                gamestart = false;
            }
        }
        if(enemies==0 && level==5){   ////////////////won

            hideLasers=true;
            hero.y-=4;
            if(hero.y <-30) {
                temp = points;
                gamestart = false;
                win = true;
                //  init0();
                initNextLevel(5,11,"inv.gif","rick.wav",19,fSpeed+1,1,0,false);
            }
        }

        if(lives==0){
            temp=points;
            gamestart=false;
            level=1;
            win=false;
            //   init0();
            initNextLevel(5,11,"inv.gif","rick.wav",19,fSpeed,1,0,false);
        }

        if(!gamestart && level==1 ){
            setStartScreen("",540);
            // gfx.drawString("SPACE INVADERS",580,250);
            gfx.drawString("ADVENTURE MODE",600,550);
            gfx.drawString("ARCADE MODE",600,650);

            gfx.setColor(Color.blue);
            if(upperDot) {
                gfx.fillOval(500, 500, 50, 50);
            }
            if(lowerDot) {
                gfx.fillOval(500, 600, 50, 50);
            }

            if (endGame)
            {
                if(!ArcadeGamestart) {
                    saveAndLoadScore("BestAdventureScore.txt");
                }
                if(ArcadeGamestart){
                    saveAndLoadScore("BestArcadeScore.txt");
                }

                gfx.drawString("The Best Score: "+s.scr,320,1100);
                gfx.setColor(new Color(148,0,211));
                gfx.drawString("YOUR SCORE: "+temp,510,950);

                if(win){
                    gfx.setColor(Color.yellow);
                    gfx.drawString("YOU WON!",660,850);
                }
                else {
                    gfx.setColor(Color.red);
                    gfx.drawString("YOU LOSE!", 660, 850);
                }
            }
        }
        if(!gamestart && level==2 ){
            setStartScreen("LEVEL 2",280);
        }
        if(!gamestart && level==3 ){
            setStartScreen("LEVEL 3",280);
        }
        if(!gamestart && level==4 ){
            setStartScreen("LEVEL 4",280);
        }
        if(!gamestart && level==5 ){
            setStartScreen("FINAL LEVEL ",140);
        }
    }
    public void setStartScreen(String str, int xP){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0,0,WIDTH,HEIGHT);
        gfx.setColor(Color.green);
        gfx.setFont(myFont2);
        gfx.drawString("PRESS ENTER TO START",360,330);
        gfx.drawString("SPAJS INWEJDERS "+str,xP,250);
    }

    public void saveAndLoadScore(String fileName){
        s.load(fileName);
        if(s.scr < temp) {
            s.save(this, fileName);
        }
    }
    public String getRandomEnemy(){

        String[] names = {
                "catt.gif", "cat2.gif", "cat3.gif","coin.gif","shrek.jpg", "mus.png","mario.png","dino.png","pikacu.png",
                "pacman.png","brick.png","megaman.png","zelda.jpg","ironman.png","ghostb.png","et.jpg","rob.png"
        };
        Random r = new Random();
        int i = r.nextInt(names.length-1);
        String enem = names[i];
        return  enem;
    }
    public String getRandomMusic(){

        String[] names = {
                "takeon.wav", "danger.wav", "guard.wav","blusky.wav","suf.wav",
                "rick.wav","thriler.wav","ghost.wav","alive.wav"
        };
        Random r = new Random();
        int i = r.nextInt(names.length-1);
        String enem = names[i];
        return  enem;
    }

    public void ufoHit(int j, int i){

        if(level<5) {
            enemy.setBrickValue(0, j, i);
            if (level==1) {
                points += 5;
                if(ArcadeGamestart){
                    points+=5;
                }
            }
            if (level==2) {
                points += 10;
            }
            if (level==3) {
                points += 15;
            }
            if (level==4) {
                points += 20;
            }

            enemies--;
            ifFire = false;
            b.hide();
        }

        else{
            bossHp--;
            points+=25;
            if(bossHp==0) {
                enemy.setBrickValue(0, j, i);
                enemies--;
                if(enemies !=0){
                    bossHp=10;
                }
            }
            ifFire = false;
            b.hide();
        }
    }

    public void drawUfo(int r, int c,int xPosition, int yPosition, int wid, int hei, int interspX, int interspY){

        int xpos=xPosition;
        int ypos=yPosition;
        map =new int[r][c];
        gfx.setColor(Color.black);
        for(int j=0;j<r;j++) {
            for (int i = 0; i < c; i++) {

                if (enemy.map[j][i] > 0) {
                    Rectangle rect = new Rectangle(xpos, ypos, wid, hei);
                    Rectangle ballRect = new Rectangle(b.getX() + 88, b.getY() - 90, 15, 60);

                    Rectangle brickRect = rect;

                    if (brickRect.getBounds().intersects(ballRect)) {
                        ufoHit(j,i);
                    }

                    gfx.drawImage(pic, xpos, ypos, wid, hei, this);
                }
                xpos += interspX;
            }
            ypos += interspY;
            xpos=xPosition;
        }
    }

    public void RespawnOtherBullet(otherBullet en, double xx, double yy) {
        if (en.getY() > 1600) {

            en.setPos(xx,yy);
        }

        if (en.getY() >= HEIGHT - 200 && en.getY() <= HEIGHT - 100) {
            if (en.getX() >= hero.getX() && en.getX() <= hero.getX() + 200) {
                //en.op(100 + (1600 - 100) * r.nextDouble() , 150 + (800 - 150) * r.nextDouble() );
                en.setPos(xx,yy);
                lives--;
                if(points >50) {
                    points -= 10;
                }
            }
        }
    }
    public void RespawnPu() {
        if (enemies % 17 == 0) {
            powerUp.showPu(100 + (1600 - 100) * rand.nextDouble(), -100);
        }
        if (powerUp.getY() >= HEIGHT - 200 && powerUp.getY() <= HEIGHT - 100) {
            if (powerUp.getX() >= hero.getX() && powerUp.getX() <= hero.getX() + 200) {
                powerUp.hide();
                if(lives<3) {
                    lives++;
                    fireSpeed=fSpeed;
                }
                else{
                    fireSpeed +=9;
                }
                points += 50;
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
    void drawStars(){
        gfx.setColor(Color.yellow);
        for(int i=0;i<25;i++)
            gfx.fillOval(rand.nextInt(WIDTH)+60,rand.nextInt(HEIGHT)+60,5,5);
    }

    public void paint(Graphics g)
    {

        gfx.setColor(Color.BLACK);          ///tło
        gfx.fillRect(0,0,WIDTH,HEIGHT);
        gfx.setColor(Color.white);

        if(!pause) {
            drawStars();
        }

        if(level==1) {
            en.draw(gfx);
            if(ArcadeGamestart) {
                en2.draw(gfx);
                en3.draw(gfx);
            }
            drawUfo(r,c,90,75,100,70,150,120);
        }
        if(level==2) {
            en.draw(gfx);
            drawUfo(r,c,90,75,100,70,150,120);
        }

        if(level==3) {
            en.draw(gfx);
            drawUfo(r,c,70,75,80,60,160,120);
        }
        if(level==4) {
            en.draw(gfx);
            drawUfo(r,c,90,75,60,50,170,120);
        }
        if(level==5){               //////boss level
            en.draw(gfx);
            drawUfo(r,c,50,30,400,600,450,1);

        }

        hero.draw(gfx);

      if(ifFire) {
          b.draw(gfx);
      }
      if(b.getY()<0) {
          ifFire = false;
      }
      powerUp.draw(gfx);

        gfx.setColor(Color.blue);                             ////obramowanie
        gfx.fillRect(0,0,WIDTH,75);

        gfx.setColor(Color.black);
        gfx.setFont(myFont);
        gfx.drawString("Points: "+points,60,50);
      //  gfx.drawString(""+vel,1750,50);
 //       gfx.drawString("ycor: "+powerUp.getY(),1500,50);
        if(pause) {
            gfx.drawString("Pause", 1150, 50);
        }
        else {
            gfx.drawString("Press P to pause", 800, 50);
        }
        if(ArcadeGamestart){
            gfx.drawString("ARCADE", 450, 50);
        }


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

        if(!hideLasers) {
            if(!ArcadeGamestart) {
                RespawnOtherBullet(en, hero.getX() + ((hero.getX() + 200) - (hero.getX())) * rand.nextDouble(), 60);
            }
            if(ArcadeGamestart){
                RespawnOtherBullet(en,550 + (1000) * rand.nextDouble() , 60);
                RespawnOtherBullet(en2,90 + (650) * rand.nextDouble() , -30);
                RespawnOtherBullet(en3,700 + (1500) * rand.nextDouble() , -50);
            }
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
                    enemy.movee();
                    powerUp.move();
                    if(ArcadeGamestart){
                        en2.move();
                        en3.move();
                    }
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
            if(!ifFire && gamestart) {
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
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!lowerDot) {
                lowerDot=true;
                upperDot=false;
                if (!ArcadeGamestart && !gamestart) {
                    ArcadeGamestart = true;
                }
            }
        }

        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            if (!upperDot) {
                lowerDot=false;
                upperDot=true;
                if (ArcadeGamestart && !gamestart) {
                    ArcadeGamestart = false;
                }
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
    public void keyTyped(KeyEvent e) {

    }

}
