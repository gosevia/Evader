package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class Enemy1 extends GameObject{
	private int score = 0;
	private int counting = 0;
	private boolean add = true;
	private int timer = 0;
	private Game game;
	
	public Enemy1(float x, float y, ID id, Handler handler, Game game, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		Thread t = new Thread(
			new Runnable(){
				public void run(){
					try{
						while(!handler.object.isEmpty()){
							Thread.sleep(1000);
							if(!game.getPaused()){
								timer++;
							}
						}
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			});
		t.start();
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 50, 50);
	}
	public void tick(){
		if(counting >= 60){
			score = score + 1;
			counting = 0;
		}
		counting++;
		if(game.getSpeed()){ //Velocida a la mitad o regular
			x += velX/2;
			y += velY/2;
		}else{
			x += (velX*game.getT());
			y += (velY*game.getT());
		}
		if(y <= 0 || y >= Game.height-70) velY *= -1; //Invertir velocidad al tocar los límites del frames
		if(x <= 0 || x >= Game.width-50) velX *= -1;
		if(timer == 19 || timer == 39 || timer == 59){
			add = true;
		}
		if(score == 60 && add){
			addV();
			score = 61;
			add = false;
		}else if(score == 40 && add){
			addV();
			score = 41;
			add = false;
		}else if(score == 20 && add){
			addV();
			score = 21;
			add = false;
		}
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
		BufferedImage enemy1 = LoadImage("resources/Enemy1.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
		g2.drawImage(enemy1, at, null);
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