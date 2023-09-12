public class Camera {
    
    private float x, y;//Coordinates for the camera 
    private int cameraSpeed;//How fast the camera moves 

    public Camera(float x, float y){
        this.x = x; 
        this.y = y; 
        this.cameraSpeed = 1; 
    }

    public void tick(){
        x -= cameraSpeed;//Camera moves horizontally 
    }

    //Getters and Setters
    public float getX(){
        return x; 
    }
    public float getY(){
        return y;
    }
    public int getCameraSpeed(){
        return this.cameraSpeed; 
    }
    public void setX(float x){
        this.x = x; 
    }
    public void setY(float y){
        this.y = y; 
    }
    public void setCameraSpeed(int speed){
        this.cameraSpeed = speed; 
    }
}
