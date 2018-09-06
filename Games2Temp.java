package sample;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Games2Temp {


   // public class Game extends Applet implements Runnable, KeyListener, ActionListener {
/*      final int WIDTH = 1800, HEIGHT = 1500;
        int r,c;
        int enemies;
        Font myFont = new Font ("Monospaced",Font.BOLD, 40);
        Font myFont2 = new Font ("Monospaced",Font.BOLD, 80);
        Thread t;
        int i,j,points,lives,level, vel,temp, fireSpeed,fsTmp;
        boolean ifFire=false;
        int vSpeed;
        boolean gamestart;

        boolean ArcadeGamestart;
        GameArcade arcade;

        boolean pause;
        boolean win;
        boolean endGame;
        int map[][];
        int bossHp=6;
        Hero hero;
        Bullet b;
        otherBullet en;
        drawEnemy enemy;
        PowerUp powerUp;
        saveScoreToFile s;
        TextField tf;
        TextField tf2;
        Button but;

        Graphics gfx;
        Image img;
        Image pic;
        Image heart;
        Image coin;
        AudioClip music;
        AudioClip laser;

        otherBullet en1;
        otherBullet en2;
        otherBullet en3;

        public void init()
        {
            r=5;
            c=11;
            // pic=getImage(getDocumentBase(),"darek.png");
            pic=getImage(getDocumentBase(),"catt.gif");
            heart=getImage(getDocumentBase(),"heart.png");
            coin=getImage(getDocumentBase(),"coin.gif");
            music=getAudioClip(getDocumentBase(),"blusky.wav");
            laser=getAudioClip(getDocumentBase(),"laser.wav");
            music.play();
            this.resize(WIDTH,HEIGHT);
            this.addKeyListener(this);
            img = createImage(WIDTH,HEIGHT);
            gfx = img.getGraphics();
            vel=18+2;
            points=0;
            lives=3;
            level=1;
            fsTmp=10;
            fireSpeed=fsTmp;
            temp=0;
            enemies=r*c;
            gamestart=false;

            ArcadeGamestart=false;


            pause=false;
            hero= new Hero();
            b=new Bullet();
            Random rand=new Random();
            en=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,60);
            enemy=new drawEnemy(r,c);
            powerUp = new PowerUp();
            s = new saveScoreToFile();


            t = new Thread(this);
            t.start();
        }
        public void init2(){
            r=6;
            c=11;
            // pic=getImage(getDocumentBase(),"cat2.gif");
            pic=getImage(getDocumentBase(),"cat2.gif");
            music.stop();
            music=getAudioClip(getDocumentBase(),"takeon.wav");
            music.play();
            vel=22+3;
            lives=3;
            fsTmp=12;
            fireSpeed=fsTmp;
            enemies=r*c;
            gamestart=false;

            hero= new Hero();
            b=new Bullet();
            //en=new otherBullet(vel);
            enemy=new drawEnemy(r,c);
        }
        public void init3(){
            r=7;
            c=10;
            pic=getImage(getDocumentBase(),"cat3.gif");
            //   pic=getImage(getDocumentBase(),"cat3.gif");
            music.stop();
            music=getAudioClip(getDocumentBase(),"guard.wav");
            music.play();
            vel=26+4;
            lives=3;
            fsTmp=14;
            fireSpeed=fsTmp;
            enemies=r*c;
            gamestart=false;

            hero= new Hero();
            b=new Bullet();
            //  en=new otherBullet(vel);
            enemy=new drawEnemy(r,c);
        }
        public void init4(){
            //pic=getImage(getDocumentBase(),"testoxD.gif");
            pic=getImage(getDocumentBase(),"inv.gif");
            music.stop();
            music=getAudioClip(getDocumentBase(),"final.wav");
            music.play();
            vel=30+2;
            lives=3;
            fsTmp=16;
            fireSpeed=fsTmp;
            enemies=1;
            //bossHp=6;
            gamestart=false;

            hero= new Hero();
            b=new Bullet();
            //  en=new otherBullet(vel);
            enemy=new drawEnemy(1,1);
        }
        public void init0()
        {
            pic=getImage(getDocumentBase(),"catt.gif");
            music.stop();
            music=getAudioClip(getDocumentBase(),"blusky.wav");
            music.play();
            this.resize(WIDTH,HEIGHT);
            this.addKeyListener(this);
            img = createImage(WIDTH,HEIGHT);
            gfx = img.getGraphics();
            vel=18+2;
            points=0;
            lives=3;
            level=1;
            fireSpeed=10;
            enemies=r*c;
            gamestart=false;

            hero= new Hero();
            b=new Bullet();
            Random rand=new Random();
            en=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,60);
            enemy=new drawEnemy(r,c);
        }
        public void initArcadeMode(int vSpeed)
        {

            r=6;
            c=11;
            pic=getImage(getDocumentBase(),"cat3.gif");
            // music.stop();
            // music=getAudioClip(getDocumentBase(),"takeon.wav");
            // music.play();
            this.resize(WIDTH,HEIGHT);
            this.addKeyListener(this);
            img = createImage(WIDTH,HEIGHT);
            gfx = img.getGraphics();
            vSpeed=18;
            vel=vSpeed;
            //   points=0;
            lives=3;
            level=10;
            fireSpeed=10;
            enemies=r*c;
            gamestart=false;
            hero= new Hero();
            b=new Bullet();
            Random rand=new Random();
            en1=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-140);
            en2=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-220);
            en3=new otherBullet(vel,100 + (1600 - 100) * rand.nextDouble() ,-300);
            enemy=new drawEnemy(r,c);
        }

        public void ufoHit(int j, int i){

            if(level<4) {
                enemy.setBrickValue(0, j, i);
                if(level==1) {
                    points += 5;
                }
                if(level==2) {
                    points += 10;
                }
                if(level==3) {
                    points += 20;
                }
                enemies--;
                ifFire = false;
                b.hide();
            }
            if (level==10){
                enemy.setBrickValue(0, j, i);
                points += 10;
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
                    xpos += interspX;
                }
                ypos += interspY;
                xpos=xPosition;
            }
        }

        public void RespawnOtherBullet(otherBullet en, double xx, double yy) {
            Random r = new Random();
            if (en.getY() > 1600) {
                // en.op(100 + (1600 - 100) * r.nextDouble() , 150 + (800 - 150) * r.nextDouble() );
                en.op(xx,yy);
            }

            if (en.getY() >= HEIGHT - 200 && en.getY() <= HEIGHT - 100) {
                if (en.getX() >= hero.getX() && en.getX() <= hero.getX() + 200) {
                    //en.op(100 + (1600 - 100) * r.nextDouble() , 150 + (800 - 150) * r.nextDouble() );
                    en.op(xx,yy);
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
                        fireSpeed=fsTmp;
                    }
                    else{
                        fireSpeed +=10;
                    }
                    points += 50;
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
            if(enemies==0 && level==1){
                level++;
                init2();
                gamestart=false;
            }
            if(enemies==0 && level==2){
                level++;
                init3();
                gamestart=false;
            }
            if(enemies==0 && level==3){
                level++;
                init4();
                gamestart=false;
            }
            if(enemies==0 && level==4){
                temp=points;
                gamestart=false;
                win=true;
                init0();
            }

            if(lives==0){
                temp=points;
                gamestart=false;
                level=1;
                win=false;
                init0();
            }
            if(!gamestart && level==1 ){
                setStartScreen();
                gfx.drawString("SPACE INVADERS",580,250);
                if (temp !=0)
                {
                    gfx.drawString("YOUR SCORE: "+temp,540,750);

                    saveScore();

                    //gfx.drawString("YOU LOSE!",660,650);
                    if(win){
                        gfx.drawString("YOU WON!",660,650);
                    }
                    else
                        gfx.drawString("YOU LOSE!",660,650);
                }
            }

            if(!gamestart && level==2 ){
                setStartScreen();
                gfx.drawString("SPACE INVADERS LEVEL 2",390,250);
            }
            if(!gamestart && level==3 ){
                setStartScreen();
                gfx.drawString("SPACE INVADERS LEVEL 3",390,250);
            }
            if(!gamestart && level==4 ){
                setStartScreen();
                gfx.drawString("SPACE INVADERS FINAL LEVEL",220,250);
            }

        }
        public void checkArcadeGameState(){
            //int v=18;
            if(enemies==0 && level==10){
                // level++;
                vSpeed+=2;
                initArcadeMode(vSpeed);
                //gamestart=false;
            }

            if(lives==0){
                temp=points;
                points=0;
                ArcadeGamestart=false;
                level=1;
                initArcadeMode(vSpeed);
            }
            if(!ArcadeGamestart&& level==10 ){
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
            if(ArcadeGamestart){
                initArcadeMode(18);

                gfx.setColor(Color.BLACK);          ///tło
                gfx.fillRect(0,0,WIDTH,HEIGHT);
                gfx.setColor(Color.white);
                if(!pause) {
                    drawStars();
                }

                en.draw(gfx);
                en2.draw(gfx);
                en3.draw(gfx);
                drawUfo(r,c,90,75,100,70,150,120);
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
                //   RespawnPu();
                checkArcadeGameState();

                g.drawImage(img,0,0,this);

            }


            else {


                gfx.setColor(Color.BLACK);          ///tło
                gfx.fillRect(0, 0, WIDTH, HEIGHT);
                gfx.setColor(Color.white);

                drawStars();    ///gwiazdki

                if (level == 1) {
                    en.draw(gfx);
                    //enemy.draw(this,pic,gfx,b,r,c,90,75,100,70,150,120);
                    drawUfo(r, c, 90, 75, 100, 70, 150, 120);
                }
                if (level == 2) {
                    en.draw(gfx);
                    drawUfo(r, c, 70, 75, 80, 60, 160, 120);
                }
                if (level == 3) {
                    en.draw(gfx);
                    drawUfo(r, c, 90, 75, 60, 50, 170, 120);
                }
                if (level == 4) {
                    en.draw2(gfx);
                    drawUfo(1, 1, 500, 100, 700, 450, 1, 1);
                    //drawUfo(1,11,90,600,100,70);
                }
                Random rand = new Random();
                hero.draw(gfx);
                //enemy.draw(gfx);

                if (ifFire) {
                    b.draw(gfx);
                }
                if (b.getY() < 0) {
                    ifFire = false;
                }
                powerUp.draw(gfx, coin, this);

                gfx.setColor(Color.blue);                             ////obramowanie
                gfx.fillRect(0, 0, 10, HEIGHT);
                gfx.fillRect(0, 0, WIDTH, 75);
                gfx.fillRect(WIDTH - 10, 0, 10, HEIGHT);
                gfx.fillRect(0, HEIGHT - 10, WIDTH, 10);

                gfx.setColor(Color.black);
                gfx.setFont(myFont);
                gfx.drawString("Points: " + points, 60, 50);
                //       gfx.drawString("ycor: "+powerUp.getY(),1500,50);
                if (pause)
                    gfx.drawString("Pause", 1150, 50);

                gfx.setColor(Color.red);
                if (lives == 3) {
                    drawHearts(3);
                } else if (lives == 2) {
                    drawHearts(2);
                } else if (lives == 1) {
                    drawHearts(1);
                }

                RespawnOtherBullet(en, hero.getX() + ((hero.getX() + 200) - (hero.getX())) * rand.nextDouble(), 60);
                RespawnPu();
                checkGameState();

                g.drawImage(img, 0, 0, this);
            }
        }
        public void update(Graphics g)
        {
            paint(g);
        }

        public void run() {

            for(;;) {
                if(gamestart ) {
                    if(!pause) {
                        hero.move();
                        b.move();
                        en.move();
                        //  en1.move();
                        //   en2.move();
                        //   en3.move();
                        enemy.movee();
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
            else if(e.getKeyCode() == KeyEvent.VK_A)
            {
                if(!ArcadeGamestart) {
                    ArcadeGamestart = true;
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
        public void keyTyped(KeyEvent e) {

        }
        public void saveScore(){
            tf= new TextField("Ya name");
            tf.setBounds(200,1000,1100,100);
            tf.setFont(myFont2);

            //   tf2=new TextField();
            ///  tf2.setBounds(200,1200,1100,100);
            // tf2.setFont(myFont2);
            but=new Button("Confirm");
            but.setBounds(1300,1000,200,100);

            tf.setText(tf.getText());

            // tf.addActionListener(this);
            but.addActionListener(this);

            add(tf);
            add(but);
            //add   add(tf2);
        }

        public void actionPerformed(ActionEvent e) {
            //Object source = e.getSource();

            // if(source == but) {
            tf.setText(tf.getText());
            String playerName = tf.getText();
            // tf.setText(playerName);
            // tf2.setText(playerName);
            //String playerName2 = tf2.getText();

            s.save(this, playerName);
        }
        //}
    }
    */
}
