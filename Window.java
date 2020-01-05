package proyecto;
import java.awt.*;
import javax.swing.*;

public class Window extends Canvas{	
	private JFrame frame;
	
	public Window(int width, int height, String title, Game game){
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	public void setComponent(JPanel j){
		frame.add(j);
		frame.setVisible(true);
	}
}