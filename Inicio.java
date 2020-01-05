package proyecto;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.lang.Character.*;
import proyecto.Game.STATE;

public class Inicio extends JPanel{
	private Game game;
	private	Handler handler;
	private JFrame frame;
	private JButton mouseB, keyB, regresarB;
	private JTextField name;
	private JLabel control, nombre;
	private int height = 600, width = 700; 
	private String user;
	
	public Inicio(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
		frame = new JFrame("INGRESAR NOMBRE");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		nombre = new JLabel("INGRESA NOMBRE DE USUARIO");
		nombre.setForeground(Color.WHITE);
		nombre.setFont(new Font("San-Serif", Font.PLAIN, 20));
		nombre.setBounds(100, 100, 400, 50);
		nombre.setLocation((width/2)-150, 40);
		name = new JTextField("Anonimo");
		name.setBounds(50, 50, 100, 30);
		name.setLocation((width/2)-50, 100);
		control = new JLabel("ELEGIR MODO DE CONTROL");
		control.setForeground(Color.WHITE);
		control.setFont(new Font("San-Serif", Font.PLAIN, 20));
		control.setBounds(100, 100, 300, 50);
		control.setLocation((width/2)-140, 180);
		keyB = new JButton("WASD");
		keyB.setBounds(50, 50, 150, 50);
		keyB.setLocation((width/2)-75, 250);
		keyB.addActionListener(new keyBListener());
		mouseB = new JButton("CONTINUAR");
		mouseB.setBounds(50, 50, 150, 50);
		mouseB.setLocation((width/2)-75, 200);
		mouseB.addActionListener(new mouseBListener());
		regresarB = new JButton("REGRESAR");
		regresarB.setBounds(50, 50, 150, 50);
		regresarB.setLocation((width/2)-75, 450);
		regresarB.addActionListener(new regresarBListener());
		//add(regresarB);
		add(nombre);
		add(name);
		//add(control);
		//add(keyB);
		add(mouseB);
		frame.getContentPane().add(this);
		frame.setVisible(true);
	}
	class mouseBListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			//game.setMouse(true);
			user = name.getText();
			if(user.length() > 8){
				game.setName(user.substring(0,8));
			}else if(user.length() < 1){
				game.setName("Anonimo");
			}else{
				game.setName(user);
			}
			game.music.playSoundP("resources/select.mp3");
			game.gameState = STATE.Control;
			game.setInsert(true);
			frame.setVisible(false);
			frame.dispose();
		}
	}
	class keyBListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			game.setMouse(false);
			user = name.getText();
			if(user.length() > 8){
				game.setName(user.substring(0,8));
			}else if(user.length() < 1){
				game.setName("Anonimo");
			}else{
				game.setName(user);
			}
			game.music.playSoundP("resources/select.mp3");
			game.gameState = STATE.Mode;
			frame.setVisible(false);
			frame.dispose();
		}
	}
	class regresarBListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			game.music.playSoundP("resources/select.mp3");
			frame.setVisible(false);
			frame.dispose();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        Image img = LoadImage("resources/bg.png");
		g.drawImage(img, -7, -11, null);
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
}