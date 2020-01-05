package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import proyecto.Game.STATE;

public class Shield extends GameObject{
	private int spawning = 0;
	private boolean visible = false;
	private GameObject player;
	private Random rand;
	private Game game;
	
	public Shield(float x, float y, ID id, Game game, Handler handler, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		player = handler.object.get(0);
		rand = new Random();
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 35, 35);
	}
	public void tick(){
		if(getBounds().intersects(new Rectangle(560, 280, 200, 200))){
			if(game.gameState == STATE.Mode3){
				x = (rand.nextInt(1240-50+1) + 50);
				y = (rand.nextInt(650-50+1) + 50);
			}
		}
		if(spawning >= (rand.nextInt(1600-1200+1) + 1200) && !visible){ //Aparecer dentro de un cierto rango de tiempo
			spawning = 0;
			visible = true;
			x = (rand.nextInt(1240-50+1) + 50);
			y = (rand.nextInt(650-50+1) + 50);
		}
		if(spawning >= (rand.nextInt(600-500+1) + 500) && visible){ //Desaparecer después de un cierto rango de tiempo
			visible = false;
		}
		if(visible && getBounds().intersects(player.getBounds())){
			spawning = 0;
			visible = false;
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
		BufferedImage shield = LoadImage("resources/Shield.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
        if(visible){	
			g2.drawImage(shield, at, null);
		}
	}
	public boolean getVisible(){
		return this.visible;
	}
}