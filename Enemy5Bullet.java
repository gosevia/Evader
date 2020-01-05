package proyecto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy5Bullet extends GameObject{
	private Random rand = new Random();
	
	public Enemy5Bullet(float x, float y, ID id, Handler handler, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 15, 15);
	}
	public void tick(){
		y += velY;
		x += velX;
		if(y <= 0 || y >= Game.height) handler.removeObject(this); //Desaparecer al salir del frame
		if(x <= 0 || x >= Game.width) handler.removeObject(this);
		handler.addObject(new Trail(x, y, ID.Trail, handler, Color.red, 15, 15, 0.05f)); //Crear un camino que va desapareciendo 
	}
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 15, 15);
	}
}