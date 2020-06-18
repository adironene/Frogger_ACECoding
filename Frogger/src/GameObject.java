import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

public class GameObject {

	private Rectangle rect;
	
	private Image image;
	
	//private SoundClip sound;  // MUST be a class for this!!
	public GameObject(int x, int y, int width, int height){
		rect = new Rectangle(x,y,width,height);




	}

	protected Image getImage(String fn) {
		Image img = null;
		try {

			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public void draw(Graphics g, String fn) {

	}
	public Rectangle getRect(){
		return rect;
	}

	
	
}
