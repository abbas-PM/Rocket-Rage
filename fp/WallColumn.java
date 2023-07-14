import java.util.ArrayList;
import java.util.Random;

public class WallColumn {
    
    private ArrayList<Wall> walls; 
    private Random random; 
    private int speed = 5; 

    private Main main; 
    private Handler handler;
    private Camera cam; 
    private Player player; 

    public WallColumn(Main main){
        this.main = main; 
        walls = new ArrayList<>(); 
        random = new Random(); 
        this.cam = main.getCam(); 
        this.handler = main.getHandler();
        this.player = main.getPlayer(); 
        initWalls();
    }

    private void initWalls(){
 
        int randWay = random.nextInt(0, 7); 

        for (int i = 0; i < 7; i++){
            if (i != randWay){
                Wall tempWall = new Wall(-cam.getX() + 1100, i*92 + 2, 100, 100, ID.Wall, main);
                handler.addFirst(tempWall);
                tempWall.setVelX(speed); 
                walls.add(tempWall); 
            }
        }

    }

    public void tick(){

        for(int i = 0; i < walls.size(); i++){
     
            if (walls.get(i).getX() < -cam.getX()){
                walls.remove(walls.get(i)); 
            }
        }

        if (walls.isEmpty()){
            player.setPoints(player.getPoints() + 1); 
            if (player.Enter1){
                main.velW = 0; 
                speed = 5;
                player.setVelocity(3); 
                player.setVM(7); 
                main.velP = 0;
                player.counter = 0;
                player.Enter1 = false;  
            }
            if (main.velW >= 20 && speed < 13){
                speed += 1;
                main.velW = 0; 
            }
            initWalls();
        }
    }

    public ArrayList<Wall> getWalls(){
        return walls; 
    } 

    public void setWalls(ArrayList<Wall> walls){
        this.walls = walls; 
    }
}
