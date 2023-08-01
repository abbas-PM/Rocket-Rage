import java.awt.Color; 
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.Timer;

public class HUD{
    
    private Main main; 
    private Texture tex;
    private Camera cam;
    private Player player;
    
    private Timer clock1; 
    private int second; 
    private DecimalFormat dFormat = new DecimalFormat("00");
    private DecimalFormat ddFormat = new DecimalFormat("0000"); 
    private Random random; 
    public String[] texts = new String[10]; 

    public HUD(Main main){
        this.main = main; 
        this.tex = this.main.getTex(); 
        this.cam = this.main.getCam(); 
        this.player = this.main.getPlayer(); 
        random = new Random(); 
        this.texts[0] = "NO POWERUP SELECTED";
        this.texts[1] = "HEALTH INCREASE"; 
        this.texts[2] = "SLOW DOWN"; 
        this.texts[3] = "COST RESET"; 
        this.texts[4] = "";
        this.texts[5] = "NOT ENOUGH ENERGY"; 
        //this.texts[6] = "NO COST CAN BE RESET";
        Message();
    }

    public void render(Graphics g){

        g.setColor(Color.black);
        g.fillRect(-(int)cam.getX() + 900, 655, 93, 95);

        g.setFont(tex.font);

        //Life
        g.drawImage(tex.HUD[13], -(int)cam.getX() + 10, 0, 70, 70, null);
        g.drawImage(tex.HUD[11], -(int)cam.getX() + 35, 5, 57, 57, null); 
        drawNum(dFormat.format(player.getLives()), g, -(int)cam.getX() + 62, 7);

        //Energy
        g.drawImage(tex.HUD[14], -(int)cam.getX() + 5, 45, 50, 50, null);
        g.drawImage(tex.HUD[11], -(int)cam.getX() + 35, 40, 57, 57, null); 
        drawNum(dFormat.format(player.getPoints()), g, -(int)cam.getX() + 62, 43);

        //PowerUps

        if (player.pSelected == -1){
            g.drawString(texts[0], -(int)cam.getX() + 120, 715);
        }

        if (player.pSelected == -2){
            clock1.start();
            if (second < 3){
                g.drawString(texts[5], -(int)cam.getX() + 120, 715);
            }
            else{
                clock1.stop();
                second = 0; 
                player.pSelected = -1; 
            }
        }

        //1)
        if (player.pSelected == 0){
            g.drawImage(tex.PowerUps[1], -(int)cam.getX() + 824, 610, 250, 200, null);
            g.drawImage(tex.HUD[13], -(int)cam.getX() + 889, 680, 100, 100, null);
            g.drawImage(tex.HUD[15], -(int)cam.getX() + 894, 655, 50, 50, null);
            g.drawImage(tex.HUD[16], -(int)cam.getX() + 922, 680, 65, 65, null);
            g.drawString(texts[1], -(int)cam.getX() + 120, 715);
            drawNum(dFormat.format(player.getCosts()[0]), g, -(int)cam.getX() + 919, 655);
        } 
        //2)
        if (player.pSelected == 1){
            g.drawImage(tex.PowerUps[5], -(int)cam.getX() + 824, 610, 250, 200, null);
            g.drawImage(tex.HUD[15], -(int)cam.getX() + 894, 655, 50, 50, null);
            g.drawImage(tex.HUD[17], -(int)cam.getX() + 889, 670, 100, 100, null);
            g.drawString(texts[2], -(int)cam.getX() + 120, 715); 
            drawNum(dFormat.format(player.getCosts()[1]), g, -(int)cam.getX() + 919, 655);
        }

        //3
        if (player.pSelected == 2){
            g.drawImage(tex.PowerUps[7], -(int)cam.getX() + 824, 610, 250, 200, null); 
            g.drawImage(tex.HUD[15], -(int)cam.getX() + 894, 655, 50, 50, null);
            g.drawImage(tex.HUD[19], -(int)cam.getX() + 904, 680, 80, 80, null); 
            g.drawString(texts[3], -(int)cam.getX() + 120, 715);
            drawNum(dFormat.format(player.getCosts()[2]), g, -(int)cam.getX() + 919, 655);


        }
        //4)
        if (player.pSelected == 3){
            g.drawImage(tex.PowerUps[9], -(int)cam.getX() + 824, 610, 250, 200, null); 
            g.drawString(texts[4], -(int)cam.getX() + 120, 715);
        }


        //Clock 
        /* 
        if (second != 0 || minute != 0){
            g.drawImage(t.HUD[12], -(int)cam.getX() + 900, 10, 57, 57, null); 
            g.drawImage(t.HUD[11], -(int)cam.getX() + 925, 10, 57, 57, null); 
            drawNum(ddMinute, g, -(int)cam.getX() + 950, 10);
            g.drawImage(t.HUD[10], -(int)cam.getX() + 995, 10, 57, 57, null); 
            drawNum(ddSecond, g, -(int)cam.getX() + 1010, 10);
        }*/

        drawNum(ddFormat.format(player.getDistance()), g, -(int)cam.getX() + 500, 30); 
    }

    private void drawNum(String s, Graphics g, int x, int y){

        try{

        for(int i = 0; i < s.length(); i++){
            int val = Character.getNumericValue(s.charAt(i));
            if (val == 3) g.drawImage(tex.HUD[val], x + (i*22), y - 2, 50, 50, null);
            else if (val == 5) g.drawImage(tex.HUD[val], x + (i*22) + 6, y + 3, 50, 50, null);
            else if (val == 6) g.drawImage(tex.HUD[val], x + (i*22) + 3, y + 2, 50, 50, null);
            else if (val == 7) g.drawImage(tex.HUD[val], x + (i*22) - 2, y + 1, 55, 55, null);
            else if (val == 8) g.drawImage(tex.HUD[val], x + (i*22), y + 2, 50, 50, null);
            else if (val == 9) g.drawImage(tex.HUD[val], x + (i*22) - 2, y + 1, 50, 50, null);
            else if (val == 0 || val == 1 || val == 2 || val == 4) g.drawImage(tex.HUD[val], x + (i*22), y, 50, 50, null);
        }
    }catch(NullPointerException exception) {}
    }


    private void Message(){

        clock1 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                second++; 
            }
            
        });
    }
}
