import java.awt.Graphics; 
import java.util.LinkedList; 

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();//Used to store all game objects 

    public Handler(){}

    //Used call on every game object's tick method
    public void tick(){

        for (int i = 0; i < object.size(); i++){

            GameObject tempObject = object.get(i);

            tempObject.tick(); 
        }
    }

    //Used call on every game object's render method
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){

            GameObject tempObject = object.get(i);

            tempObject.render(g); 
        }
    }

    //Add an object to the list
    public void addObject(GameObject object){
        this.object.add(object);
    }

    //Remove an object from the list
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    //Remove all objects from the list
    public void ClearAll(){
        object.clear();  
    }

    //Add an object to the front of the list
    public void addFirst(GameObject object){
        this.object.add(0, object);
    }
}
