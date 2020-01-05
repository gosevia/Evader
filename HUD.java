package proyecto;
import java.awt.*;
import proyecto.Game.STATE;

public class HUD{
	public static float health = 100f; //Valor estático para todos los modos
	private int greenValue = 255; //Para el color de la barra de vida
	private int counting = 0;
	private static int score = 0;
	private Game game;
	
	public HUD(Game game){
		this.game = game;
	}
	
	public void tick(){
		health = (int) Game.clamp(health, 0, 100);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		greenValue = (int)health*2;
		if(counting > 60){
			score = score + game.getT();
			counting = 0;
		}
		counting++;
		if(health <= 0){
			game.setScore(score); //Guardar puntaje actual a la clase principal
			//game.puntaje.sortScore();
			switch(game.gameState){ //Guardar y cargar puntaje si valor nuevo es mayor que el anterior
				case Mode1:	game.previousState = STATE.Mode1;
							if(game.getScore() > Integer.parseInt(game.puntaje.getScore("modo11.txt"))){
								game.puntaje.saveScore("modo13.txt", Integer.parseInt(game.puntaje.getScore("modo12.txt")), game.puntaje.getName("modo12.txt"));
								game.puntaje.saveScore("modo12.txt", Integer.parseInt(game.puntaje.getScore("modo11.txt")), game.puntaje.getName("modo11.txt"));
								game.puntaje.saveScore("modo11.txt", game.getScore(), game.getName());
								game.puntaje.loadScores("modo11.txt");
								game.puntaje.loadScores("modo12.txt");
								game.puntaje.loadScores("modo13.txt");
							}else if(game.getScore() > Integer.parseInt(game.puntaje.getScore("modo12.txt"))){
								game.puntaje.saveScore("modo13.txt", Integer.parseInt(game.puntaje.getScore("modo12.txt")), game.puntaje.getName("modo12.txt"));
								game.puntaje.saveScore("modo12.txt", game.getScore(), game.getName());
								game.puntaje.loadScores("modo12.txt");
								game.puntaje.loadScores("modo13.txt");
							}else if(game.getScore() > Integer.parseInt(game.puntaje.getScore("modo13.txt"))){
								game.puntaje.saveScore("modo13.txt", game.getScore(), game.getName());
								game.puntaje.loadScores("modo13.txt");
							}
							break;
				case Mode2: game.previousState = STATE.Mode2;
							if(score > Integer.parseInt(game.puntaje.getScore("modo21.txt"))){
								game.puntaje.saveScore("modo23.txt", Integer.parseInt(game.puntaje.getScore("modo22.txt")), game.puntaje.getName("modo22.txt"));
								game.puntaje.saveScore("modo22.txt", Integer.parseInt(game.puntaje.getScore("modo21.txt")), game.puntaje.getName("modo21.txt"));
								game.puntaje.saveScore("modo21.txt", score, game.getName());
								game.puntaje.loadScores("modo21.txt");
								game.puntaje.loadScores("modo22.txt");
								game.puntaje.loadScores("modo23.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo22.txt"))){
								game.puntaje.saveScore("modo23.txt", Integer.parseInt(game.puntaje.getScore("modo22.txt")), game.puntaje.getName("modo22.txt"));
								game.puntaje.saveScore("modo22.txt", score, game.getName());
								game.puntaje.loadScores("modo22.txt");
								game.puntaje.loadScores("modo23.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo23.txt"))){
								game.puntaje.saveScore("modo23.txt", score, game.getName());
								game.puntaje.loadScores("modo23.txt");
							}
							break;
				case Mode3: game.previousState = STATE.Mode3;
							if(score > Integer.parseInt(game.puntaje.getScore("modo31.txt"))){
								game.puntaje.saveScore("modo33.txt", Integer.parseInt(game.puntaje.getScore("modo32.txt")), game.puntaje.getName("modo32.txt"));
								game.puntaje.saveScore("modo32.txt", Integer.parseInt(game.puntaje.getScore("modo31.txt")), game.puntaje.getName("modo31.txt"));
								game.puntaje.saveScore("modo31.txt", score, game.getName());
								game.puntaje.loadScores("modo31.txt");
								game.puntaje.loadScores("modo32.txt");
								game.puntaje.loadScores("modo33.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo32.txt"))){
								game.puntaje.saveScore("modo33.txt", Integer.parseInt(game.puntaje.getScore("modo32.txt")), game.puntaje.getName("modo32.txt"));
								game.puntaje.saveScore("modo32.txt", score, game.getName());
								game.puntaje.loadScores("modo32.txt");
								game.puntaje.loadScores("modo33.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo33.txt"))){
								game.puntaje.saveScore("modo33.txt", score, game.getName());
								game.puntaje.loadScores("modo33.txt");
							}
							break;
				case Mode4: game.previousState = STATE.Mode4;
							if(score > Integer.parseInt(game.puntaje.getScore("modo41.txt"))){
								game.puntaje.saveScore("modo43.txt", Integer.parseInt(game.puntaje.getScore("modo42.txt")), game.puntaje.getName("modo42.txt"));
								game.puntaje.saveScore("modo42.txt", Integer.parseInt(game.puntaje.getScore("modo41.txt")), game.puntaje.getName("modo41.txt"));
								game.puntaje.saveScore("modo41.txt", score, game.getName());
								game.puntaje.loadScores("modo41.txt");
								game.puntaje.loadScores("modo42.txt");
								game.puntaje.loadScores("modo43.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo42.txt"))){
								game.puntaje.saveScore("modo43.txt", Integer.parseInt(game.puntaje.getScore("modo42.txt")), game.puntaje.getName("modo42.txt"));
								game.puntaje.saveScore("modo42.txt", score, game.getName());
								game.puntaje.loadScores("modo42.txt");
								game.puntaje.loadScores("modo43.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo43.txt"))){
								game.puntaje.saveScore("modo43.txt", score, game.getName());
								game.puntaje.loadScores("modo43.txt");
							}
							break;
				case Mode5: game.previousState = STATE.Mode5;
							if(score > Integer.parseInt(game.puntaje.getScore("modo51.txt"))){
								game.puntaje.saveScore("modo53.txt", Integer.parseInt(game.puntaje.getScore("modo52.txt")), game.puntaje.getName("modo52.txt"));
								game.puntaje.saveScore("modo52.txt", Integer.parseInt(game.puntaje.getScore("modo51.txt")), game.puntaje.getName("modo51.txt"));
								game.puntaje.saveScore("modo51.txt", score, game.getName());
								game.puntaje.loadScores("modo51.txt");
								game.puntaje.loadScores("modo52.txt");
								game.puntaje.loadScores("modo53.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo52.txt"))){
								game.puntaje.saveScore("modo53.txt", Integer.parseInt(game.puntaje.getScore("modo52.txt")), game.puntaje.getName("modo52.txt"));
								game.puntaje.saveScore("modo52.txt", score, game.getName());
								game.puntaje.loadScores("modo52.txt");
								game.puntaje.loadScores("modo53.txt");
							}else if(score > Integer.parseInt(game.puntaje.getScore("modo53.txt"))){
								game.puntaje.saveScore("modo53.txt", score, game.getName());
								game.puntaje.loadScores("modo53.txt");
							}
							break;
				case Mode6: game.previousState = STATE.Mode6;
							if((game.getScore() > Integer.parseInt(game.puntaje.getScore("modo62.txt"))) && (game.getScore() < Integer.parseInt(game.puntaje.getScore("modo61.txt")))){
								game.puntaje.saveScore("modo63.txt", Integer.parseInt(game.puntaje.getScore("modo62.txt")), game.puntaje.getName("modo62.txt"));
								game.puntaje.saveScore("modo62.txt", game.getScore(), game.getName());
								game.puntaje.loadScores("modo62.txt");
								game.puntaje.loadScores("modo63.txt");
							}else if(game.getScore() > Integer.parseInt(game.puntaje.getScore("modo61.txt"))){
								game.puntaje.saveScore("modo63.txt", Integer.parseInt(game.puntaje.getScore("modo62.txt")), game.puntaje.getName("modo62.txt"));
								game.puntaje.saveScore("modo62.txt", Integer.parseInt(game.puntaje.getScore("modo61.txt")), game.puntaje.getName("modo61.txt"));
								game.puntaje.saveScore("modo61.txt", game.getScore(), game.getName());
								game.puntaje.loadScores("modo61.txt");
								game.puntaje.loadScores("modo62.txt");
								game.puntaje.loadScores("modo63.txt");
							}else if(game.getScore() > Integer.parseInt(game.puntaje.getScore("modo63.txt")) && game.getScore() < Integer.parseInt(game.puntaje.getScore("modo62.txt"))){
								game.puntaje.saveScore("modo63.txt", game.getScore(), game.getName());
								game.puntaje.loadScores("modo63.txt");
							}
							break;		
			} //Cargar puntaje antes de la pantalla Finish
			game.puntaje.loadScores("modo11.txt");
			game.puntaje.loadScores("modo12.txt");
			game.puntaje.loadScores("modo13.txt");
			game.puntaje.loadScores("modo21.txt");
			game.puntaje.loadScores("modo22.txt");
			game.puntaje.loadScores("modo23.txt");
			game.puntaje.loadScores("modo31.txt");
			game.puntaje.loadScores("modo32.txt");
			game.puntaje.loadScores("modo33.txt");
			game.puntaje.loadScores("modo41.txt");
			game.puntaje.loadScores("modo42.txt");
			game.puntaje.loadScores("modo43.txt");
			game.puntaje.loadScores("modo51.txt");
			game.puntaje.loadScores("modo52.txt");
			game.puntaje.loadScores("modo53.txt");
			game.puntaje.loadScores("modo61.txt");
			game.puntaje.loadScores("modo62.txt");
			game.puntaje.loadScores("modo63.txt");
			health = 100; //Reiniciar valores del HUD
			greenValue = 255;
			counting = 0;
			score = 0;
			game.clearHandler();
			game.setProceed(false);
			game.setTurbo(1);
			game.gameState = STATE.Finish;
		}
	}
	
	public void render(Graphics g, int level){
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 20);
		g.setColor(new Color(75, greenValue, 0)); //Valor verde que baja a rojo (RGB)
		g.fillRect(10, 10, (int)health * 2, 20);
		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 20);
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize()*1.4F);
		g.setFont(newFont);
		g.drawString(game.getName(), 230, 25);
		g.drawString("Puntaje: " + score, 400, 25);
		g.drawString("Modo: " + level, 330, 25);
		g.drawString("SPEED X " + game.getT(), 1100, 25);
	}
	public static void setScore(int s){
		score = s;
	}
	public static void addScore(int s){
		score += s;
	}
	public static int getScore(){
		return score;
	}
}