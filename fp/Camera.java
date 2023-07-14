public class Camera {
    
    private float x, y; 
    public int cameraSpeed;  

    public Camera(float x, float y){
        this.x = x; 
        this.y = y; 
        cameraSpeed = 1; 
    }

    public void tick(){
        x -= cameraSpeed;  
    }

    public float getX(){
        return x; 
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x; 
    }
    public void setY(float y){
        this.y = y; 
    }
}
