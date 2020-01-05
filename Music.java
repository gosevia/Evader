package proyecto;
import javazoom.jl.player.Player;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javafx.scene.input.KeyCode;

public class Music{
	private Player play = null;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
	private Game game;
	
	public Music(Game game){
		this.game = game;
	}
	public void playMusic(String file){ //Para la música de fondo
		try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            play = new Player(bis);
            Thread musicThread = new Thread(){
				public void run(){
					while(true){
						try{
							play.play();
							//System.out.println("REPLAY");
							fis = new FileInputStream(file);
							bis = new BufferedInputStream(fis);
							play = new Player(bis);
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			};
			musicThread.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
	public void playSoundH(String file){ //Para sonidos de colisión
		try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            play = new Player(bis);
            Thread soundThread = new Thread(){
				public void run(){
					try{
						play.play(); //Esperar un poco antes de poder jugar el mismo sonido otra vez
						Thread.sleep(100);
						game.setHitSound(false);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			};
			soundThread.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
	public void playSoundP(String file){ //Para sonidos de selección, puntos, etc.
		try{
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            play = new Player(bis);
            Thread soundThread = new Thread(){
				public void run(){
					try{
						play.play();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			};
			soundThread.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
}