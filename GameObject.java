package proyecto;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject{
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected Handler handler;
	
	public GameObject(float x, float y, ID id, Handler handler){ //Clase que heredan los objetos del juego
		this.x = x;
		this.y = y;
		this.id = id; 
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){this.x = x;}
	public void setY(int y){this.y = y;}
	public float getX(){return x;}
	public float getY(){return y;}
	public void setID(ID id){this.id = id;}
	public ID getID(){return id;}
	public void setVelX(int velX){this.velX = velX;}
	public float getVelX(){return velX;}
	public void setVelY(int velY){this.velY = velY;}
	public float getVelY(){return velY;}
	public boolean getVisible(){return true;}
}