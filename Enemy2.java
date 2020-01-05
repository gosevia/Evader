package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class Enemy2 extends GameObject{
	private int timer = 0;
	private boolean add = false;
	private boolean vertical;
	private GameObject player;
	private Game game;
	private float px, py;
	private boolean verticalShot;
	private boolean horizontalShot;
	private int vShotDelay, hShotDelay;
	
	public Enemy2(float x, float y, ID id, Handler handler, Game game, float velX, float velY, boolean vertical){
		super(x, y, id, handler);
		this.velX = velX;
		this.velY = velY;
		this.game = game;
		this.vertical = vertical;
		player = handler.object.get(0);
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
		if(verticalShot){
			if(vShotDelay >= 30){
				verticalShot = false;
				vShotDelay = 0;
			}
			vShotDelay++;
		}
		if(horizontalShot){
			if(hShotDelay >= 30){
				horizontalShot = false;
				hShotDelay = 0;
			}
			hShotDelay++;
		}
		if(vertical){
			if(game.getSpeed()){
				y += velY/2;
			}else{
				y += velY*(game.getT());
			}
		}else{
			if(game.getSpeed()){
				x += velX/2;
			}else{
				x += velX*(game.getT());
			}
		}
		if(y <= 50 || y >= Game.height-100) velY *= -1;
		if(x <= 50 || x >= Game.width-100) velX *= -1;
		if(timer == 9 || timer == 19 || timer == 29){
			add = true;
		}
		if(timer == 30 && add){
			addV();
			add = false;
		}else if(timer == 20 && add){
			addV();
			add = false;
		}else if(timer == 10 && add){
			addV();
			add = false;
		}
		px = Math.abs(x - player.getX());
		py = Math.abs(y - player.getY());
		if(vertical){ //Detección de alineación para lanzar proyectil
			if(py < 5 && x > player.getX() && !horizontalShot){
				handler.addObject(new Enemy2Bullet(x, (y+25), ID.Enemy2Bullet, handler, -6, 0, false));
				horizontalShot = true;
			}else if(py < 5 && x < player.getX() && !horizontalShot){
				handler.addObject(new Enemy2Bullet((x+25), (y+25), ID.Enemy2Bullet, handler, 6, 0, false));
				horizontalShot = true;
			}	
			if(px < 5 && !verticalShot){
				handler.addObject(new Enemy2Bullet((x+15), (y+25), ID.Enemy2Bullet, handler, 0, -6, true));
				handler.addObject(new Enemy2Bullet((x+15), (y+25), ID.Enemy2Bullet, handler, 0, 6, true));
				verticalShot = true;
			}
		}else{
			if(px < 5 && y > player.getY() && !verticalShot){
				handler.addObject(new Enemy2Bullet((x+25), y, ID.Enemy2Bullet, handler, 0, -6, true));
				verticalShot = true;
			}else if(px < 5 && y < player.getY() && !verticalShot){
				handler.addObject(new Enemy2Bullet((x+25), (y+25), ID.Enemy2Bullet, handler, 0, 6, true));
				verticalShot = true;
			}
			if(py < 5 && !horizontalShot){
				handler.addObject(new Enemy2Bullet((x+25), (y+15), ID.Enemy2Bullet, handler, -6, 0, false));
				handler.addObject(new Enemy2Bullet((x+25), (y+15), ID.Enemy2Bullet, handler, 6, 0, false));
				horizontalShot = true;
			}
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
		BufferedImage enemy2 = LoadImage("resources/Enemy2.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
		g2.drawImage(enemy2, at, null);
	}
	public void addV(){ //Agregar velocidad de objeto
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