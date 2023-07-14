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
    
    private Timer clock; 
    private int second, minute; 
    private String ddSecond, ddMinute; 
    private DecimalFormat dFormat = new DecimalFormat("00");
    private DecimalFormat ddFormat = new DecimalFormat("0000"); 
    private Random random; 

    public HUD(Main main){
        this.main = main; 
        this.tex = this.main.getTex(); 
        this.cam = this.main.getCam(); 
        this.player = this.main.getPlayer(); 
        Clock();
        clock.start();
        random = new Random(); 
    }

    public void render(Graphics g){

        //Life
        g.drawImage(tex.HUD[13], -(int)cam.getX() + 10, 0, 70, 70, null);
        g.drawImage(tex.HUD[11], -(int)cam.getX() + 35, 5, 57, 57, null); 
        drawNum(dFormat.format(player.getLives()), g, -(int)cam.getX() + 62, 7);

        //Energy
        g.drawImage(tex.HUD[14], -(int)cam.getX() + 5, 45, 50, 50, null);
        g.drawImage(tex.HUD[11], -(int)cam.getX() + 35, 40, 57, 57, null); 
        drawNum(dFormat.format(player.getPoints()), g, -(int)cam.getX() + 62, 43);

        //Distance
        //int offset = random.nextInt(0, 5); 
        //drawNum(ddFormat.format(player.getDistance()), g, -(int)cam.getX() + 975, 10);
        //drawNum(Integer.toString((int)Math.abs(player.getVelX()) * 100 + offset), g, -(int)cam.getX() + 975, 100);

        //PowerUps

        //1) 
        if (player.getPC() == 0) g.drawImage(tex.PowerUps[2], -(int)cam.getX() + 80, 610, 250, 200, null);
        else g.drawImage(tex.PowerUps[1], -(int)cam.getX() + 80, 610, 250, 200, null);
        g.drawImage(tex.HUD[13], -(int)cam.getX() + 145, 680, 100, 100, null);
        g.drawImage(tex.HUD[15], -(int)cam.getX() + 150, 655, 50, 50, null);
        g.drawImage(tex.HUD[16], -(int)cam.getX() + 178, 680, 65, 65, null);
        drawNum(dFormat.format(player.getCosts()[0]), g, -(int)cam.getX() + 175, 655);

        //2)
        if (player.getPC() == 1) g.drawImage(tex.PowerUps[6], -(int)cam.getX() + 250, 610, 250, 200, null);
        else g.drawImage(tex.PowerUps[5], -(int)cam.getX() + 250, 610, 250, 200, null);
        g.drawImage(tex.HUD[15], -(int)cam.getX() + 320, 655, 50, 50, null);
        g.drawImage(tex.HUD[17], -(int)cam.getX() + 315, 670, 100, 100, null); 
        drawNum(dFormat.format(player.getCosts()[1]), g, -(int)cam.getX() + 345, 655);

        //3)
        if (player.getPC() == 2) g.drawImage(tex.PowerUps[4], -(int)cam.getX() + 420, 565, 250, 200, null);
        else g.drawImage(tex.PowerUps[3], -(int)cam.getX() + 420, 565, 250, 200, null);

        //Clock 
        /* 
        if (second != 0 || minute != 0){
            g.drawImage(t.HUD[12], -(int)cam.getX() + 900, 10, 57, 57, null); 
            g.drawImage(t.HUD[11], -(int)cam.getX() + 925, 10, 57, 57, null); 
            drawNum(ddMinute, g, -(int)cam.getX() + 950, 10);
            g.drawImage(t.HUD[10], -(int)cam.getX() + 995, 10, 57, 57, null); 
            drawNum(ddSecond, g, -(int)cam.getX() + 1010, 10);
        }*/
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

    public void Clock(){

        clock = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                second++; 
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);

                if(second == 60){
                    second = 0; 
                    minute++; 

                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                }
            }
        });
    
    }
}
