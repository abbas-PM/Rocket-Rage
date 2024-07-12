import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 

public class Menu extends MouseAdapter{

    //Instances
    private Main main; 
    private Handler handler; 
    private Texture tex;

    public Menu(Main main, Handler handler){
        this.main = main; 
        this.handler = handler; 
        this.tex = this.main.getTex(); 
    }

    //Method to see where a mouse clicked
    public void mousePressed(MouseEvent e){
        int mx = e.getX(); 
        int my = e.getY(); 
        
        //If the game is in the menu
        if(main.gameState == Main.STATE.MENU){
          //Play button
          if(mouseOver(mx, my,460,200,200,64)){

            //Start the game 
            if (main.getPlayer().totalScore > 0){
              main.getPlayer().reset();
              main.getHUD().resetMessage();
              handler.ClearAll();
              handler.addFirst(main.getPlayer());
              WallColumn wc = new WallColumn(main); 
              main.setWallColumn(wc);
            } 

            main.gameState = Main.STATE.GAME;
          }
            
            //Help button
            if(mouseOver(mx, my,460,300,200,64)){
              main.gameState = Main.STATE.HELP;//Put the game in the menu 
          }

            //Load and Save button
            if(mouseOver(mx, my,460,400,200,64)){
              main.gameState = Main.STATE.LOAD;//Put the game in the menu 
          }

            //Shop button
            if(mouseOver(mx, my,460,500,200,64)){
              main.gameState = Main.STATE.SHOP;//Put the game in the menu 
          }
            
            //Quit button
            if(mouseOver(mx, my,460,600,200,64)){
              System.exit(1);//Exit the game
          }
        }

        //If the game is in help page
        else if (main.gameState == Main.STATE.HELP){
          if(mouseOver(mx, my, 460, 650, 200, 64)){
            main.gameState = Main.STATE.MENU;
          }
        }

        //If the game is load save page
        else if (main.gameState == Main.STATE.LOAD){

          //If player saves
          if(mouseOver(mx, my, 460, 200, 200, 64)){
            main.getLoad().save();
            main.gameState = Main.STATE.MENU;
          }

          //If player loads
          if(mouseOver(mx, my, 460, 300, 200, 64)){
            main.getLoad().load();
            main.gameState = Main.STATE.MENU;
          }

          //If player returns to menu
          if(mouseOver(mx, my, 460, 400, 200, 64)){
            main.gameState = Main.STATE.MENU;
            }
          }

        //If the player is in the shop page
        else if (main.gameState == Main.STATE.SHOP){
          if(mouseOver(mx, my, 460, 600, 200, 64)){
            main.gameState = Main.STATE.MENU;
          }

          //Each button for buying/selecting a certain skin

          if(mouseOver(mx, my, 60, 200, 200, 64)){
            main.getPlayer().playerSkin = main.getTex().Player[0];
            main.getPlayer().selected = 0; 
          }

          if(mouseOver(mx, my, 310, 200, 200, 64)){
            if (main.getPlayer().skins[0] == 0 && main.getPlayer().totalScore >= 99999){
              main.getPlayer().skins[0] = 1; 
              main.getPlayer().totalScore -= 99999; 
              main.getPlayer().playerSkin = main.getTex().Player[1];
              main.getPlayer().selected = 1; 
            }
            else if (main.getPlayer().skins[0] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[1];
              main.getPlayer().selected = 1; 
            }
          }

          if(mouseOver(mx, my, 560, 200, 200, 64)){
            if (main.getPlayer().skins[1] == 0 && main.getPlayer().totalScore >= 499999){
              main.getPlayer().skins[1] = 1; 
              main.getPlayer().totalScore -= 499999; 
              main.getPlayer().playerSkin = main.getTex().Player[2];
              main.getPlayer().selected = 2; 
            }
            else if (main.getPlayer().skins[1] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[2];
              main.getPlayer().selected = 2; 
            }
          }

          if(mouseOver(mx, my, 810, 200, 200, 64)){
            if (main.getPlayer().skins[2] == 0 && main.getPlayer().totalScore >= 999999){
              main.getPlayer().skins[2] = 1; 
              main.getPlayer().totalScore -= 999999; 
              main.getPlayer().playerSkin = main.getTex().Player[3];
              main.getPlayer().selected = 3; 
            }
            else if (main.getPlayer().skins[2] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[3];
              main.getPlayer().selected = 3; 
            }
          }

          if(mouseOver(mx, my, 60, 400, 200, 64)){
            if (main.getPlayer().skins[3] == 0 && main.getPlayer().totalScore >= 4999999){
              main.getPlayer().skins[3] = 1; 
              main.getPlayer().totalScore -= 4999999; 
              main.getPlayer().playerSkin = main.getTex().Player[4];
              main.getPlayer().selected = 4; 
            }
            else if (main.getPlayer().skins[3] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[4];
              main.getPlayer().selected = 4; 
            }
          }

          if(mouseOver(mx, my, 310, 400, 200, 64)){
            if (main.getPlayer().skins[4] == 0 && main.getPlayer().totalScore >= 9999999){
              main.getPlayer().skins[4] = 1; 
              main.getPlayer().totalScore -= 9999999; 
              main.getPlayer().playerSkin = main.getTex().Player[5];
              main.getPlayer().selected = 5; 
            }
            else if (main.getPlayer().skins[4] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[5];
              main.getPlayer().selected = 5; 
            }
          }

          if(mouseOver(mx, my, 560, 400, 200, 64)){
            if (main.getPlayer().skins[5] == 0 && main.getPlayer().totalScore >= 49999999){
              main.getPlayer().skins[5] = 1; 
              main.getPlayer().totalScore -= 49999999; 
              main.getPlayer().playerSkin = main.getTex().Player[6];
              main.getPlayer().selected = 6; 
            }
            else if (main.getPlayer().skins[5] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[6];
              main.getPlayer().selected = 6; 
            }
          }

          if(mouseOver(mx, my, 810, 400, 200, 64)){
            if (main.getPlayer().skins[6] == 0 && main.getPlayer().totalScore >= 99999999){
              main.getPlayer().skins[6] = 1; 
              main.getPlayer().totalScore -= 99999999; 
              main.getPlayer().playerSkin = main.getTex().Player[7];
              main.getPlayer().selected = 7; 
            }
            else if (main.getPlayer().skins[6] == 1){
              main.getPlayer().playerSkin = main.getTex().Player[7];
              main.getPlayer().selected = 7; 
            }
          }
        }
    }

    public void mouseReleased(MouseEvent e) {}

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

        //If the mouse is in the box in terms of the width
        if(mx > x && mx < (x + width)){

            //If the mouse is in the box is terns of the height
            if(my > y && my <(y + height)){

                return true;//Return true
                
            }else return false;//If not return false 
        }else return false;//If not return false 
            
    }

    public void tick(){}

    //Method to display everything in the menu, shop, or load and save page
    public void render(Graphics g){

        if (main.gameState == Main.STATE.MENU){
          
          g.setColor(Color.black);

          //Draw the title and options you can choose
          g.setFont(tex.fonts[0]);
          g.drawString("ROCKET RAGE", 340, (int)(75 + Math.sin(System.currentTimeMillis() / 100) * 2));

          g.setFont(tex.fonts[1]);
          g.drawString("PLAY", 510, 240);
          g.drawString("HELP", 510, 340);
          g.drawString("SAVE OR", 480, 430); g.drawString("LOAD", 510, 460);
          g.drawString("SHOP", 510, 540);
          g.drawString("QUIT", 515, 640);
          
          g.drawString("HIGH SCORE: " + main.getPlayer().highScore, 340, 125); 
          g.drawString("TOTAL SCORE: " + main.getPlayer().totalScore, 340, 175); 
        
          //Drawing the rectangles
          g.drawRect(460,600,200,64);
          g.drawRect(460,500,200,64);
          g.drawRect(460,400,200,64);
          g.drawRect(460,300,200,64);
          g.drawRect(460,200,200,64);

          g.drawString("Created by Abbas Peermohammed", 5, 745);
               
        }

        else if (main.gameState == Main.STATE.HELP){
          g.setColor(Color.black);
          g.setFont(tex.fonts[1]);
          g.drawString("RETURN", 490, 690);

          g.drawRect(460,650,200,64);

          g.drawString("USE THE ARROW KEYS TO CONTROL THE ROCKET", 125, 50);
          g.drawString("AND AVOID THE WALLS", 325, 75);

          g.drawString("HOLD THE SPACEBAR TO MOVE FASTER", 200, 170);

          g.drawString("THE MORE WALLS YOU AVOID", 300, 245);
          g.drawString("THE MORE POINTS YOU COLLECT", 260, 270);

          g.drawString("SPEND POINTS ON POWERUPS", 285, 345); 
          g.drawString("COSTS INCREASE AFTER USAGE", 260, 370); 


          g.drawString("USE W TO INCREASE HEALTH", 300, 445); 
          g.drawString("USE A TO SLOW DOWN", 300, 470);
          g.drawString("USE S TO RESET A COST", 300, 495); 
          g.drawString("USE D TO SHRINK IN SIZE", 300, 520); 

          g.drawString("PRESS THE RESPECTED BUTTON", 270, 595); 
          g.drawString("TWICE TO USE THE POWERUP", 290, 620); 
        }

        else if (main.gameState == Main.STATE.LOAD){
          g.setFont(tex.fonts[1]);
          g.setColor(Color.black);

          g.drawString("SAVE", 513, 230); g.drawString("GAME", 510, 255);
          g.drawString("LOAD", 514, 330); g.drawString("GAME", 510, 355);
          g.drawString("RETURN", 490, 440); 

          g.drawRect(460,400,200,64);
          g.drawRect(460,300,200,64);
          g.drawRect(460,200,200,64);
          
        }

        else if (main.gameState == Main.STATE.SHOP){
          g.setFont(tex.fonts[1]);

          g.setColor(Color.black);
          g.drawString("RETURN", 490, 640);

          if (main.getPlayer().playerSkin == main.getTex().Player[0]){
            g.drawString("SELECTED", 65, 240);
          } else g.drawString("SELECT", 65, 240);

          if (main.getPlayer().skins[0] == 0){
            g.drawString("BUY FOR", 325, 230); g.drawString("99999", 350, 255);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[1]){
              g.drawString("SELECTED", 315, 240);
            } else g.drawString("SELECT", 315, 240);
          }

          if (main.getPlayer().skins[1] == 0){
            g.drawString("BUY FOR", 575, 230); g.drawString("499999", 585, 255);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[2]){
              g.drawString("SELECTED", 565, 240);
            } else g.drawString("SELECT", 565, 240);
          }

          if (main.getPlayer().skins[2] == 0){
            g.drawString("BUY FOR", 825, 230); g.drawString("999999", 835, 255);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[3]){
              g.drawString("SELECTED", 815, 240);
            } else g.drawString("SELECT", 815, 240);
          }

          if (main.getPlayer().skins[3] == 0){
            g.drawString("BUY FOR", 75, 430); g.drawString("4999999", 73, 455);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[4]){
              g.drawString("SELECTED", 65, 440);
            } else g.drawString("SELECT", 65, 440);
          }

          if (main.getPlayer().skins[4] == 0){
            g.drawString("BUY FOR", 325, 430); g.drawString("9999999", 323, 455);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[5]){
              g.drawString("SELECTED", 315, 440);
            } else g.drawString("SELECT", 315, 440);
          }

          if (main.getPlayer().skins[5] == 0){
            g.drawString("BUY FOR", 575, 430); g.drawString("49999999", 565, 455);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[6]){
              g.drawString("SELECTED", 565, 440);
            } else g.drawString("SELECT", 565, 440);
          }

          if (main.getPlayer().skins[6] == 0){
            g.drawString("BUY FOR", 825, 430); g.drawString("99999999", 815, 455);  
          } else{
            if (main.getPlayer().playerSkin == main.getTex().Player[7]){
              g.drawString("SELECTED", 815, 440);
            } else g.drawString("SELECT", 815, 440);
          }

          g.drawRect(60,400,200,64);
          g.drawRect(310,400,200,64);
          g.drawRect(560,400,200,64);
          g.drawRect(810,400,200,64);

          g.drawRect(60,200,200,64);
          g.drawRect(310,200,200,64);
          g.drawRect(560,200,200,64);
          g.drawRect(810,200,200,64);

          g.drawImage(main.getTex().Player[0], 30, 50, 250, 250, null);
          g.drawImage(main.getTex().Player[1], 280, 50, 250, 250, null);
          g.drawImage(main.getTex().Player[2], 530, 50, 250, 250, null);
          g.drawImage(main.getTex().Player[3], 780, 50, 250, 250, null);
          g.drawImage(main.getTex().Player[4], 30, 250, 250, 250, null);
          g.drawImage(main.getTex().Player[5], 280, 250, 250, 250, null);
          g.drawImage(main.getTex().Player[6], 530, 250, 250, 250, null);
          g.drawImage(main.getTex().Player[7], 780, 250, 250, 250, null);

          g.drawRect(460,600,200,64);
        }
    }
}
