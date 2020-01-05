package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Enemy5 extends GameObject{
	private int score = 0;
	private int counting = 0, spawning = 0;
	private boolean visible = true;
	private GameObject player;
	private float px, py;
	private Random rand;
	private Game game;
	
	public Enemy5(float x, float y, ID id, Handler handler, Game game, float velX, float velY){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		player = handler.object.get(0);
		rand = new Random();
		spawning = (rand.nextInt(150-0+1)+0);
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
		if(spawning >= 180){
			spawning = 0;
			if(visible){
				visible = false;
			}else{
				visible = true; //Aparecer en posición aleatoria y producir proyectiles cuya velocidad depende del estado activo del objeto de velocidad
				x = (rand.nextInt(1220-50+1) + 50);
				y = (rand.nextInt(620-50+1) + 50);
				if(game.getSpeed()){
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 2, 0));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 2, 2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, 2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -2, 2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -2, 0));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -2, -2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, -2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 2, -2));
				}else{
					Thread t = new Thread(
						new Runnable(){
							public void run(){
								try{
									Thread.sleep(500); //Esperar medio segundo antes de producir proyectiles
									if(!game.getPaused()){
										for(int i=0; i < (game.getT()); i++){
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, 0));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, 0));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, -5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, -5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, -5));
										Thread.sleep(300);
										}
										/*handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, 0));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, 0));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, -5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, -5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, -5));*/
									}
								}catch(InterruptedException e){
									e.printStackTrace();
								}
							}
						});
					t.start();	
				}
			}
		}/*
		if(spawning >= 180 && !game.getTurbo()){
			spawning = 0;
			if(visible){
				visible = false;
			}else{
				visible = true; //Aparecer en posición aleatoria y producir proyectiles cuya velocidad depende del estado activo del objeto de velocidad
				x = (rand.nextInt(1220-50+1) + 50);
				y = (rand.nextInt(620-50+1) + 50);
				if(game.getSpeed()){
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 2, 0));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 2, 2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, 2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -2, 2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -2, 0));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -2, -2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, -2));
					handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 2, -2));
				}else{
					Thread t = new Thread(
						new Runnable(){
							public void run(){
								try{
									Thread.sleep(500); //Esperar medio segundo antes de producir proyectiles
									if(!game.getPaused()){
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, 0));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, 5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, 0));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, -5, -5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 0, -5));
										handler.addObject(new Enemy5Bullet((x+25), (y+25), ID.Enemy5Bullet, handler, 5, -5));	
									}
								}catch(InterruptedException e){
									e.printStackTrace();
								}
							}
						});
					t.start();	
				}
			}
		}*/
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
		BufferedImage enemy5 = LoadImage("resources/Enemy5.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
		if(visible){
			g2.drawImage(enemy5, at, null);
		}
	}
	public boolean getVisible(){
		return this.visible;
	}
}