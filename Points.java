package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import proyecto.Game.STATE;

public class Points extends GameObject{
	private int score = 0;
	private int counting = 0, spawning = 0;
	private boolean add = true;
	private boolean visible = true;
	private GameObject player;
	private float i = 0f;
	private float px, py;
	private Random rand;
	private Game game;
	
	public Points(float x, float y, ID id, Game game, Handler handler, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		player = handler.object.get(0);
		rand = new Random();
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 20, 20);
	}
	public void tick(){
		if(getBounds().intersects(new Rectangle(560, 280, 200, 200))){ //Revisar que no aparezca dentro o muy cercas del Enemy3
			if(game.gameState == STATE.Mode3){
				x = (rand.nextInt(1240-50+1) + 50);
				y = (rand.nextInt(650-50+1) + 50);
			}
		}
		if(counting >= 60){
			score = score + 1;
			counting = 0;
		}
		counting++;
		if(spawning >= (rand.nextInt(420-240+1) + 240)){
			spawning = 0;
			if(visible){ //Reducir puntaje y vida si desaparece, de otro modo aparecer en una posición aleatoria
				visible = false;
				HUD.addScore(-100);
				HUD.health -= 30;
				game.music.playSoundH("resources/hit.mp3");
			}else{
				visible = true;
				x = (rand.nextInt(1240-50+1) + 50);
				y = (rand.nextInt(650-50+1) + 50);
			}
		}
		if(visible && getBounds().intersects(player.getBounds())){ //Desaparecer cuando interactua con el jugador
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
		BufferedImage points = LoadImage("resources/Points.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
        at.rotate(Math.toRadians(i), points.getWidth()/2, points.getHeight()/2);
		if(visible){	
			g2.drawImage(points, at, null);
		}
		if(!game.getPaused()){
			i = i + 0.5f;
		}
	}
	public boolean getVisible(){
		return this.visible;
	}
}