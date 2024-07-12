import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    //BufferedImage
    private BufferedImage image; 

    public SpriteSheet(BufferedImage image){
        this.image = image; 
    }

    //Method used to get an image from a sprite sheet and return it
    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height); 
        return img; 
    }
}
