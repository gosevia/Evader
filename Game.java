package proyecto;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Game extends Canvas implements Runnable{ //Hereda de Canvas para nivel bajo de pintar e implementa Runnable para los métodos run, start, stop.
	public static final int width = 1280, height = 720;
	private Thread thread; //Thread principal del juego
	private boolean running = false; //Si está corriendo el programa
	private boolean proceed = true; //Si está en sesión (false) o en una ventana de menu (true)
	private boolean reiniciar = false; //Si se ha reiniciado una sesión
	private boolean paused = false; //Si está en pausa el juego
	private boolean speed = false; //Si el objeto de velocidad está activado
	private boolean hitSound = false; //Sonido cuando se choca con un objeto enemigo
	private boolean mouse = false; //Bandera para el uso del ratón
	private int turbo = 1; //Nivel intenso de sesión
	private boolean insertN = false;
	private int fill = 0; //Para el resalto de botones
	private Window window;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	private Mode mode;
	private Info info;
	public Puntaje puntaje;
	public Music music;
	private Control control;
	private Random r;
	private int pixelX[] = new int[1000];
	private int pixelY[] = new int[1000];
	private String name = "Anonimo"; //Nombre del usuario, Anonimo por default
	private int score; //Guarda el puntaje actual de la sesión
	public enum STATE{ //Todos los estados del juego
		Menu,
		Info,
		Puntaje,
		Finish,
		Control,
		Mode,
		Mode1,
		Mode2,
		Mode3,
		Mode4,
		Mode5,
		Mode6
	};
	public STATE gameState = STATE.Menu; //Se inicia el estado del menu
	public STATE previousState = STATE.Menu; //Se inicia el estado previo
	
	public Game(){
		handler = new Handler(); //Maneja los objetos del juego... jugador, enemigos, etc.
		menu = new Menu(this, handler); //Clase de la ventana del menu
		mode = new Mode(this, handler); //Clase de la ventana de modos
		info = new Info(this, handler); //Clase de la ventana de información
		//inicio = new Inicio(this, handler);
		puntaje = new Puntaje(this, handler); //Clase que maneja las funciones del puntaje
		music = new Music(this); //Clase que maneja el sonido del programa
		control = new Control(this, handler);
		this.addKeyListener(new KeyInput(handler, this)); //Para reconocer las entradas del teclado
		this.addMouseListener(menu); //Para reconocer las entradas del ratón
		this.addMouseMotionListener(menu);
		window = new Window(width, height, "EVADER", this); //El componente de la ventana
		hud = new HUD(this); //Heads up display que se muestra durante una sesión de juego
		r = new Random();
		for(int i=0; i<1000; i++){ //Generar posición de pixeles para el fondo
			pixelX[i] = (int)(r.nextInt(1280-1+1)+1);
			pixelY[i] = (int)(r.nextInt(720-1+1)+1);
		}
		music.playMusic("resources/scifi.mp3"); //Iniciar musica de fondo
		//Se cargan las puntuaciones 
		puntaje.loadScores("modo11.txt");
		puntaje.loadScores("modo12.txt");
		puntaje.loadScores("modo13.txt");
		puntaje.loadScores("modo21.txt");
		puntaje.loadScores("modo22.txt");
		puntaje.loadScores("modo23.txt");
		puntaje.loadScores("modo31.txt");
		puntaje.loadScores("modo32.txt");
		puntaje.loadScores("modo33.txt");
		puntaje.loadScores("modo41.txt");
		puntaje.loadScores("modo42.txt");
		puntaje.loadScores("modo43.txt");
		puntaje.loadScores("modo51.txt");
		puntaje.loadScores("modo52.txt");
		puntaje.loadScores("modo53.txt");
		puntaje.loadScores("modo61.txt");
		puntaje.loadScores("modo62.txt");
		puntaje.loadScores("modo63.txt");
	}
	//synchronized para evitar el doble acceso de recursos
	public synchronized void start(){ 
		thread = new Thread(this); //Iniciar el nuevo thread con la instancia de esta clase
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try{
			thread.join(); //Parar el thread
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){ //Motor del juego
		this.requestFocus(); //Control automático del teclado/ratón
		long lastTime = System.nanoTime(); //Obtener tiempo actual hasta el nano segundo
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks; //Cuantas veces se puede dividir 60 entre 1e9 de nano segundos
		double delta = 0; //Cambio en tiempo
		long timer = System.currentTimeMillis(); //Obtener tiempo actual
		int frames = 0;
		while(running){
			long now = System.nanoTime(); //Tiempo que empieza el juego
			delta += (now - lastTime) / ns; //Agregar la cantidad de cambio desde el último ciclo
			lastTime = now; //Actualizar las variables para el siguiente ciclo
			while(delta >= 1){ //Asegurar que pase por lo menos un tick antes de continuar (para obtener aprox. 60 ticks por segundo)
				tick(); //Actualizar la lógica del programa
				delta --; //Regresar a 0 para la siguiente espera del cuadro (frame)
			}
			if(running){
				render(); //Lo que se va a pintar en el canvas
			}
			frames ++; //Nota para indicar que ha pasado un frame
			if(System.currentTimeMillis() - timer > 1000){ //Si ya pasó un segundo
				timer += 1000; //Agregar para el siguiente ciclo
				//System.out.println("FPS: " + frames);
				frames = 0; //Restablecer para el siguiente segundo
			}	
		}	
		stop(); //Parar el thread
	}
	private void tick(){ //Actualizar la lógica del juego
		if(!paused){ //Si no está en pausa, actualiza el handler y el hud dependiendo del estado
			handler.tick();
			if(reiniciar){ //Reiniciar valores para nueva sesión
				HUD.health = 100;
				score = 0;
				HUD.setScore(0);
				reiniciar = false;
			}
			switch(gameState){
				case Mode1: hud.tick(); break;
				case Mode2: hud.tick(); break;
				case Mode3: hud.tick(); break;
				case Mode4: hud.tick(); break;
				case Mode5: hud.tick(); break;
				case Mode6: hud.tick(); break;				
			}
		}
	}
	private void render(){ //Lo que se va a pintar en el canvas
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){ //Revisar si existe un BufferStrategy
			this.createBufferStrategy(3); //Crear un BufferStrategy triple para manejar 3 niveles de imagenes ya cargadas
			return;
		}
		Graphics g = bs.getDrawGraphics(); //Se obtiene Graphics del BufferStrategy	
		//Pintar el fondo predeterminado durante las ventanas Menu, Mode, Info, Puntaje y pintar un fondo negro con pixeles blancos durante una sesión de juego
		if(gameState == STATE.Menu || gameState == STATE.Mode || gameState == STATE.Info || gameState == STATE.Puntaje || gameState == STATE.Control){
			BufferedImage bg = LoadImage("resources/bg.png");
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
			g2.drawImage(bg, at, null);
			for(int i=0; i<1000; i++){ //Nueva posición de pixeles aleatorios
				pixelX[i] = (int)(r.nextInt(1280-1+1)+1);
				pixelY[i] = (int)(r.nextInt(720-1+1)+1);
			}
			if(fill != 0){ //Relleno de botones
				g.setColor(Color.blue);
				switch(fill){
					case 1: g.fillRect(500, 280, 268, 100); break;
					case 2: g.fillRect(500, 400, 268, 100); break;
					case 3: g.fillRect(500, 520, 268, 100); break;
					case 4: g.fillRect(306, 180, 256, 100); break;
					case 5: g.fillRect(708, 180, 256, 100); break;
					case 6: g.fillRect(306, 290, 256, 100); break;
					case 7: g.fillRect(708, 290, 256, 100); break;
					case 8: g.fillRect(306, 400, 256, 100); break;
					case 9: g.fillRect(708, 400, 256, 100); break;
					case 10: g.fillRect(500, 560, 256, 80); break;
					case 11: g.fillRect(500, 560, 256, 80); break;
					case 12: g.fillRect(500, 560, 256, 80); break;
					case 13: g.fillRect(500, 560, 256, 80); break;
					case 14: g.fillRect(306, 180, 256, 100); break;
					case 15: g.fillRect(708, 180, 256, 100); break;
				}
			}
		}else{
			if(proceed){
				g.setColor(Color.black);
				g.fillRect(0,0,width,height);
				g.setColor(Color.white);
				for(int i=0; i<1000; i++){
					g.drawLine(pixelX[i],pixelY[i],pixelX[i],pixelY[i]);
				}
			}
		}
		handler.render(g); //Pintar los objetos dentro de la lista handler
		switch(gameState){ //Pintar según el estado actual
			case Menu:	menu.render(g); break;
			case Mode:	mode.render(g); 
						if(!handler.object.isEmpty()){
							handler.object.clear(); //Se borra todos los objetos del handler 
						}
						break;
			case Info:	info.render(g); 
						if(!handler.object.isEmpty()){
							handler.object.clear();
						}
						break;			
			case Puntaje:	puntaje.render(g);
							if(!handler.object.isEmpty()){
								handler.object.clear();
							}
							break;
			case Control:	control.render(g);
							if(!handler.object.isEmpty()){
								handler.object.clear();
							}
							break;					
			case Mode1:	hud.render(g, 1); //Se pinta el hud
						if(handler.object.isEmpty()){ //Agregar todos los objetos según el modo
							handler.addObject(new Jugador(width/2-32, height/2-32, ID.Jugador, handler, this));
							handler.addObject(new Enemy1(800, 400, ID.Enemy1, handler, this, 5, 3));	
							handler.addObject(new Enemy1(500, 400, ID.Enemy1, handler, this, -3, 4));
							handler.addObject(new Enemy1(800, 300, ID.Enemy1, handler, this, 4, -3));
							handler.addObject(new Enemy4(50, 100, ID.Enemy4, handler, this, -1f));
							handler.addObject(new Points(700, 300, ID.Points, this, handler, 0, 0));
							handler.addObject(new Health(0, 0, ID.Health, this, handler, 0, 0));
							handler.addObject(new Shield(0, 0, ID.Shield, this, handler, 0, 0));
							handler.addObject(new Speed(0, 0, ID.Speed, this, handler, 0, 0));
						}
						break;
			case Mode2:	hud.render(g, 2);
						if(handler.object.isEmpty()){
							handler.addObject(new Jugador(width/2-32, height/2-32, ID.Jugador, handler, this));
							handler.addObject(new Enemy2(1000, 1, ID.Enemy2, handler, this, 2, 2, false));	
							handler.addObject(new Enemy2(1200, 610, ID.Enemy2, handler, this, 4, 4, true));
							handler.addObject(new Enemy2(60, 620, ID.Enemy2, handler, this, 3, 3, false));
							handler.addObject(new Enemy2(1, 50, ID.Enemy2, handler, this, 5, 5, true));
							handler.addObject(new Points(200, 200, ID.Points, this, handler, 0, 0));
							handler.addObject(new Health(0, 0, ID.Health, this, handler, 0, 0));
							handler.addObject(new Shield(0, 0, ID.Shield, this, handler, 0, 0));
							handler.addObject(new Speed(0, 0, ID.Speed, this, handler, 0, 0));
						}
						break;
			case Mode3:	hud.render(g, 3);
						if(handler.object.isEmpty()){
							handler.addObject(new Jugador(1200, 620, ID.Jugador, handler, this));
							handler.addObject(new Enemy3(width/2-50, height/2-50, ID.Enemy3, handler, this));
							handler.addObject(new Points(200, 500, ID.Points, this, handler, 0, 0));
							handler.addObject(new Health(0, 0, ID.Health, this, handler, 0, 0));
							handler.addObject(new Shield(0, 0, ID.Shield, this, handler, 0, 0));
							handler.addObject(new Speed(0, 0, ID.Speed, this, handler, 0, 0));
						}
						break;			
			case Mode4:	hud.render(g, 4);
						if(handler.object.isEmpty()){
							handler.addObject(new Jugador(width/2-32, height/2-32, ID.Jugador, handler, this));
							handler.addObject(new Enemy1(100, 200, ID.Enemy1, handler, this, -4, -3));
							handler.addObject(new Enemy4(1200, 600, ID.Enemy4, handler, this, -1.f));
							handler.addObject(new Enemy4(1200, 50, ID.Enemy4, handler, this, -1.5f));
							handler.addObject(new Enemy4(100, 100, ID.Enemy4, handler, this, -2.0f));
							handler.addObject(new Points(width/2-20, 500, ID.Points, this, handler, 0, 0));
							handler.addObject(new Health(0, 0, ID.Health, this, handler, 0, 0));
							handler.addObject(new Shield(0, 0, ID.Shield, this, handler, 0, 0));
							handler.addObject(new Speed(0, 0, ID.Speed, this, handler, 0, 0));
						}
						break;
			case Mode5:	hud.render(g, 5);
						if(handler.object.isEmpty()){
							handler.addObject(new Jugador(width/2-32, height/2-32, ID.Jugador, handler, this));
							handler.addObject(new Enemy5(100, 580, ID.Enemy5, handler,this, 0, 0));
							handler.addObject(new Enemy5(1100, 50, ID.Enemy5, handler, this, 0, 0));
							handler.addObject(new Enemy5(1100, 580, ID.Enemy5, handler,this, 0, 0));
							handler.addObject(new Points(1200, 640, ID.Points, this, handler, 0, 0));
							handler.addObject(new Health(0, 0, ID.Health, this, handler, 0, 0));
							handler.addObject(new Shield(0, 0, ID.Shield, this, handler, 0, 0));
							handler.addObject(new Speed(0, 0, ID.Speed, this, handler, 0, 0));
						}
						break;
			case Mode6:	hud.render(g, 6);
						if(handler.object.isEmpty()){
							handler.addObject(new Jugador(width/2-32, height/2-32, ID.Jugador, handler, this));
							handler.addObject(new Enemy6(100, 580, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(300, 580, ID.Enemy6, handler, this, 0, 0));							
							handler.addObject(new Enemy6(500, 580, ID.Enemy6, handler, this, 0, 0));								
							handler.addObject(new Enemy6(700, 580, ID.Enemy6, handler, this, 0, 0));								
							handler.addObject(new Enemy6(900, 580, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(1100, 580, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(100, 50, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(300, 50, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(500, 50, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(700, 50, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(900, 50, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(1100, 50, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(100, 200, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(100, 400, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(1100, 200, ID.Enemy6, handler, this, 0, 0));
							handler.addObject(new Enemy6(1100, 400, ID.Enemy6, handler, this, 0, 0));	
							handler.addObject(new Points(800, 500, ID.Points, this, handler, 0, 0));
							handler.addObject(new Health(0, 0, ID.Health, this, handler, 0, 0));
							handler.addObject(new Shield(0, 0, ID.Shield, this, handler, 0, 0));
						}
						break;
			case Finish:	Font fnt1 = new Font("arial", 1, 60);
							Font fnt2 = new Font("verdana", 1, 40);
							g.setFont(fnt1);
							g.setColor(Color.white);
							g.drawString("JUEGO TERMINADO", 300, 300); 
							g.setFont(fnt2);
							g.drawString("Continuar (C)", 420, 350);
							g.drawString("Reiniciar Partida (R)", 360, 400);
							break;
		}
		if(paused){ //Condición cuando se detiene una sesión
			Font fnt1 = new Font("verdana", 1, 40);
			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("EN PAUSA", 520, 100); 
			g.drawString("REGRESAR (R)", 470, 150);
		}
		g.dispose(); //Libera cualquier recurso del sistema que esté utilizando g
		bs.show(); //Mostrar el buffer
	}
	public BufferedImage LoadImage(String FileName){ //Función para cargar imagenes
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(FileName));
		}catch(IOException e){
			e.printStackTrace();
		}
		return img;
	}
	public static float clamp(float var, float min, float max){ //Función que limita el valor de un float dentro de un rango finito
		if(var >= max){
			return var = max;
		}else if(var <= min){
			return var = min;
		}else{
			return var;
		}
	}
	public void addT(int t){
		this.turbo = turbo + t;
		if(turbo < 1){
			turbo = 1;
		}
	}
	public void setInsert(boolean s){
		this.insertN = s;
	}
	public boolean getInsert(){
		return this.insertN;
	}
	public void setTurbo(int t){
		this.turbo = t;
	}
	public int getT(){
		return this.turbo;
	}
	public void setFill(int f){
		this.fill = f;
	}
	public int getFill(){
		return this.fill;
	}
	public void setMouse(boolean m){
		this.mouse = m;
	}
	public boolean getMouse(){
		return this.mouse;
	}
	public void setScore(int s){
		this.score = s;
	}
	public int getScore(){
		return this.score;
	}
	public void setName(String n){
		this.name = n;
	}
	public String getName(){
		return name;
	}
	public boolean getPaused(){
		return paused;
	}
	public void setPaused(boolean p){
		this.paused = p;
	}
	public boolean getHitSound(){
		return hitSound;
	}
	public void setHitSound(boolean set){
		this.hitSound = set;
	}
	public boolean getProceed(){
		return proceed;
	}
	public void setProceed(boolean p){
		this.proceed = p;
	}
	public void setReiniciar(boolean r){
		this.reiniciar = r;
	}
	public boolean getReiniciar(){
		return reiniciar;
	}
	public boolean getSpeed(){
		return speed;
	}
	public void setSpeed(boolean s){
		this.speed = s;
	}
	public void clearHandler(){
		handler.object.clear();
	}	
	public static void main(String[] args){
		new Game();
	}
}