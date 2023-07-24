import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random; 

public class KeyInput extends KeyAdapter{
    
    private Main main; 
    private Player player; 
    private WallColumn wc; 
    private Random random; 
    private int[] baseCosts = new int[5]; 

    public KeyInput(Main main){
        this.main = main; 
        this.player = this.main.getPlayer(); 
        this.wc = this.main.getWalls(); 
        this.random = new Random(); 
        baseCosts[0] = 3; baseCosts[1] = 5; baseCosts[2] = 5; baseCosts[3] = 7; baseCosts[4] = 7; 
    }

    public void keyPressed(KeyEvent evt){

        int c = evt.getKeyCode(); 

        if (c == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

        //PowerUps
        if (c == KeyEvent.VK_SHIFT){
            if (player.getPC() == 4) player.setPC(0); 
            else player.setPC(player.getPC() + 1);
        }

        //Movements
        if (c == KeyEvent.VK_A || c == KeyEvent.VK_LEFT){
            if (!player.KeyDown[4]) main.getPlayer().setVelX(-player.getVelocity());
            else main.getPlayer().setVelX(-player.getVM());
            player.KeyDown[0] = true; 
            player.KeyDown[1] = false; 
        }

        if (c == KeyEvent.VK_D || c == KeyEvent.VK_RIGHT){
            if (!player.KeyDown[4]) main.getPlayer().setVelX(player.getVelocity());
            else main.getPlayer().setVelX(player.getVM());
            player.KeyDown[0] = false; 
            player.KeyDown[1] = true; 
         }

        if (c == KeyEvent.VK_W || c == KeyEvent.VK_UP){
            if (!player.KeyDown[4]) main.getPlayer().setVelY(-player.getVelocity());
            else main.getPlayer().setVelY(-player.getVM());
            player.KeyDown[2] = true; 
            player.KeyDown[3] = false; 
        }

        if (c == KeyEvent.VK_S || c == KeyEvent.VK_DOWN){
            if (!player.KeyDown[4]) main.getPlayer().setVelY(player.getVelocity());
            else main.getPlayer().setVelY(player.getVM());
            player.KeyDown[2] = false; 
            player.KeyDown[3] = true; 
        }

        if (c == KeyEvent.VK_SPACE){
            player.KeyDown[4] = true; 
            if (player.KeyDown[0]) main.getPlayer().setVelX(-player.getVM());
            if (player.KeyDown[1]) main.getPlayer().setVelX(player.getVM());
            if (player.KeyDown[2]) main.getPlayer().setVelY(-player.getVM());
            if (player.KeyDown[3]) main.getPlayer().setVelY(player.getVM());
        }

    }
        

    public void keyReleased(KeyEvent evt){

        int c = evt.getKeyCode();

        //PowerUps
        if (c == KeyEvent.VK_ENTER){

            if (player.getPC() == 0 && player.getPoints() >= player.getCosts()[player.getPC()]){

                player.setLives(player.getLives() + 3);
                player.setPoints(player.getPoints() - player.getCosts()[player.getPC()]);  
                player.setCosts(0, player.getCosts()[player.getPC()] + 3); 

            }

            if (player.getPC() == 1 && player.getPoints() >= player.getCosts()[player.getPC()]){

                player.Enter1 = true; 
                player.setPoints(player.getPoints() - player.getCosts()[player.getPC()]);  
                player.setCosts(1, player.getCosts()[player.getPC()] + 5);
            }

            if (player.getPC() == 2 && player.getPoints() >= player.getCosts()[player.getPC()]){

                player.setPoints(player.getPoints() - player.getCosts()[player.getPC()]);  

                int randWay = random.nextInt(0, 3); 
                if (player.getCosts()[randWay] == baseCosts[randWay]){

                    if (randWay == 0) player.setLives(player.getLives() + 3);
                    if (randWay == 1) player.Enter1 = true; 
                    if (randWay == 2) player.setLives(player.getLives() + 5);
                }
                else player.setCosts(randWay, baseCosts[randWay]); 

                if (randWay != 2){
                      player.setCosts(2, player.getCosts()[player.getPC()] + 5);
                }
            
            }

        }


        //Movements
        if(c == KeyEvent.VK_A || c == KeyEvent.VK_LEFT) player.KeyDown[0] = false;   
        if(c == KeyEvent.VK_D || c == KeyEvent.VK_RIGHT) player.KeyDown[1] = false;  
        if(c == KeyEvent.VK_W || c == KeyEvent.VK_UP) player.KeyDown[2] = false;  
        if(c == KeyEvent.VK_S || c == KeyEvent.VK_DOWN) player.KeyDown[3] = false;  
            
        //horizontal movement
        if((player.KeyDown[0] == false && player.KeyDown[1] == false)) main.getPlayer().setVelX(0);
        //vertical movement
        if((player.KeyDown[2] == false && player.KeyDown[3] == false)) main.getPlayer().setVelY(0);

        if (c == KeyEvent.VK_SPACE){
            player.KeyDown[4] = false; 
            if (player.KeyDown[0]) main.getPlayer().setVelX(-player.getVelocity());
            if (player.KeyDown[1]) main.getPlayer().setVelX(player.getVelocity());
            if (player.KeyDown[2]) main.getPlayer().setVelY(-player.getVelocity());
            if (player.KeyDown[3]) main.getPlayer().setVelY(player.getVelocity()); 
         }
                
    }
        

    public void keyTyped(KeyEvent evt){}   
}
