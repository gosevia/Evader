package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Jugador extends GameObject{
	private Random r = new Random();
	private int counting = 0, timer = 0;
	private Game game;
	private boolean hit = false, shield = false, runningT = false; //Para evitar numerosas colisión, el escudo y revisar si está corrieno el thread de jugador rojo
	
	public Jugador(int x, int y, ID id, Handler handler, Game game){
		super(x, y, id, handler);
		this.game = game;
		Thread t = new Thread(
			new Runnable(){
				public void run(){
					try{
						while(!handler.object.isEmpty()){
							Thread.sleep(250);
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
		return new Rectangle((int)x, (int)y, 30, 30);
	}
	public void tick(){
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.width - 45); //Evitar que el jugador se salga del frame
		y = Game.clamp(y, 0, Game.height - 75);
		collision();
	}
	private void collision(){
		for(int i=0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i); //tempObject is reference of enemy
			switch(tempObject.getID()){
				case Enemy1:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
									if(counting >= 30){
										counting = 0;
										HUD.addScore(-1);
										HUD.health -= 5;
										hit = true;
										if(!(game.getHitSound())){
											game.setHitSound(true);
											game.music.playSoundH("resources/hit.mp3");
										}
									}
								}
								break;
				case Enemy2:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
									if(counting >= 30){
										counting = 0;
										HUD.addScore(-1);
										HUD.health -= 2;
										hit = true;
										if(!(game.getHitSound())){
											game.setHitSound(true);
											game.music.playSoundH("resources/hit.mp3");
										}
									}
								}
								break;
				case Enemy2Bullet:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
										if(counting >= 30){
											counting = 0;
											HUD.addScore(-1);
											HUD.health -= 2;
											hit = true;
											if(!(game.getHitSound())){
												game.setHitSound(true);
												game.music.playSoundH("resources/hit.mp3");
											}
										}
									}
									break;
				case Enemy3:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
									if(counting >= 30){
										counting = 0;
										HUD.addScore(-1);
										HUD.health -= 1;
										hit = true;
										if(!(game.getHitSound())){
											game.setHitSound(true);
											game.music.playSoundH("resources/hit.mp3");
										}
									}
								}
								break;
				case Enemy3Bullet:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
										if(counting >= 30){
											counting = 0;
											HUD.addScore(-1);
											HUD.health -= 1;
											hit = true;
											if(!(game.getHitSound())){
												game.setHitSound(true);
												game.music.playSoundH("resources/hit.mp3");
											}
										}
									}
									break;
				case Enemy4:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
									if(counting >= 30){
										counting = 0;
										HUD.addScore(-1);
										HUD.health -= 5;
										hit = true;
										if(!(game.getHitSound())){
											game.setHitSound(true);
											game.music.playSoundH("resources/hit.mp3");
										}
									}
								}
								break;
				case Enemy5:	if(tempObject.getVisible() && getBounds().intersects(tempObject.getBounds()) && !shield){
									if(counting >= 30){
										counting = 0;
										HUD.addScore(-1);
										HUD.health -= 1;
										hit = true;
										if(!(game.getHitSound())){
											game.setHitSound(true);
											game.music.playSoundH("resources/hit.mp3");
										}
									}
								}		
								break;
				case Enemy5Bullet:	if(getBounds().intersects(tempObject.getBounds()) && !shield){
										if(counting >= 30){
											counting = 0;
											HUD.addScore(-1);
											HUD.health -= 1;
											hit = true;
											if(!(game.getHitSound())){
												game.setHitSound(true);
												game.music.playSoundH("resources/hit.mp3");
											}
										}
									}
									break;		
				case Enemy6:	if(tempObject.getVisible()){
									if(getBounds().intersects(tempObject.getBounds()) && !shield){
										if(counting >= 30){
											counting = 0;
											HUD.addScore(-1);
											HUD.health -= 1;
											hit = true;
											if(!(game.getHitSound())){
												game.setHitSound(true);
												game.music.playSoundH("resources/hit.mp3");
											}
										}
									}
								}
								break;
				case Points:	if(tempObject.getVisible()){
									if(getBounds().intersects(tempObject.getBounds())){
										HUD.addScore(100);
										game.music.playSoundP("resources/point.mp3");
									}
								}
								break;
				case Health:	if(tempObject.getVisible()){
									if(getBounds().intersects(tempObject.getBounds())){
										HUD.health += 25;
										game.music.playSoundP("resources/powerup.mp3");
									}
								}
								break;
				case Shield:	if(tempObject.getVisible()){
									if(getBounds().intersects(tempObject.getBounds())){
										shield = true;
										game.music.playSoundP("resources/powerup.mp3");
									}
								}
								break;
				case Speed:	if(tempObject.getVisible()){
								if(getBounds().intersects(tempObject.getBounds())){
									game.setSpeed(true);
									Thread speedThread = new Thread(
										new Runnable(){
											public void run(){
												try{
													Thread.sleep(5000);
													if(!game.getPaused()){
														game.setSpeed(false);
													}
												}catch(InterruptedException e){
													e.printStackTrace();
												}
											}
										});
									speedThread.start();
									game.music.playSoundP("resources/powerup.mp3");
								}
							}
							break;
			}
			if(timer >= 11){
				timer = 0;
			}
			counting++;
		}
	}
	public BufferedImage LoadImage(String FileName){ //Cargar imagen de archivo resources
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(FileName));
		}catch(IOException e){
			e.printStackTrace();
		}
		return img;
	}
	public void render(Graphics g){
		if(hit && !shield){
			BufferedImage player = LoadImage("resources/PlayerH.png");
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
			g2.drawImage(player, at, null);
			Thread hitThread = new Thread( //Versión roja del jugador cuando choca
				new Runnable(){
					public void run(){
						try{
							if(!runningT){
								runningT = true;
								Thread.sleep(800);
								if(!game.getPaused()){
									hit = false;
								}
								runningT = false;
							}
						}catch(InterruptedException e){
							e.printStackTrace();
						}
					}
				});
			hitThread.start();
		}
		if(shield){
			BufferedImage player = LoadImage("resources/PlayerG.png");
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
			g2.drawImage(player, at, null);
			Thread shieldThread = new Thread(
				new Runnable(){
					public void run(){
						try{
							if(!runningT){
								runningT = true;
								Thread.sleep(5000);
								if(!game.getPaused()){
									shield = false;
								}
								runningT = false;
							}
						}catch(InterruptedException e){
							e.printStackTrace();
						}
					}
				});
			shieldThread.start();
		}
		if(timer >= 10 && !hit && !shield){
			BufferedImage player = LoadImage("resources/PlayerB.png");
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
			g2.drawImage(player, at, null);
		}
		if(timer < 10 && !hit && !shield){
			BufferedImage player = LoadImage("resources/Player.png");
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
			g2.drawImage(player, at, null);
		}
	}
}