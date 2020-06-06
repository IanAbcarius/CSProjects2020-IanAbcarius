import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.Font;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener {

	/* Attributes a.k.a. Instance Variables */
	int w = 1300, h = 900;
	Map map = new Map("lv1.in");
	Room cRoom = map.getRoom(w, h, 50 , 600);
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		cRoom.updateRoom();
		cRoom.paint(g);		
	}// end of paint method - put code above for anything dealing with drawing -

	public void update() {
		cRoom.updateRoom();
		
		//portal stuff
		if(cRoom.p.inPortal(cRoom.grid)==1){
			map.setcR(cRoom.grid[cRoom.p.getR()][cRoom.p.getC()].getmR());
			map.setcC(cRoom.grid[cRoom.p.getR()][cRoom.p.getC()].getmC());
			cRoom = map.getRoom(w, h, cRoom.grid[cRoom.p.getR()][cRoom.p.getC()].getnX(), cRoom.grid[cRoom.p.getR()][cRoom.p.getC()].getnY());
		}	
//		}else if(cRoom.p.inPortal(cRoom.grid)==2) {
//			if(cRoom.p.inPortal(cRoom.grid)==1){
//				map.setcR(cRoom.grid[cRoom.p.getR()+1][cRoom.p.getC()].getmR());
//				map.setcC(cRoom.grid[cRoom.p.getR()+1][cRoom.p.getC()].getmC());
//				cRoom = map.getRoom(w, h, cRoom.grid[cRoom.p.getR()+1][cRoom.p.getC()].getnX(), cRoom.grid[cRoom.p.getR()][cRoom.p.getC()].getnY());
//				
//				}
//		}
			
		
		
		
	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
		
	}

	/* Instantiate any attributes here (instance variables */
	public Driver() {

		JFrame f = new JFrame();
		f.setTitle("Hollow Knight");
		f.setSize(w, h);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this); //listen for keypresses on this frame

		f.add(this);
		t = new Timer(20, this);
		t.start();
//		t.stop();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
	
	
	Timer t;
	@Override
	 public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 90) {
		cRoom.getPlayer().jump(cRoom.grid);
			
		}

		if (e.getKeyCode() == 37) {
			cRoom.getPlayer().attL = true;
			cRoom.getPlayer().attR = false;
			cRoom.getPlayer().attU = false;

			cRoom.getPlayer().xA=-0.25;
		}
		if (e.getKeyCode() == 38) {
			cRoom.getPlayer().attU = true;
			cRoom.getPlayer().attR = false;
			cRoom.getPlayer().attL = false;
		}
		if (e.getKeyCode() == 88) {
			if(!cRoom.getPlayer().isDying) {
				cRoom.getPlayer().isAttacking = true;
			}
		}
		if (e.getKeyCode() == 39) {
			cRoom.getPlayer().attR = true;
			cRoom.getPlayer().attU = false;
			cRoom.getPlayer().attL = false;
			
			cRoom.getPlayer().xA=0.25;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 90) {
			cRoom.getPlayer().yV=0;
		}
		if (e.getKeyCode() == 88) {
			cRoom.getPlayer().isAttacking = false;
		}
		if (e.getKeyCode() == 37) {
			cRoom.getPlayer().xV=0;
			cRoom.getPlayer().xA=0;
		}
		if (e.getKeyCode() == 38) {
			cRoom.getPlayer().attU = false;
		}

		if (e.getKeyCode() == 39) {
			cRoom.getPlayer().xA=0;
			cRoom.getPlayer().xV=0;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}//36 by 52
