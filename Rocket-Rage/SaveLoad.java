import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    
    //Instance 
    Main main; 

    public SaveLoad(Main main){
        this.main = main; 
    }

    //Method used to save the game
    public void save(){

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage(); 

            ds.totalScore = main.getPlayer().totalScore; 
            ds.highScore = main.getPlayer().highScore; 
            ds.skins = main.getPlayer().skins; 
            ds.selected = main.getPlayer().selected; 

            oos.writeObject(ds);

        } catch(Exception e){
            System.out.println("SAVE FAILED!");
        }
    }

    //Method used to load a savefile
    public void load(){

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            DataStorage ds = (DataStorage)ois.readObject(); 

            main.getPlayer().totalScore = ds.totalScore; 
            main.getPlayer().highScore = ds.highScore; 
            main.getPlayer().skins = ds.skins; 
            main.getPlayer().selected = ds.selected; 
            main.getPlayer().playerSkin = main.getTex().Player[main.getPlayer().selected];
            
        }catch(Exception e){
            System.out.println("LOAD FAILED!"); 
        }
    }
}
