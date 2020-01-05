package proyecto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import proyecto.Game.STATE;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private Game game;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(!game.getMouse()){
			for(int i=0; i<handler.object.size(); i++){ //Movimiento del jugador
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Jugador){
					if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
					if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
					if(key == KeyEvent.VK_S) tempObject.setVelY(5);
					if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				}
			}
		}
		if(!game.getProceed() && key == KeyEvent.VK_C){ //Presionar C para continuar a pantalla de puntuaciones
			game.setProceed(true);
			game.gameState = STATE.Puntaje;
		}
		if(game.gameState == STATE.Mode1 || game.gameState == STATE.Mode2 || game.gameState == STATE.Mode3 || game.gameState == STATE.Mode4 || game.gameState == STATE.Mode5 || game.gameState == STATE.Mode6){
			if(key == KeyEvent.VK_SPACE){ //Pausa cuando se presiona la tecla de espacio durante una sesión de juego
				if(game.getPaused()){
					game.setPaused(false);
				}else{
					game.setPaused(true);
				}
			}
			if(key == KeyEvent.VK_T){
				game.addT(1);
			}
			if(key == KeyEvent.VK_Y){
				game.addT(-1);
			}
			if(key == KeyEvent.VK_R && game.getPaused()){ //Regresar a pantalla de modos cuando se presiona la tecla R durante la pantalla de pausa
				game.setPaused(false);
				game.setReiniciar(true);
				game.setTurbo(1);
				game.gameState = STATE.Mode;
			}
		}
		if(!game.getProceed() && key == KeyEvent.VK_R){ //Durante estado Finish, reiniciar sesión si se presiona el teclado R
			game.setProceed(true);
			game.setReiniciar(true);
			game.gameState = game.previousState;
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(!game.getMouse()){
			for(int i=0; i<handler.object.size(); i++){ // por si quiere agregar mas jugadores
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Jugador){
					if(key == KeyEvent.VK_W) tempObject.setVelY(0);
					if(key == KeyEvent.VK_A) tempObject.setVelX(0);
					if(key == KeyEvent.VK_S) tempObject.setVelY(0);
					if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				}
			}
		}
	}
}