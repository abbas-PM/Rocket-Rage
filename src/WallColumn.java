import java.util.ArrayList;
import java.util.Random;

public class WallColumn {
    
    private ArrayList<Wall> walls;//Store all walls 
    private Random random;//To see where opening exists
    
    //Speed of walls
    private int speed = 5; 
    private int velW = 0; 

    ///Instances
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

    //Method to create walls
    private void initWalls(){
 
        int randWay = random.nextInt(0, 7); 

        //Creating an opening for player to go past walls
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

        //Removing any walls that are not screen 
        for(int i = 0; i < walls.size(); i++){
     
            if (walls.get(i).getX() < -cam.getX() - 100){
                handler.removeObject(walls.get(i));
                walls.remove(walls.get(i)); 
            }
        }

        //Speeding up the walls at a certain time
        if (walls.isEmpty()){
            player.setPoints(player.getPoints() + 1); 
            if (velW >= 20 && speed < 13){
                speed += 1;
                velW = 0; 
            }
            initWalls();
        }
    }


    //Getters and Setters
    public ArrayList<Wall> getWalls(){
        return walls; 
    } 

    public void setWalls(ArrayList<Wall> walls){
        this.walls = walls; 
    }

    public void setSpeed(int speed){
        this.speed = speed; 
    }

    public int getSpeed(){
        return this.speed; 
    }

    public void setVELW(int velW){
        this.velW = velW;  
    }

    public int getVELW(){
        return this.velW; 
    }
}
