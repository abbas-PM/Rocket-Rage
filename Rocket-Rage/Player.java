import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random; 

import javax.swing.Timer;

public class Player extends GameObject{

    //Instances
    private Main main; 
    private Texture tex; 
    private Handler handler;
    private Camera cam; 

    //Animation used for player sprite
    private Animation fireAnimation; 

    //Used to see which key is pressed
    public boolean[] KeyDown; 

    //Used for player's velocity
    private int velP; 
    private int velocity; 
    private int maxVelocity; 

    //Attributes player has during the game
    private int lives; 
    private int points;
    private int distance; 

    //PowerUps
    private int[] Costs; 
    private int[] baseCosts = new int[4]; 
    private int counter; 
    public boolean shrinkSelected; 
    public int pSelected;

    //For when player is hit
    private boolean hit;

    //Used for powerups
    private Timer invincibilityTimer; 
    private Timer IAT; 
    private Timer distanceTimer; 
    private Timer Speed; 
    private int distanceFrames;
    private int invincibilityFrames; 
    private boolean stopR;  
    private Timer shrinkTimer; 
    private int shrinktime; 
    private int mTime; 
    private int ogV; private int ogMV; 
    private boolean shrinkUsed; 
    private Random random; 

    //Attributes that is stored when saving a game
    public int totalScore; 
    public int highScore; 
    public int[] skins;
    public int selected; 
    public BufferedImage playerSkin; 

    public Player(float x, float y, int width, int height, ID id, Main main, int totalScore, int highScore) {
        super(x, y, width, height, id);
        this.main = main;
        this.tex = this.main.getTex(); 
        this.handler = this.main.getHandler(); 
        this.cam = this.main.getCam(); 
        this.velP = 0; 
        this.velocity = 3; 
        this.maxVelocity = 7; 
        this.lives = 3; 
        this.points = 0; 
        this.distance = 0;
        this.Costs = new int[4];
        this.counter = 0;
        this.shrinkSelected = false; 
        this.pSelected = -1; 
        this.hit = false; 
        this.distanceFrames = 550;
        this.invincibilityFrames = 0;  
        this.stopR = false; 
        this.shrinktime = 0; 
        this.random = new Random(); 
        this.totalScore = totalScore; 
        this.highScore = highScore; 
        this.mTime = 7; 
        this.shrinkUsed = false; 
        this.skins = new int[7];
        this.playerSkin = tex.Player[0];
        this.selected = 0; 
        velX = 0; 
        velY = 0; 
        Timers(); 
        distanceTimer.start();
        Speed.start();
        fireAnimation = new Animation(tex.fire[0], tex.fire[1], tex.fire[2], tex.fire[3], tex.fire[4], tex.fire[5], tex.fire[6], tex.fire[7]); 
        KeyDown = new boolean[5];
        this.Costs[0] = 3; this.Costs[1] = 5; this.Costs[2] = 7; this.Costs[3] = 9;   
        this.baseCosts[0] = 3; this.baseCosts[1] = 5; this.baseCosts[2] = 7; this.baseCosts[3] = 9;  
    }

    @Override
    public void tick() {

        //For movement
        x += velX; 
        y += velY;   

        //Clamping the player in the window, different values for when player is shrinked
        if (shrinkSelected){
            x = main.clamp((int)x, ((int)-cam.getX()) - 25, ((int)-cam.getX()) + 1023);
            y = main.clamp((int)y, 0, 623); 
        }
        else{
            //Windows: 990, Mac: 1005 
            x = main.clamp((int)x, ((int)-cam.getX()) - 25, ((int)-cam.getX()) + 990);
            y = main.clamp((int)y, 5, 615); 
        }

        //Changing the speed of the fire animation of the rocket
        int speedFrames = 12; 

        if((KeyDown[0] || KeyDown[1] || KeyDown[2] || KeyDown[3]) && !KeyDown[4]) speedFrames = 5; 
        else if ((KeyDown[0] || KeyDown[1] || KeyDown[2] || KeyDown[3]) && KeyDown[4]) speedFrames = 1; 
        else if (!(KeyDown[0] || KeyDown[1] || KeyDown[2] || KeyDown[3]) && !KeyDown[4])speedFrames = 12; 

        fireAnimation.runAnimation(speedFrames);

        //Increases speed of game
        if (velP >= 60 && counter < 3 && pSelected != 3){
            velocity += 1;
            maxVelocity += 2; 
            counter += 1; 
            velP = 0;
            main.getCam().setCameraSpeed(main.getCam().getCameraSpeed() + 1); 
        } 

        //Creating a max value for lives, costs and points
        lives = main.clamp(lives, 0, 99);
        Costs[0] = main.clamp(Costs[0], 0, 99); 
        Costs[1] = main.clamp(Costs[1], 0, 99); 
        Costs[2] = main.clamp(Costs[2], 0, 99); 
        Costs[3] = main.clamp(Costs[3], 0, 99); 
        points = main.clamp(points, 0, 999); 

        //If player is hit, they get invincibility frames, this happens when those
        //frames should stop
        if (invincibilityFrames == 3){
            hit = false; 
            invincibilityFrames = 0; 
            invincibilityTimer.stop();
            IAT.stop();
            fireAnimation.setStop(false);
            stopR = false; 
        }

        //When shrink power up is selected
        if (shrinkSelected && !shrinkUsed){
            int randWay = random.nextInt(0, 3);
            if (randWay == 0) mTime = 7; 
            else if (randWay == 1) mTime = 9; 
            else if (randWay == 2) mTime = 11; 
            shrinkTimer.start();
            ogV = velocity; 
            ogMV = maxVelocity; 
            velocity = 20; 
            maxVelocity = 20; 
            shrinkUsed = true; 
        }

        //When the shrink power up ends
        if (shrinktime == mTime){
            shrinkTimer.stop();
            shrinktime = 0; 
            velocity = ogV; 
            maxVelocity = ogMV; 
            shrinkSelected = false; 
            shrinkUsed = false; 
            pSelected = -1; 
        }

        //When player runs out of lives
        if (lives == 0){
            totalScore += distance; 
            if (distance > highScore) highScore = distance; 
            main.gameState = Main.STATE.MENU; 
        }

        //Incrementing distance value
        if (x > ((int)-cam.getX() - 25)) distance += Math.abs(velX/7)*(counter+1); 

        Collision();
    }

    //Method to handle any collisions between player and another game object
    public void Collision(){

        for(int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i); 

            //For when player collides with a wall
            if (tempObject.getID() == ID.Wall){

                if ((getBoundsRight().intersects(tempObject.getBounds()) || getBoundsTop().intersects(tempObject.getBounds()) || getBounds().intersects(tempObject.getBounds())) && !hit){
                    hit = true;
                    invincibilityTimer.start();  
                    lives--;
                    fireAnimation.setStop(true); 
                    stopR = true; 
                    IAT.start();  
                }
            }
        }
    }

    //Health increase powerup
    public void healthIncrease(){
        this.lives += 3; 
        this.points -= Costs[0];
        Costs[0] += 3; 
        pSelected = -1; 
    }

    //Slow down powerup
    public void slowDown(){
        main.getWalls().setVELW(0);
        main.getWalls().setSpeed(5);
        this.velocity = 3; 
        this.maxVelocity = 7; 
        main.getCam().setCameraSpeed(1);
        velP = 0; 
        counter = 0; 

        this.points -= Costs[1]; 
        Costs[1] += 5; 
        pSelected = -1; 
    }

    //Cost reset powerup
    public void costReset(){
        int counter = 4; 
        for (int i = 0; i < 4; i++){
            if (Costs[i] == baseCosts[i]) counter--;  
        }

        if (counter == 0){
            pSelected = -3;  
        }

        else if (counter == 1){
            for (int i = 0; i < 4; i++){
                if (Costs[i] != baseCosts[i]){
                    this.points -= Costs[2]; 
                    Costs[2] += 7; 
                    Costs[i] = baseCosts[i];
                    break; 
                }
            } 
            pSelected = -1; 
        }

        else if (counter == 2 || counter == 3){
            int randWay = random.nextInt(1, counter+1); 
            int counter2 = 0; 
            for(int i = 0; i < 4; i++){
                if (Costs[i] != baseCosts[i]){
                    counter2++; 
                    if (counter2==randWay){
                        this.points -= Costs[2]; 
                        Costs[2] += 7; 
                        Costs[i] = baseCosts[i];
                        break; 
                    }
                }
            }
            pSelected = -1; 
        }

        else if (counter == 4){
            this.points -= Costs[2]; 
            int randWay = random.nextInt(0, 4);
            Costs[randWay] = baseCosts[randWay];
            pSelected = -1; 
        }
    }

    //shrink powerup
    public void shrink(){
        shrinkSelected = true; 
        this.points -= Costs[3]; 
        Costs[3] += 9; 
    }

    @Override
    public void render(Graphics g) {

        //Drawing the fire animation of the rocket
        if (shrinkSelected) fireAnimation.drawAnimation(g, (int)x - 27, (int)y - 51, 125, 130);
        else fireAnimation.drawAnimation(g, (int)x - 85, (int)y - 110, 250, 250);

        //Drawing the rocket
        if (!stopR){
            if (shrinkSelected) g.drawImage(playerSkin, (int)x - 27, (int)y - 52, 125, 130, null); 
            else g.drawImage(playerSkin, (int)x - 85, (int)y - 111, 250, 250, null); 
        }  
    }

    //Bottom HitBox
    public Rectangle getBounds(){
        if (shrinkSelected) return new Rectangle((int)(x+(width/8)) + 17,(int)y+(height/4)+5,(width/4) - 8,(height/4)+4);
        else return new Rectangle((int)(x+(width/4)) + 1,(int)y+(height/2),(width/2) - 8,(height/2)+4);
    }
    //Top Hitbox 
    public Rectangle getBoundsTop(){
        if (shrinkSelected) return new Rectangle((int)(x+(width/8)) + 17 ,(int)y + 2,(width/4) - 8,height/4 + 3);
        else return new Rectangle((int)(x+(width/4)) + 1 ,(int)y - 3,(width/2) - 8,height/2 + 3);
    }
    //Left Hitbox 
    public Rectangle getBoundsLeft(){
        return null; 
    }
    //Right Hitbox 
    public Rectangle getBoundsRight(){
        if (shrinkSelected) return new Rectangle((int)x+(width/2),(int)y + 2,15,(height/2)+7);
        else return new Rectangle((int)x+width-27,(int)y-2,29,height+5);
    }

    //Setters and Getters
    public int getLives(){
        return this.lives; 
    }

    public void setLives(int lives){
        this.lives = lives; 
    }

    public int getPoints(){
        return this.points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public int getVelocity(){
        return this.velocity; 
    }

    public void setVelocity(int velocity){
        this.velocity = velocity; 
    }

    public int getVM(){
        return this.maxVelocity; 
    }

    public void setVM(int maxVelocity){
        this.maxVelocity = maxVelocity; 
    }

    public float getDistance(){
        return this.distance; 
    }

    public void setDistance(int distance){
        this.distance = distance;  
    }

    //Setters and Getters for PowerUp costs
    public int[] getCosts(){
        return this.Costs; 
    }

    public void setCosts(int index, int value){
        this.Costs[index] = value; 
    }

    public int getCounter(){
        return this.counter; 
    }
    
    //Timers used for powerups and speed of game
    private void Timers(){

        invincibilityTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              if (!main.paused) invincibilityFrames++; 
            }
            
        });

        IAT = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!main.paused){
                    fireAnimation.setStop(!fireAnimation.getStop());
                    stopR = !stopR;
                } 
            }
            
        });

        distanceTimer = new Timer(distanceFrames, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (main.gameState == Main.STATE.GAME) distance++;  
            }
            
        });

        shrinkTimer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!main.paused) shrinktime++; 
            }
            
        });

        Speed = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              if (main.gameState == Main.STATE.GAME){
                main.getWalls().setVELW(main.getWalls().getVELW() + 1); 
                velP++;  
              }
            }
            
            
          });
    }

    //When the game ends and starts the game again
    public void reset(){
        x = 450; 
        y = 300; 
        velocity = 3; 
        maxVelocity = 7; 
        lives = 3; 
        points = 0; 
        distance = 0;
        counter = 0;
        shrinkSelected = false; 
        pSelected = -1; 
        hit = false; 
        distanceFrames = 550;
        invincibilityFrames = 0;  
        stopR = false;
        fireAnimation.setStop(false); 
        shrinktime = 0;  
        mTime = 7; 
        shrinkUsed = false; 
        Costs[0] = 3; Costs[1] = 5; Costs[2] = 7; Costs[3] = 9;   
        velP = 0; 
        main.getWalls().setVELW(0); 
        main.getCam().setCameraSpeed(1);
        cam.setX(0);
        cam.setY(0);
        invincibilityTimer.stop(); 
        IAT.stop();
        shrinkTimer.stop();
    }
    
}
