import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Main extends Canvas implements Runnable{

    private boolean running = false; 
    private Thread thread; 
    public final int WIDTH = 1100, HEIGHT = 800;
    public int velW, velP; 

    private Timer clock; 
    private HUD hud; 
    private WallColumn wc; 
    private Handler handler; 
    private Camera cam; 
    private Texture tex;
    private Player player; 
    
    private Animation explosion; 
    private int Ex = 0; 
    private int Ey = 0; 
   
    public static void main(String[] args){
        new Main(); 
    }

    public Main(){
        this.setFocusable(true);
        this.tex = new Texture(); 
        this.cam = new Camera(0, 0);
        this.handler = new Handler();
        this.player = new Player(450, 300, 90, 28, ID.Player, this);
        this.handler.addObject(player);
        this.hud = new HUD(this); 
        this.wc = new WallColumn(this); 
        explosion = new Animation(tex.Explosion[0], tex.Explosion[1], tex.Explosion[2], tex.Explosion[3], tex.Explosion[4], tex.Explosion[5], tex.Explosion[6], tex.Explosion[7], tex.Explosion[8]); 
        Clock();
        clock.start();
        this.addKeyListener(new KeyInput(this));
        new Window(WIDTH, HEIGHT, "Game", this);
    }

    public synchronized void start(){
        if (running){
            return; 
        }

        thread = new Thread(this);
        thread.start(); 
        running = true;  
    }

    public synchronized void stop(){
        try{thread.join(); running=false;}
        catch(Exception e){e.printStackTrace();}
    }
    


    @Override
    public void run() {
        long lastTime = System.nanoTime(); 
        double amountOfTicks = 60.0; 
        double ns = 1000000000 / amountOfTicks; 
        double delta = 0; 
        long timer = System.currentTimeMillis();
        int frames = 0; 
        while(running){
          long now = System.nanoTime(); 
          delta += ((now - lastTime) / ns);
          lastTime = now; 
          while(delta >= 1){
            tick(); 
            delta--;
          }
          if(running) {
            render(); 
            frames++; 
          }
          if((System.currentTimeMillis() - timer) > 1000){
            timer += 1000; 
            //System.out.println("FRAMES: " + frames);
            frames = 0; 
          }
          
        } 
        stop(); 
    }

    private void tick(){
        handler.tick();
        explosion.runAnimation(5);
        cam.tick();
        wc.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
          this.createBufferStrategy(3);
          return; 
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;


        g2d.translate(cam.getX(), cam.getY());

        //Makes background move, but drops frames to much.
        /*
        for(int xx = 0; xx < t.background.getWidth() * -cam.getX(); xx += t.background.getWidth()){
          g.drawImage(t.background, xx, 0, WIDTH, HEIGHT - 150, null);
        } 

        for(int xx = 0; xx < t.cloud.getWidth() * -cam.getX(); xx += t.cloud.getWidth()){
          g.drawImage(t.cloud, xx, 0, WIDTH, HEIGHT, null);
        }*/

        g.drawImage(tex.background, (int)-cam.getX(), 0, WIDTH, HEIGHT - 150, null);

        if (explosion.count == 10){
          Random random = new Random();
          Ex = random.nextInt(WIDTH - 50); 
          Ey = random.nextInt(HEIGHT - 400, HEIGHT - 200);
          explosion.count = 0; 
        } 

        explosion.drawAnimation(g, (int)-cam.getX() + Ex, Ey, 100, 100);

        g.drawImage(tex.cloud, (int)-cam.getX(), 0, WIDTH, HEIGHT, null);
        g.drawImage(tex.PowerUps[0], (int)-cam.getX() - 160, 615, WIDTH + 340, 225, null); 

        handler.render(g);

        hud.render(g);

        g2d.translate(-cam.getX(), -cam.getY());

        g.dispose(); 
        bs.show(); 
    }

    public Texture getTex(){
      return tex; 
    }

    public Camera getCam(){
      return cam; 
    }

    public Handler getHandler(){
      return handler; 
    }

    public Player getPlayer(){
      return player; 
    }

    public WallColumn getWalls(){
      return wc; 
    }

    public int clamp(int val, int min, int max){
      return Math.max(min, (Math.min(max, val))); 
    }

    public void Clock(){

      clock = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          velW++; 
          velP++;  
        }
        
        
      });

    }
}
