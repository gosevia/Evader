package proyecto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import proyecto.Game.STATE;

public class Mode{
	private Game game;
	private	Handler handler;
	
	public Mode(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	public void tick(){
		
	}
	public void render(Graphics g){
		Font fnt1 = new Font("arial", 1, 70);
		Font fnt2 = new Font("arial", 3, 50);
		Font fnt3 = new Font("verdana", 1, 40);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Seleccionar el tipo de juego", 165, 120); 
		g.setFont(fnt2);
		g.drawString("Modo 1", 350, 250);
		g.drawString("Modo 2", 752, 250);
		g.drawString("Modo 3", 350, 360);
		g.drawString("Modo 4", 752, 360);
		g.drawString("Modo 5", 350, 470);
		g.drawString("Modo 6", 752, 470);
		g.drawRect(306, 180, 256, 100);
		g.drawRect(708, 180, 256, 100);
		g.drawRect(306, 290, 256, 100);
		g.drawRect(708, 290, 256, 100);
		g.drawRect(306, 400, 256, 100);
		g.drawRect(708, 400, 256, 100);
		g.setFont(fnt3);
		g.drawString("Regresar", 530, 615);
		g.drawRect(500, 560, 256, 80);
	}
}