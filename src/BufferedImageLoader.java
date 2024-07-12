import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
    
    private BufferedImage image;//Used to store an image 

    //Method to try and find a certain image and return it
    public BufferedImage loadImage(String path){

        try{
            image = ImageIO.read(getClass().getResource(path)); 
        } 
        
        catch(IOException e){
            e.printStackTrace();
        }

        return image; 
    }
}
