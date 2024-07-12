import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    
    //Instances
    private Main main; 
    private Player player;  

    public KeyInput(Main main){
        this.main = main; 
        this.player = this.main.getPlayer();  
    }

    //Used to see which keys are pressed
    public void keyPressed(KeyEvent evt){

        int c = evt.getKeyCode(); 

        //Pausing the game
        if (c == KeyEvent.VK_ESCAPE && (main.gameState == Main.STATE.GAME || main.gameState == Main.STATE.PAUSED)){
            if (main.paused){
                main.paused = false; 
                main.gameState = Main.STATE.GAME;
            }
            else {
                main.paused = true; 
                main.gameState = Main.STATE.PAUSED; 
            }
        }

        //Movements:

        //Moving left
        if (c == KeyEvent.VK_LEFT && !main.paused){
            if (!player.KeyDown[4]) main.getPlayer().setVelX(-player.getVelocity());
            else main.getPlayer().setVelX(-player.getVM());
            player.KeyDown[0] = true; 
            player.KeyDown[1] = false; 
        }

        //Moving right
        if (c == KeyEvent.VK_RIGHT && !main.paused){
            if (!player.KeyDown[4]) main.getPlayer().setVelX(player.getVelocity());
            else main.getPlayer().setVelX(player.getVM());
            player.KeyDown[0] = false; 
            player.KeyDown[1] = true; 
         }

         //Moving up
        if (c == KeyEvent.VK_UP && !main.paused){
            if (!player.KeyDown[4]) main.getPlayer().setVelY(-player.getVelocity());
            else main.getPlayer().setVelY(-player.getVM());
            player.KeyDown[2] = true; 
            player.KeyDown[3] = false; 
        }

        //Moving down
        if (c == KeyEvent.VK_DOWN && !main.paused){
            if (!player.KeyDown[4]) main.getPlayer().setVelY(player.getVelocity());
            else main.getPlayer().setVelY(player.getVM());
            player.KeyDown[2] = false; 
            player.KeyDown[3] = true; 
        }

        //Speeding up
        if (c == KeyEvent.VK_SPACE && !main.paused){
            player.KeyDown[4] = true; 
            if (player.KeyDown[0]) main.getPlayer().setVelX(-player.getVM());
            if (player.KeyDown[1]) main.getPlayer().setVelX(player.getVM());
            if (player.KeyDown[2]) main.getPlayer().setVelY(-player.getVM());
            if (player.KeyDown[3]) main.getPlayer().setVelY(player.getVM());
        }

    }

    //Used to see if a keys has been released
    public void keyReleased(KeyEvent evt){

        int c = evt.getKeyCode();

        //Movements
        if(c == KeyEvent.VK_LEFT) player.KeyDown[0] = false;   
        if(c == KeyEvent.VK_RIGHT) player.KeyDown[1] = false;  
        if(c == KeyEvent.VK_UP) player.KeyDown[2] = false;  
        if(c == KeyEvent.VK_DOWN) player.KeyDown[3] = false;  
            
        //horizontal movement
        if((player.KeyDown[0] == false && player.KeyDown[1] == false)) main.getPlayer().setVelX(0);
        //vertical movement
        if((player.KeyDown[2] == false && player.KeyDown[3] == false)) main.getPlayer().setVelY(0);

        //Speed up released
        if (c == KeyEvent.VK_SPACE){
            player.KeyDown[4] = false; 
            if (player.KeyDown[0]) main.getPlayer().setVelX(-player.getVelocity());
            if (player.KeyDown[1]) main.getPlayer().setVelX(player.getVelocity());
            if (player.KeyDown[2]) main.getPlayer().setVelY(-player.getVelocity());
            if (player.KeyDown[3]) main.getPlayer().setVelY(player.getVelocity()); 
         }

        //Health powerUp is selected 
        if (c == KeyEvent.VK_W && !player.shrinkSelected && !main.paused){

            if (player.pSelected == 0){

                if (player.getPoints() >= player.getCosts()[0]){

                    player.healthIncrease();

                } else player.pSelected = -2; 

            } else player.pSelected = 0; 
        }  

        //Slow down powerUp is selected
        if (c == KeyEvent.VK_A && !player.shrinkSelected && !main.paused){

            if (player.pSelected == 1){

                if (player.getPoints() < player.getCosts()[1]){

                    player.pSelected = -2; 
                }

                else if (player.getCounter() == 0 && player.getPoints() >= player.getCosts()[1]){

                    player.pSelected = -4; 
                }

                else{

                    player.slowDown();
                }

            } else player.pSelected = 1; 

        }
        
        //Reset powerUp is selected
        if (c == KeyEvent.VK_S && !player.shrinkSelected && !main.paused){

            if (player.pSelected == 2){

                if (player.getPoints() >= player.getCosts()[2]){

                    player.costReset();
                    
                } else player.pSelected = -2; 
                
            } else player.pSelected = 2; 
        }

        //Shrink is selected
        if (c == KeyEvent.VK_D && !main.paused){

            if (player.pSelected == 3 && !player.shrinkSelected){

                if (player.getPoints() >= player.getCosts()[3]){
                    
                    player.shrink();
                     
                } else player.pSelected = -2; 

            } else player.pSelected = 3; 
            
        } 
                
    }
        
    public void keyTyped(KeyEvent evt){}   
}
