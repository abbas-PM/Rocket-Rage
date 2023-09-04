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
    //Windows size: 1100x800, Mac Size: 1100x790
    public final int WIDTH = 1100, HEIGHT = 800;
    public int velW, velP; 

    public Timer clock; 
    private HUD hud; 
    private WallColumn wc; 
    private Handler handler; 
    private Camera cam; 
    private Texture tex;
    private Menu menu; 
    private SaveLoad saveLoad; 
    private Player player; 
    
    private Animation explosion; 
    private int Ex = 0; 
    private int Ey = 0; 

    public enum STATE{MENU, GAME, PAUSED, HELP, SHOP, LOAD};
    public STATE gameState = STATE.MENU;  

    public boolean paused = false; 
   
    public static void main(String[] args){
        new Main(); 
    }

    public Main(){
        this.setFocusable(true);
        this.tex = new Texture(); 
        this.cam = new Camera(0, 0);
        this.handler = new Handler();
        this.player = new Player(450, 300, 90, 28, ID.Player, this, 0, 0);
        this.handler.addObject(player);
        this.hud = new HUD(this); 
        this.wc = new WallColumn(this);
        this.menu = new Menu(this, handler);
        this.saveLoad = new SaveLoad(this); 
        explosion = new Animation(tex.Explosion[0], tex.Explosion[1], tex.Explosion[2], tex.Explosion[3], tex.Explosion[4], tex.Explosion[5], tex.Explosion[6], tex.Explosion[7], tex.Explosion[8]); 
        Clock();
        clock.start();
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(menu);
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
            frames = 0; 
          }
          
        } 
        stop(); 
    }

    private void tick(){

      player.totalScore = clamp(player.totalScore, 0, 999999999);
      player.highScore = clamp(player.highScore, 0, 999999999);
      
      if (gameState == STATE.GAME){
        handler.tick();
        explosion.runAnimation(5);
        cam.tick();
        wc.tick();
      }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
          this.createBufferStrategy(3);
          return; 
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;

        if (gameState == STATE.GAME || gameState == STATE.PAUSED){

          g2d.translate(cam.getX(), cam.getY());

          //Windows: HEIGHT -150, Mac: HEIGHT -130
          g.drawImage(tex.background, (int)-cam.getX(), 0, WIDTH, HEIGHT - 150, null);

          if (explosion.count == 10){
            Random random = new Random();
            Ex = random.nextInt(WIDTH - 50); 
            Ey = random.nextInt(HEIGHT - 400, HEIGHT - 200);
            explosion.count = 0; 
          } 

          explosion.drawAnimation(g, (int)-cam.getX() + Ex, Ey, 100, 100);

          g.drawImage(tex.cloud, (int)-cam.getX(), 0, WIDTH, HEIGHT, null);
          //Windows WIDTH + 340, MAC: WIDTH + 360
          g.drawImage(tex.PowerUps[0], (int)-cam.getX() - 160, 615, WIDTH + 340, 225, null); 

          handler.render(g);

          hud.render(g);

          g2d.translate(-cam.getX(), -cam.getY());
      } 

      else {
        //Windows: HEIGHT -150, Mac: HEIGHT -130
        g.drawImage(tex.background, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(tex.cloud, 0, 0, WIDTH, HEIGHT, null);
        menu.render(g);
      }

      if (paused){
          g.setFont(tex.font);
          g.setColor(Color.white);

          g.drawString("PAUSED", 470, 300); 
      }

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

    public HUD getHUD(){
      return hud; 
    }

    public SaveLoad getLoad(){
      return saveLoad; 
    }

    public WallColumn getWalls(){
      return wc; 
    }

    public void setWallColumn(WallColumn wc){
      this.wc = wc; 
    }

    public int clamp(int val, int min, int max){
      return Math.max(min, (Math.min(max, val))); 
    }

    public void Clock(){

      clock = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (gameState == STATE.GAME){
            velW++; 
            velP++;  
          }
        }
        
        
      });

    }
}
