package proyecto;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import proyecto.Game.STATE;

public class Info{
	private Game game;
	private	Handler handler;
	
	public Info(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	public void tick(){
		
	}
	public BufferedImage LoadImage(String FileName){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(FileName));
		}catch(IOException e){
			e.printStackTrace();
		}
		return img;
	}
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		BufferedImage controls = LoadImage("resources/Controls.png");
		AffineTransform at1 = AffineTransform.getTranslateInstance(80, 160);
		g2.drawImage(controls, at1, null);
		
		BufferedImage space = LoadImage("resources/Space.png");
		AffineTransform at2 = AffineTransform.getTranslateInstance(420, 220);
		g2.drawImage(space, at2, null);
		
		Font fnt1 = new Font("arial", 1, 70);
		Font fnt2 = new Font("verdana", 1, 40);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Informaci\u00F3n", 420, 120); 
		g.setFont(fnt2);
		g.drawString("Controles de", 60, 420);
		g.drawString("navegaci\u00F3n", 70, 460);
		g.drawString("Pausar/Continuar sesi\u00F3n", 590, 255);
		g.drawString("OBJETIVO", 420, 340);
		g.drawString("Evadir todos los objetos enemigos.", 420, 380);
		g.drawString("Almacenar un puntaje alto.", 420, 420);
		g.drawString("Divertirse!", 420, 460);
		g.drawString("Regresar", 530, 615);
		g.drawRect(500, 560, 256, 80);
	}
}