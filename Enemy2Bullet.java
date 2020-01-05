package proyecto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy2Bullet extends GameObject{
	private boolean vertical;
	
	public Enemy2Bullet(float x, float y, ID id, Handler handler, float velX, float velY, boolean vertical){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.vertical = vertical;
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 15, 15);
	}
	public void tick(){
		if(vertical){
			y += velY;
		}else{
			x += velX;
		}
		if(y <= 0 || y >= Game.height) handler.removeObject(this);
		if(x <= 0 || x >= Game.width) handler.removeObject(this);
		handler.addObject(new Trail(x, y, ID.Trail, handler, Color.red, 15, 15, 0.05f));
	}
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 15, 15);
	}
	public void addV(){
		if(velX < 0){
			velX = velX - 1;
		}else{
			velX = velX + 1;
		}
		if(velY < 0){
			velY = velY - 1;
		}else{
			velY = velY + 1;
		}
	}
}