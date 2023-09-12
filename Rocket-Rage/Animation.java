import java.awt.image.BufferedImage;
import java.awt.Graphics; 

public class Animation {
    
    private int frames;//Number of images 

    private int index = 0;//Used to track speed of animation
    private int count = 0;//Used to decide which image to display

    private BufferedImage[] images;//Contains all the images 
    private BufferedImage currentImg;//Image currently being displayed

    private boolean stopAnimation = false;//To see if animation should be displayed or not 

    public Animation(BufferedImage... args){
        images = new BufferedImage[args.length]; 
        for(int i = 0; i < args.length; i++){
            images[i] = args[i]; 
        }
        frames = args.length; 
    }

    //Method to draw next image at a certain speed
    public void runAnimation(int speed){
        index++; 
        if (index > speed){
            index = 0; 
            nextFrame(); 
        }
    }

    //Method to decide which image should be displayed
    private void nextFrame(){
        currentImg = images[(count) % frames]; 
        count++; 
    }

    //Display the animation
    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        if (!stopAnimation) g.drawImage(currentImg, x, y, scaleX, scaleY, null); 
    }

    //Setter and Getter for boolean variable and count variable
    public void setStop(boolean stopAnimation){
        this.stopAnimation = stopAnimation;
    }

    public boolean getStop(){
        return this.stopAnimation; 
    }

    public void setCount(int count){
        this.count = count;  
    }

    public int getCount(){
        return this.count; 
    }

}
