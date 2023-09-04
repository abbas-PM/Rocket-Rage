import javax.swing.JFrame;
import java.awt.*;

public class Window extends Canvas{ 
    
    public Window(int width, int height, String title, Main main){

        JFrame frame = new JFrame(title);

        //Creating size of the window
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Can close when pressing X
        frame.setResizable(false);//Cant resize the window
        frame.setLocationRelativeTo(null);//Set to null
        frame.add(main);//Add the project to the frame
        frame.setVisible(true);//Set to true
        main.start(); 

        
    }
}