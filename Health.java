package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import proyecto.Game.STATE;

public class Health extends GameObject{
	private int spawning = 0;
	private boolean visible = false;
	private GameObject player;
	private Random rand;
	private Game game;
	
	public Health(float x, float y, ID id, Game game, Handler handler, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		player = handler.object.get(0);
		rand = new Random();
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 30, 30);
	}
	public void tick(){
		if(getBounds().intersects(new Rectangle(560, 280, 200, 200))){
			if(game.gameState == STATE.Mode3){ //Evitar aparecer dentro o cercas del Enemy3
				x = (rand.nextInt(1240-50+1) + 50);
				y = (rand.nextInt(650-50+1) + 50);
			}
		}
		if(spawning >= (rand.nextInt(1200-1000+1) + 1000) && !visible && HUD.health < 50){ //Aparecer siempre y cuando la vida del jugador sea menor a 50
			spawning = 0;
			visible = true;
			x = (rand.nextInt(1240-50+1) + 50);
			y = (rand.nextInt(650-50+1) + 50);
		}
		if(spawning >= (rand.nextInt(600-500+1) + 500) && visible){ //Desaparecer si no interactua con el jugador
			visible = false;
		}
		if(visible && getBounds().intersects(player.getBounds())){ //Desaparecer al interactuar con el jugador
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
		BufferedImage health = LoadImage("resources/Health.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
        if(visible){	
			g2.drawImage(health, at, null);
		}
	}
	public boolean getVisible(){
		return this.visible;
	}
}