import java.awt.*;
import javax.swing.*;

public class Display extends JFrame {
	
	public static void main(String[] args) 
	{
		//adding elements
		JFrame jframe = new JFrame("Display");
		Canvas canvas = new Canvas(); //to paint Firework
		Firework firework = new Firework(); //GUI
		firework.setTitle("Firework!"); //setting the title
		//adding some panels
		JPanel East = new JPanel();
		JPanel West = new JPanel();
		JPanel South = new JPanel();;

		//the window is using boarder layout
		jframe.add(canvas, BorderLayout.CENTER); // the center is where the firework will be displayed
		jframe.add(firework, BorderLayout.NORTH);
		jframe.add(East, BorderLayout.EAST);
		jframe.add(West, BorderLayout.WEST);
		jframe.add(South, BorderLayout.SOUTH);

		//basic setting for GUI
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(2000,1000);
		jframe.setVisible(true);
		
		
	}

}
