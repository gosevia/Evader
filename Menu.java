package proyecto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import proyecto.Game.STATE;

public class Menu extends MouseAdapter{
	private Game game;
	private	Handler handler;
	private String name;
	private int fill;
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	public void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		switch(game.gameState){ //Detección de botón izquierdo y la posición del click
			case Menu:	if(mouseOver(mx, my, 500, 280, 268, 100)){
							game.setFill(1);
						}
						else if(mouseOver(mx, my, 500, 400, 268, 100)){
							game.setFill(2);
						}
						else if(mouseOver(mx, my, 500, 520, 268, 100)){
							game.setFill(3);
						}else{ game.setFill(0);}
						break;
			case Mode:	if(mouseOver(mx, my, 306, 180, 256, 100)){
							game.setFill(4);
						}
						else if(mouseOver(mx, my, 708, 180, 256, 100)){
							game.setFill(5);
						}
						else if(mouseOver(mx, my, 306, 290, 256, 100)){
							game.setFill(6);
						}
						else if(mouseOver(mx, my, 708, 290, 256, 100)){
							game.setFill(7);
						}
						else if(mouseOver(mx, my, 306, 400, 256, 100)){
							game.setFill(8);
						}
						else if(mouseOver(mx, my, 708, 400, 256, 100)){
							game.setFill(9);
						}
						else if(mouseOver(mx, my, 500, 560, 256, 80)){
							game.setFill(10);
						}else{ game.setFill(0);}
						break;
			case Info:	if(mouseOver(mx, my, 500, 560, 256, 80)){
							game.setFill(11);
						}else{ game.setFill(0);}
						break;
			case Control:if(mouseOver(mx, my, 500, 560, 256, 80)){
							game.setFill(13);
						}else if(mouseOver(mx, my, 306, 180, 256, 100)){
							game.setFill(14);
						}else if(mouseOver(mx, my, 708, 180, 256, 100)){
							game.setFill(15);
						}else { game.setFill(0);}
						break;
			case Puntaje:	if(mouseOver(mx, my, 500, 560, 256, 80)){
								game.setFill(12);
							}else{ game.setFill(0);}
							break;
			case Mode1:	if(game.getMouse() && !game.getPaused()){ 
							GameObject tempObject = handler.object.get(0);
							if(tempObject.getID() == ID.Jugador){
								tempObject.setX(mx-10);
								tempObject.setY(my-10);
							}
						}
						break;
			case Mode2:	if(game.getMouse() && !game.getPaused()){ 
							GameObject tempObject = handler.object.get(0);
							if(tempObject.getID() == ID.Jugador){
								tempObject.setX(mx-10);
								tempObject.setY(my-10);
							}
						}
						break;
			case Mode3:	if(game.getMouse() && !game.getPaused()){ 
							GameObject tempObject = handler.object.get(0);
							if(tempObject.getID() == ID.Jugador){
								tempObject.setX(mx-10);
								tempObject.setY(my-10);
							}
						}
						break;
			case Mode4:	if(game.getMouse() && !game.getPaused()){ 
							GameObject tempObject = handler.object.get(0);
							if(tempObject.getID() == ID.Jugador){
								tempObject.setX(mx-10);
								tempObject.setY(my-10);
							}
						}
						break;
			case Mode5:	if(game.getMouse() && !game.getPaused()){ 
							GameObject tempObject = handler.object.get(0);
							if(tempObject.getID() == ID.Jugador){
								tempObject.setX(mx-10);
								tempObject.setY(my-10);
							}
						}
						break;
			case Mode6:	if(game.getMouse() && !game.getPaused()){ 
							GameObject tempObject = handler.object.get(0);
							if(tempObject.getID() == ID.Jugador){
								tempObject.setX(mx-10);
								tempObject.setY(my-10);
							}
						}
						break;			
		}
	}
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		switch(game.gameState){ //Detección de botón izquierdo y la posición del click
			case Menu:	if(mouseOver(mx, my, 500, 280, 268, 100)){
							if(!game.getInsert()){
								new Inicio(game, handler);
							}else{
								game.gameState = STATE.Control;
							}
						}
						if(mouseOver(mx, my, 500, 400, 268, 100)){
							game.gameState = STATE.Info;
						}
						if(mouseOver(mx, my, 500, 520, 268, 100)){
							game.puntaje.loadScores("modo11.txt");
							game.puntaje.loadScores("modo12.txt");
							game.puntaje.loadScores("modo13.txt");
							game.puntaje.loadScores("modo21.txt");
							game.puntaje.loadScores("modo22.txt");
							game.puntaje.loadScores("modo23.txt");
							game.puntaje.loadScores("modo31.txt");
							game.puntaje.loadScores("modo32.txt");
							game.puntaje.loadScores("modo33.txt");
							game.puntaje.loadScores("modo41.txt");
							game.puntaje.loadScores("modo42.txt");
							game.puntaje.loadScores("modo43.txt");
							game.puntaje.loadScores("modo51.txt");
							game.puntaje.loadScores("modo52.txt");
							game.puntaje.loadScores("modo53.txt");
							game.puntaje.loadScores("modo61.txt");
							game.puntaje.loadScores("modo62.txt");
							game.puntaje.loadScores("modo63.txt");
							game.gameState = STATE.Puntaje;
						}
						game.setFill(0);
						game.music.playSoundP("resources/select.mp3");
						break;
			case Mode:	if(mouseOver(mx, my, 306, 180, 256, 100)){
							game.gameState = STATE.Mode1;
						}
						if(mouseOver(mx, my, 708, 180, 256, 100)){
							game.gameState = STATE.Mode2;
						}
						if(mouseOver(mx, my, 306, 290, 256, 100)){
							game.gameState = STATE.Mode3;
						}
						if(mouseOver(mx, my, 708, 290, 256, 100)){
							game.gameState = STATE.Mode4;
						}
						if(mouseOver(mx, my, 306, 400, 256, 100)){
							game.gameState = STATE.Mode5;
						}
						if(mouseOver(mx, my, 708, 400, 256, 100)){
							game.gameState = STATE.Mode6;
						}
						if(mouseOver(mx, my, 500, 560, 256, 80)){
							game.gameState = STATE.Control;
						}
						game.setFill(0);
						game.music.playSoundP("resources/select.mp3");
						break;
			case Info:	if(mouseOver(mx, my, 500, 560, 256, 80)){
							game.gameState = STATE.Menu;
						}
						game.setFill(0);
						game.music.playSoundP("resources/select.mp3");
						break;
			case Puntaje:	if(mouseOver(mx, my, 500, 560, 256, 80)){
								game.gameState = STATE.Menu;
								game.music.playSoundP("resources/select.mp3");
							}
							game.setFill(0);
							break;
			case Control: if(mouseOver(mx, my, 500, 560, 256, 80)){
							game.music.playSoundP("resources/select.mp3");
							game.gameState = STATE.Menu;
						}
						if(mouseOver(mx, my, 306, 180, 256, 100)){
							game.music.playSoundP("resources/select.mp3");
							game.setMouse(false);
							game.gameState = STATE.Mode;
						}
						if(mouseOver(mx, my, 708, 180, 256, 100)){
							game.music.playSoundP("resources/select.mp3");
							game.setMouse(true);
							game.gameState = STATE.Mode;
						}
						game.setFill(0);
						break;
		}
	}
	public void mouseReleased(MouseEvent e){
		
	}
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){ //Función para revisar si el click están dentro de una cierta dimensión
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public void tick(){
		
	}
	public void render(Graphics g){
		Font fnt1 = new Font("arial", 1, 100);
		Font fnt2 = new Font("arial", 3, 50);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("EVADER", 430, 200); 
		g.setFont(fnt2);
		g.drawRect(500, 280, 268, 100);
		g.drawRect(500, 400, 268, 100);
		g.drawRect(500, 520, 268, 100);
		g.drawString("Jugar", 560, 350);
		g.drawString("Info", 580, 470);
		g.drawString("Puntaje", 540, 590);
	}
}