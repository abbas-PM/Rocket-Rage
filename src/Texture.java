import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.Font;

public class Texture {
    
    //All spritesheets
    SpriteSheet fs; 
    SpriteSheet hs; 
    SpriteSheet es;
    SpriteSheet pb; 
    SpriteSheet ps;  

    //Used to load each sprite sheet
    private BufferedImage fire_sheet = null; 
    private BufferedImage HUD_sheet = null; 
    private BufferedImage explosion_sheet = null; 
    private BufferedImage box_sheet = null; 
    private BufferedImage player_sheet = null;

    private BufferedImage box = null;
    public BufferedImage background = null; 
    public BufferedImage cloud = null;  
    public BufferedImage Wall = null;

    //Arrays that store each sprite in a spritesheet
    public BufferedImage[] PowerUps = new BufferedImage[5]; 
    public BufferedImage[] Explosion = new BufferedImage[9]; 

    public BufferedImage[] fire = new BufferedImage[8];
    public BufferedImage[] HUD = new BufferedImage[21];

    public BufferedImage[] Player = new BufferedImage[8]; 

    //Custom font
    private Font font;
    public Font[] fonts = new Font[2]; 
    
    //Loading all the spritesheets
    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader(); 
        try{
            fire_sheet = loader.loadImage("/res/rocket_sheet.png");
            HUD_sheet = loader.loadImage("/res/HUD_sheet.png"); 
            background = loader.loadImage("/res/Background.png"); 
            cloud = loader.loadImage("/res/Cloud.png"); 
            Wall = loader.loadImage("/res/Wall.png");  
            box = loader.loadImage("/res/box.png"); 
            explosion_sheet = loader.loadImage("/res/explosion_sheet.png"); 
            box_sheet = loader.loadImage("/res/box_sheet.png"); 
            player_sheet = loader.loadImage("/res/player_sheet.png");

            InputStream is = getClass().getResourceAsStream("/res/font.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is); 
            fonts[0] = font.deriveFont(64f);
            fonts[1] = font.deriveFont(32f);
            
        }catch(Exception e){
            e.printStackTrace();
        }

        //Used to get each sprite in a spritesheet
        fs = new SpriteSheet(fire_sheet);
        hs = new SpriteSheet(HUD_sheet);
        es = new SpriteSheet(explosion_sheet); 
        pb = new SpriteSheet(box_sheet);
        ps = new SpriteSheet(player_sheet);  
        getTextures();
    }

    //Storing each sprite in arrays
    private void getTextures(){
        //Player skins
        Player[0] = ps.grabImage(1, 1, 150, 150); 
        Player[1] = ps.grabImage(2, 1, 150, 150); 
        Player[2] = ps.grabImage(3, 1, 150, 150);
        Player[3] = ps.grabImage(4, 1, 150, 150); 
        Player[4] = ps.grabImage(5, 1, 150, 150); 
        Player[5] = ps.grabImage(6, 1, 150, 150);
        Player[6] = ps.grabImage(7, 1, 150, 150); 
        Player[7] = ps.grabImage(8, 1, 150, 150);

        //Rocket Fire
        fire[0] = fs.grabImage(1, 1, 150, 150);
        fire[1] = fs.grabImage(2, 1, 150, 150);
        fire[2] = fs.grabImage(3, 1, 150, 150);
        fire[3] = fs.grabImage(4, 1, 150, 150);
        fire[4] = fs.grabImage(5, 1, 150, 150);
        fire[5] = fs.grabImage(6, 1, 150, 150);
        fire[6] = fs.grabImage(7, 1, 150, 150);
        fire[7] = fs.grabImage(8, 1, 150, 150);
         

        //HUD
        HUD[0] = hs.grabImage(1, 1, 32, 32); 
        HUD[1] = hs.grabImage(2, 1, 32, 32); 
        HUD[2] = hs.grabImage(3, 1, 32, 32); 
        HUD[3] = hs.grabImage(4, 1, 32, 32); 
        HUD[4] = hs.grabImage(5, 1, 32, 32); 
        HUD[5] = hs.grabImage(6, 1, 32, 32); 
        HUD[6] = hs.grabImage(7, 1, 32, 32); 
        HUD[7] = hs.grabImage(8, 1, 32, 32); 
        HUD[8] = hs.grabImage(9, 1, 32, 32); 
        HUD[9] = hs.grabImage(10, 1, 32, 32); 
        HUD[10] = hs.grabImage(11, 1, 32, 32); 
        HUD[11] = hs.grabImage(12, 1, 32, 32); 
        HUD[12] = hs.grabImage(13, 1, 32, 32); 
        HUD[13] = hs.grabImage(1, 2, 32, 32);
        HUD[14] = hs.grabImage(14, 1, 32, 32); 
        HUD[15] = hs.grabImage(15, 1, 32, 32); 
        HUD[16] = hs.grabImage(16, 1, 32, 32); 
        HUD[17] = hs.grabImage(17, 1, 32, 32); 
        HUD[18] = hs.grabImage(18, 1, 32, 32);
        HUD[19] = hs.grabImage(19, 1, 32, 32);
        HUD[20] = hs.grabImage(20, 1, 32, 32);

        //Explosions
        Explosion[0] = es.grabImage(1, 1, 100, 100);
        Explosion[1] = es.grabImage(2, 1, 100, 100);
        Explosion[2] = es.grabImage(3, 1, 100, 100);
        Explosion[3] = es.grabImage(4, 1, 100, 100);
        Explosion[4] = es.grabImage(5, 1, 100, 100);
        Explosion[5] = es.grabImage(6, 1, 100, 100);
        Explosion[6] = es.grabImage(7, 1, 100, 100);
        Explosion[7] = es.grabImage(8, 1, 100, 100);
        Explosion[8] = es.grabImage(9, 1, 100, 100);

        //PowerBox powerups
        PowerUps[0] = box;
        PowerUps[1] = pb.grabImage(1, 1, 200, 150); 
        PowerUps[2] = pb.grabImage(2, 1, 200, 150); 
        PowerUps[3] = pb.grabImage(3, 1, 200, 150); 
        PowerUps[4] = pb.grabImage(4, 1, 200, 150); 
    }
}
