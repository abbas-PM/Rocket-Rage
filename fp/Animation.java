import java.awt.image.BufferedImage;
import java.awt.Graphics; 

public class Animation {
    
    private int frames; 

    private int index = 0; 
    public  int count = 0;

    private BufferedImage[] images; 
    private BufferedImage currentImg; 

    private boolean stopAnimation = false; 

    public Animation(BufferedImage... args){
        images = new BufferedImage[args.length]; 
        for(int i = 0; i < args.length; i++){
            images[i] = args[i]; 
        }
        frames = args.length; 
    }

    public void runAnimation(int speed){
        index++; 
        if (index > speed){
            index = 0; 
            nextFrame(); 
        }
    }

    private void nextFrame(){
        currentImg = images[(count) % frames]; 
        count++; 
    }

    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        if (!stopAnimation) g.drawImage(currentImg, x, y, scaleX, scaleY, null); 
    }

    public void setStop(boolean stopAnimation){
        this.stopAnimation = stopAnimation;
    }

    public boolean getStop(){
        return this.stopAnimation; 
    }

}
