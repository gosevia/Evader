package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Enemy6 extends GameObject{
	private int score = 0;
	private int counting = 0, spawning = 0;
	private boolean add = true;
	private boolean visible = true;
	private GameObject player;
	private float i = 0f;
	private float px, py;
	private Random rand;
	private Game game;
	
	public Enemy6(float x, float y, ID id, Handler handler, Game game, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		player = handler.object.get(0);
		rand = new Random();
		spawning = (rand.nextInt(150-0+1)+0);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 120, 120);
	}
	public void tick(){
		if(counting >= 60){
			score = score + 1;
			counting = 0;
		}
		counting++;
		if(spawning >= (180/game.getT())){ //Modo de aparecer y desaparecer cada aprox. 3 segundos
			spawning = 0;
			if(visible){
				visible = false;
			}else{
				visible = true;
				x = (rand.nextInt(1180-50+1) + 50);
				y = (rand.nextInt(620-50+1) + 50);
			}
		}
		spawning++;
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
		BufferedImage enemy6 = LoadImage("resources/Enemy6.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
        at.rotate(Math.toRadians(i), enemy6.getWidth()/2, enemy6.getHeight()/2);
		if(visible){
			g2.drawImage(enemy6, at, null);
		}
		if(!game.getPaused() && game.getProceed()){
			i = i + 0.5f;
		}
	}
	public boolean getVisible(){
		return this.visible;
	}
}