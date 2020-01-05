package proyecto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy3Bullet extends GameObject{
	private Random rand = new Random();
	private Game game;
	
	public Enemy3Bullet(float x, float y, ID id, Handler handler, Game game){
		super(x, y, id, handler);
		this.velX = -5 + (rand.nextFloat() * (5 - -5)); //Angulo aleatorio de velocidad X
		if(Math.random() < 0.5){ //Probabilidad 50/50 de tener una velocidad Y negativa o positiva
			this.velY = 3;
		}else{
			this.velY = -3;
		}
		this.game = game;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 15, 15);
	}
	public void tick(){
		if(!game.getPaused() && game.getProceed()){
			y += velY;
			x += velX;
			if(y <= 0 || y >= Game.height) handler.removeObject(this);
			if(x <= 0 || x >= Game.width) handler.removeObject(this);
			handler.addObject(new Trail(x, y, ID.Trail, handler, Color.red, 15, 15, 0.05f)); 
		}
	}
	public void render(Graphics g){
		if(!game.getPaused() && game.getProceed()){
			g.setColor(Color.red);
			g.fillRect((int)x, (int)y, 15, 15);
		}
	}
}