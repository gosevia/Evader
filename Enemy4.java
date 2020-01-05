package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.geom.AffineTransform; 

public class Enemy4 extends GameObject{
	private int score = 0;
	private int counting = 0;
	private boolean add = true;
	private GameObject player;
	private Game game;
	private float speed;
	private float i = 0f;
	private int timer = 0;
	
	public Enemy4(float x, float y, ID id, Handler handler, Game game, float speed){
		super(x, y, id, handler);
		this.speed = speed;
		this.game = game;
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
		for(int i=0; i<handler.object.size(); i++){
			if(handler.object.get(i).getID() == ID.Jugador) player = handler.object.get(i); //Guardar referencia del jugador
		}
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 30, 30);
	}
	public void tick(){
		if(game.getSpeed()){ //Mitad de velocidad si objeto de velocidad está activo
			x += velX/2;
			y += velY/2;
		}else{
			x += (velX*game.getT());
			y += (velY*game.getT());
		}
		//Ajustar la velocidad y dirección con relación a la posición actual del jugador
		float diffX = x - player.getX() - 15;
		float diffY = y - player.getY() - 15;
		float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX())+(y - player.getY())*(y - player.getY())); 
		velX = ((speed/distance) * diffX);
		velY = ((speed/distance) * diffY);
		if(counting >= 60){
			score = score + 1;
			counting = 0;
		}
		if(timer == 16 || timer == 21){
			add = true;
		}
		if(timer == 20 && add){
			addV();
			add = false;
		}else if(timer == 10 && add){
			addV();
			add = false;
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
		BufferedImage enemy4 = LoadImage("resources/Enemy4.png");
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((int)x, (int)y);
        at.rotate(Math.toRadians(i), enemy4.getWidth()/2, enemy4.getHeight()/2);
		g2.drawImage(enemy4, at, null);
		if(!game.getPaused() && game.getProceed()){
			i = i + 0.5f;
		}
	}
	public void addV(){
		speed = speed - 0.5f;
	}
}