import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class FroggerGame {
	
	// what are the things you see in the game?
	
	
	GameObject frog = new frog(0, 0, 60, 60);
	final int FROG_SIZE = 60;
	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		System.out.println("In frogger game (keyHit): "+s);

            if (s.equals("left")) {
                frog.getRect().translate(-FROG_SIZE, 0);
            }
            if (s.equals("right")) {
                frog.getRect().translate(FROG_SIZE, 0);
            }
            if (s.equals("up")) {
                frog.getRect().translate(0, -FROG_SIZE);
            }
            if (s.equals("down")) {
                frog.getRect().translate(0, FROG_SIZE);
            }

		
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

	public void draw(Graphics g, int wi, int height){
//	    System.out.print(wi + " "+ height);
		g.drawImage(getImage("river.png"),0,300,700,100,null);
		g.drawImage(getImage("unnamed.png"), (int)frog.getRect().getX(), (int)frog.getRect().getY(),FROG_SIZE,FROG_SIZE, null);
		System.out.println("the frog is at x: "+ (int)frog.getRect().getX()+" the frog is at y: "+ (int)frog.getRect().getY());

	}
    public void move(){

    }
	
	
	
	
	
	
	
	
}
