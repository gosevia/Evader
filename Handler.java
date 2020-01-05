package proyecto;
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){ //Ejecuta el método tick para cada objeto dentro de la lista
		for(int i=0; i<object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}	
	}
	public void render(Graphics g){ //Ejecuta el método render para cada objeto dentro de la lista
		for(int i=0; i< object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
}