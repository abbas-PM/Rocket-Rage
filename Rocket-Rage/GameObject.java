import java.awt.Graphics; 
import java.awt.Rectangle;
  
public abstract class GameObject {
    
    protected float x, y, velX, velY; 
    protected ID id; 
    protected int width; 
    protected int height; 

    public GameObject(float x, float y, int width, int height, ID id){
        this.x = x;
        this.y = y;
        this.width = width; 
        this.height = height;  
        this.id = id; 
    }

    public abstract void tick(); 
    public abstract void render(Graphics g); 
    public abstract Rectangle getBounds(); 
    public abstract Rectangle getBoundsTop(); 
    public abstract Rectangle getBoundsLeft(); 
    public abstract Rectangle getBoundsRight(); 

    public void setX(float x){
        this.x = x; 
    }

    public void setVelX(float velX){
        this.velX = velX; 
    }

    public void setVelY(float velY){
        this.velY = velY; 
    }

    public void setY(float y){
        this.y = y; 
    }

    public void setID(ID id){
        this.id = id;  
    }

    public void setWidth(int w){
        this.width = w; 
    }

    public void setHeight(int h){
        this.height = h; 
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public float getVelX(){
        return this.velX;
    }

    public float getvelY(){
        return this.velY;
    }

    public ID getID(){
        return this.id;
    }

    public int getWidth(){
        return this.width; 
    }

    public int getHeight(){
        return this.height; 
    }



}
