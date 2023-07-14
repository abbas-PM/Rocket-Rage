import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall extends GameObject{

    private Main main; 
    private Texture tex; 
    private Camera cam; 
    private Handler handler; 

    public Wall(float x, float y, int width, int height, ID id, Main main) {
        super(x, y, width, height, id);
        this.main = main; 
        this.tex = this.main.getTex(); 
        this.cam = this.main.getCam(); 
        this.handler = this.main.getHandler(); 
    }

    @Override
    public void tick() {
        x -= velX;
        if (x < (-cam.getX() - 150)) handler.removeObject(this);
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.Wall, (int)x, (int)y, 99, 99, null);  
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);

        //g2d.draw(getBounds());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height - 8);
    }

    @Override
    public Rectangle getBoundsTop() {
        return null; 
    }

    @Override
    public Rectangle getBoundsLeft() {
        return null; 
       }

    @Override
    public Rectangle getBoundsRight() {
        return null; 
    }
    
}
