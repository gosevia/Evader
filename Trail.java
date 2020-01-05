package proyecto;
import java.awt.*;

public class Trail extends GameObject{
	private float alpha = 1, life;
	private Color color;
	private int width, height;
	
	public Trail(float x, float y, ID id, Handler handler, Color color, int width, int height, float life){
		super(x, y, id, handler);
		this.color = color;
		this.width = width;
		this.height =height;
		this.life = life;
	}
	public void tick(){
		if(alpha > life){
			alpha -= life - 0.001f; //Disminuir el alpha y destruir objeto cuando el valor de life sea mayor al alpha
		}else{
			handler.removeObject(this);
		}
	}
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(makeTransparent(alpha)); //Para efectos de transparencia
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		g2.setComposite(makeTransparent(1)); //Regresar la transparencia a 1
	}
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	public Rectangle getBounds(){
		return null;
	}
}