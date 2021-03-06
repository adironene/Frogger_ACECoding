import com.sun.nio.sctp.HandlerResult;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.*;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class FroggerGameRunner {
	private JPanel panel;

	private Timer timer;
	private int ticks;
	
	// Notice this intuitive method for finding the screen size 
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) (screenSize.getWidth()),HEIGHT=(int) (screenSize.getHeight());
	private FroggerGame game = new FroggerGame(WIDTH, HEIGHT);
	public FroggerGameRunner() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					start();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}
			}
		});
	}

	public static void main(String[] args) {
		new FroggerGameRunner();
	}

	private void start() {
		JFrame frame = new JFrame("Frogger");
		System.out.println("width is " + WIDTH + " height is " + HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}
		};
		panel.setBackground(new Color(20, 15, 120));
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.setLocation(WIDTH/10, HEIGHT/10);

		mapKeyStrokesToActions(panel);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		panel.requestFocusInWindow();
		timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateGame();
				panel.repaint();
			}
		});
		timer.start();
	}

	// this method is called every time the timer goes off (which right now is every 10 milliseconds = 100 times per second
	protected void updateGame() {
		ticks++;// keeps track of the number of times the timer has gone off
		if(ticks %100 == 0) {
			System.out.println(ticks/100+" seconds");
			game.move();
		}

		
		
	}

	private void mapKeyStrokesToActions(JPanel panel) {

		// A map is an Data storage interface which defines
		// an association of a key with a value
		// to "add" to a map you use the "put" method
		// to "get" from a map you use "get(key)" and the 
		// value associated with the key is returned (or null)
		ActionMap map = panel.getActionMap();
		InputMap inMap = panel.getInputMap();

		// code below associates pressing the up arrow with the command "up"
		// essentially creating the command "up" being broadcast any time the 
		// up key is hit
		inMap.put(KeyStroke.getKeyStroke("pressed UP"), "up");
		inMap.put(KeyStroke.getKeyStroke((char) KeyEvent.VK_UP), "up");
		// code below associates the "up" action with anything in the
		// actionPerformed method.  Right now, it just prints something
		map.put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hit("up");
			}


		});

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		panel.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("left");
			}
		});

        panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
        panel.getActionMap().put("right",new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                hit("right");
            }
        });

        panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
        panel.getActionMap().put("down",new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                hit("down");
            }
        });

	}
	public  void hit(String s) {
		game.keyHit(s);
		panel.repaint();
	}
	protected void drawGame(Graphics g) {
		game.draw(g);

	}

}
