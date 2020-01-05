package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform; 
import java.util.Random;

public class Enemy3 extends GameObject{
	private int score = 0;
	private int counting = 0;
	private boolean runningT = false;
	private float i = 0f;
	private Random rand;
	private Game game;
	
	public Enemy3(float x, float y, ID id, Handler handler, Game game){
		super(x, y, id, handler);
		this.velX = 0;
		this.velY = 0;
		this.game = game;
		rand = new Random();
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 130, 130);
	}
	public void tick(){
		if(counting >= 60){
			score = score + 1;
			counting = 0;
			Thread t = new Thread(){
				public synchronized void run(){
					try{
						if(!runningT){
							runningT = true; //Velocidad de proyectiles lanzados
							if(game.getSpeed()){
								handler.addObject(new Enemy3Bullet((x+20), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(800);
								handler.addObject(new Enemy3Bullet((x+100), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(800);
								handler.addObject(new Enemy3Bullet((x+20), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(800);
								handler.addObject(new Enemy3Bullet((x+100), (y+100), ID.Enemy3Bullet, handler, game));
							}/*else if(game.getTurbo()){
								handler.addObject(new Enemy3Bullet((x+20), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+100), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+20), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+100), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+20), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+100), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+20), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(100);
								handler.addObject(new Enemy3Bullet((x+100), (y+100), ID.Enemy3Bullet, handler, game));
							}else if(score > 20){
								handler.addObject(new Enemy3Bullet((x+20), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+100), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+20), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+100), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+20), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+100), (y+20), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+20), (y+100), ID.Enemy3Bullet, handler, game));
								Thread.sleep(200);
								handler.addObject(new Enemy3Bullet((x+100), (y+100), ID.Enemy3Bullet, handler, game));
							}*/else{
								for(int i=0; i < (game.getT()); i++){
									handler.addObject(new Enemy3Bullet((x+20), (y+20), ID.Enemy3Bullet, handler, game));
									Thread.sleep(400/game.getT());
									handler.addObject(new Enemy3Bullet((x+100), (y+20), ID.Enemy3Bullet, handler, game));
									Thread.sleep(400/game.getT());
									handler.addObject(new Enemy3Bullet((x+20), (y+100), ID.Enemy3Bullet, handler, game));
									Thread.sleep(400/game.getT());
									handler.addObject(new Enemy3Bullet((x+100), (y+100), ID.Enemy3Bullet, handler, game));
								}
							}
							runningT = false;
						}
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			};
				t.start();
			}
		counting++;
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
		BufferedImage enemy3 = LoadImage("resources/Enemy3.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
		at.rotate(Math.toRadians(i), enemy3.getWidth()/2, enemy3.getHeight()/2);
		g2.drawImage(enemy3, at, null);
		if(!game.getPaused()){
			i = i + 0.5f;
		}
	}
}