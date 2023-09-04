import java.awt.Color; 
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Timer;

public class HUD{
    
    private Main main; 
    private Texture tex;
    private Camera cam;
    private Player player;
    
    private Timer clock1; private Timer clock2; private Timer clock3; 
    private int second; private int second2; private int second3; 
    private DecimalFormat dFormat = new DecimalFormat("00");
    private DecimalFormat ddFormat = new DecimalFormat("000000000"); 
    public String[] texts = new String[10]; 

    public HUD(Main main){
        this.main = main; 
        this.tex = this.main.getTex(); 
        this.cam = this.main.getCam(); 
        this.player = this.main.getPlayer();
        this.texts[0] = "NO POWERUP SELECTED";
        this.texts[1] = "HEALTH INCREASE"; 
        this.texts[2] = "SLOW DOWN"; 
        this.texts[3] = "RANDOM COST RESET"; 
        this.texts[4] = "SHRINK";
        this.texts[5] = "NOT ENOUGH ENERGY"; 
        this.texts[6] = "NO COST CAN BE RESET";
        this.texts[7] = "SPEED IS AT A MINIMUM"; 
        Message();
        Message2();
        Message3();
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

        //Distance
        drawNum(ddFormat.format(player.getDistance()), g, -(int)cam.getX() + 865, 15); 

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

        if (player.pSelected == -3){
            clock2.start();
            if (second2 < 3){
                g.drawString(texts[6], -(int)cam.getX() + 120, 715);
            }
            else{
                clock2.stop();
                second2 = 0; 
                player.pSelected = -1; 
            }
        }

        if (player.pSelected == -4){
            clock3.start();
            if (second3 < 3){
                g.drawString(texts[7], -(int)cam.getX() + 120, 715);
            }
            else{
                clock3.stop();
                second3 = 0; 
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
            g.drawImage(tex.HUD[15], -(int)cam.getX() + 894, 655, 50, 50, null);
            g.drawImage(tex.HUD[20], -(int)cam.getX() + 890, 670, 97, 100, null); 
            g.drawString(texts[4], -(int)cam.getX() + 120, 715);
            drawNum(dFormat.format(player.getCosts()[3]), g, -(int)cam.getX() + 919, 655);
        }
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

    public void resetMessage(){
        clock1.stop(); 
        clock2.stop();
        clock3.stop();
        second = 0; 
        second2 = 0; 
        second3 = 0; 
    }
    private void Message(){

        clock1 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!main.paused) second++; 
            }
            
        });
    }

    private void Message2(){

        clock2 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!main.paused) second2++; 
            }
            
        });
        
    }

    private void Message3(){

        clock3 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!main.paused) second3++; 
            }
            
        });
    }
}
