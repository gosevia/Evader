package proyecto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.lang.Character.*;
import proyecto.Game.STATE;

public class Puntaje{
	private Game game;
	private	Handler handler;
	private String name, score, modo11, modo12, modo13, modo21, modo22, modo23, modo31, modo32, modo33;
	private String modo41, modo42, modo43, modo51, modo52, modo53, modo61, modo62, modo63;
	private String read = null;
	private ArrayList<String> text = new ArrayList<String>();

	public Puntaje(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	public void tick(){
		
	}
	public void loadScores(String arg){ //Cargar puntaje de archivos a variables del juego
		try{
			FileInputStream file = new FileInputStream(arg);
			DataInputStream in = new DataInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while((read = br.readLine()) != null){
				text.add(read);
			}
			name = (String)text.get(0);
			score = (String)text.get(1);
			switch(arg){
				case "modo11.txt":	modo11 = name + " : " + score; break;
				case "modo12.txt":	modo12 = name + " : " + score; break;
				case "modo13.txt":	modo13 = name + " : " + score; break;
				case "modo21.txt":	modo21 = name + " : " + score; break;
				case "modo22.txt":	modo22 = name + " : " + score; break;
				case "modo23.txt":	modo23 = name + " : " + score; break;
				case "modo31.txt":	modo31 = name + " : " + score; break;
				case "modo32.txt":	modo32 = name + " : " + score; break;
				case "modo33.txt":	modo33 = name + " : " + score; break;
				case "modo41.txt":	modo41 = name + " : " + score; break;
				case "modo42.txt":	modo42 = name + " : " + score; break;
				case "modo43.txt":	modo43 = name + " : " + score; break;
				case "modo51.txt":	modo51 = name + " : " + score; break;
				case "modo52.txt":	modo52 = name + " : " + score; break;
				case "modo53.txt":	modo53 = name + " : " + score; break;
				case "modo61.txt":	modo61 = name + " : " + score; break;
				case "modo62.txt":	modo62 = name + " : " + score; break;
				case "modo63.txt":	modo63 = name + " : " + score; break;
			}
			text.clear();
			br.close();
			in.close();
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public String getScore(String arg){ //Conseguir puntaje de archivo
		try{
			FileInputStream file = new FileInputStream(arg);
			DataInputStream in = new DataInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while((read = br.readLine()) != null){
				text.add(read);
			}
			score = (String)text.get(1);
			text.clear();
			br.close();
			in.close();
			file.close();
			return score;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public String getName(String arg){ //Conseguir nombre de archivo
		try{
			FileInputStream file = new FileInputStream(arg);
			DataInputStream in = new DataInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while((read = br.readLine()) != null){
				text.add(read);
			}
			name = (String)text.get(0);
			text.clear();
			br.close();
			in.close();
			file.close();
			return name;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public void saveScore(String arg, int s, String n){ //Sobre escribir viejo puntaje con uno nuevo
		try{
			FileWriter outFile = new FileWriter(arg);
			BufferedWriter out = new BufferedWriter(outFile);
			name = n;
			score = Integer.toString(s);
			out.write(name + "\n" + score);
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void render(Graphics g){
		Font fnt1 = new Font("arial", 1, 70);
		Font fnt2 = new Font("arial", 1, 20);
		Font fnt3 = new Font("verdana", 1, 40);
		g.setFont(fnt1);
		g.setColor(Color.black);
		g.fillRect(286, 150, 306, 120);
		g.fillRect(628, 150, 306, 120);
		g.fillRect(286, 280, 306, 120);
		g.fillRect(628, 280, 306, 120);
		g.fillRect(286, 410, 306, 120);
		g.fillRect(628, 410, 306, 120);
		g.setColor(Color.white);
		g.drawRect(286, 150, 306, 120);
		g.drawRect(628, 150, 306, 120);
		g.drawRect(286, 280, 306, 120);
		g.drawRect(628, 280, 306, 120);
		g.drawRect(286, 410, 306, 120);
		g.drawRect(628, 410, 306, 120);
		
		g.drawString("Puntuaciones altas totales", 180, 120); 
		g.setFont(fnt2);
		g.drawString("Modo 1", 330, 180);
		g.drawString("1. " + modo11, 330, 205);
		g.drawString("2. " + modo12, 330, 230);
		g.drawString("3. " + modo13, 330, 255);
		g.drawString("Modo 2", 670, 180);
		g.drawString("1. " + modo21, 670, 205);
		g.drawString("2. " + modo22, 670, 230);
		g.drawString("3. " + modo23, 670, 255);
		g.drawString("Modo 3", 330, 310);
		g.drawString("1. " + modo31, 330, 335);
		g.drawString("2. " + modo32, 330, 360);
		g.drawString("3. " + modo33, 330, 385);
		g.drawString("Modo 4", 670, 310);
		g.drawString("1. " + modo41, 670, 335);
		g.drawString("2. " + modo42, 670, 360);
		g.drawString("3. " + modo43, 670, 385);
		g.drawString("Modo 5", 330, 440);
		g.drawString("1. " + modo51, 330, 465);
		g.drawString("2. " + modo52, 330, 490);
		g.drawString("3. " + modo53, 330, 515);
		g.drawString("Modo 6", 670, 440);
		g.drawString("1. " + modo61, 670, 465);
		g.drawString("2. " + modo62, 670, 490);
		g.drawString("3. " + modo63, 670, 515);
		
		g.setFont(fnt3);
		g.drawString("Regresar", 530, 615);
		g.drawRect(500, 560, 256, 80);
	}
}