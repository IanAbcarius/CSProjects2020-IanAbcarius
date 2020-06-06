import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.filechooser.FileNameExtensionFilter;

public class Room {
	Platform[][] grid;

	BufferedReader in;
	String roomfile;
	int w;
	int h;
	int ix;
	int iy;
	Player p;
	NPC Hornet;
	Background background;
	ArrayList<Enemy> aspid;
	Slash up;
	Masks mask;
	Slash right;
	Slash left;
	Physics phy;
	ArrayList<Boss> aspidBoss;

	public Room(String file, int wi, int hi, int ix, int iy) {
		roomfile = file;

		grid = new Platform[0][0];
		w = wi;
		h = hi;
		int a = 0;
		int b = 0;
		int px = 0;
		int py = 0;
		this.ix = ix;
		this.iy = iy;
		double gravity = 0;
		double hs = 0;
		double vs = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(roomfile)));
			int line = 0;
			grid = new Platform[0][0];
			for (String x = in.readLine(); x != null; x = in.readLine()) {
				line++;
				if (line == 1) {
					String[] tokens = x.split(" ");
					a = Integer.parseInt(tokens[0]);
					b = Integer.parseInt(tokens[1]);
					px = ix;
					py = iy;
					gravity = Double.parseDouble(tokens[4]);
					hs = Double.parseDouble(tokens[5]);
					vs = Double.parseDouble(tokens[6]);

					p = new Player(px, py, ix,iy, 30, 50);
					if(roomfile.equals("L1R1.in")) {
						Hornet = new NPC("hornet_sitting.gif");
					}
					mask = new Masks("mask.png");
					if(roomfile.equals("L1R10.in")) {
						background = new Background("bossRoom.png");
					} else {
						background = new Background("background.jpg");
					}
					aspid = new ArrayList<Enemy>();
					aspidBoss = new ArrayList<Boss>();
					removeEnemiesBosses();
					left = new Slash("slash_left.gif", 1);
					right = new Slash("slash_right.gif", 2);
					up = new Slash("slash_up.gif", 3);
					phy = new Physics(gravity, hs, vs);
					grid = new Platform[a][b];
					continue;
				}
				if(line>37) {
					String[] tokens = x.split(" ");
					int aX = Integer.parseInt(tokens[0]);
					int aY = Integer.parseInt(tokens[1]);
					int type = Integer.parseInt(tokens[2]);
					System.out.println(type);
					if(type == 2) {
						aspidBoss.add(new Boss(aX, aY, "aspid_boss.gif", p)); // x, y, image are parameters
					} else {
						aspid.add(new Enemy(aX, aY, "aspid_2.gif", p)); // x, y, image are parameters
					}
					continue;
				}
				if (line > 1 && line <= 37) {
					String[] tokens = x.split(" ");
					for (int i = 0; i < tokens.length; i++) {
//						System.out.println(i);
						if (tokens[i].equals("1")) {
							grid[line - 2][i] = new Platform((line - 2), i, w / b, h / a, i * w / b, (line - 2) * h / a,
									true);
						} else if (tokens[i].equals("0")) {
							grid[line - 2][i] = new Platform((line - 2), i, w / b, h / a, i * w / b, (line - 2) * h / a,
									false);
						} else if (tokens[i].equals("2")) {
							grid[line - 2][i] = new Obstacles((line - 2), i, w / b, h / a, i * w / b,
									(line - 2) * h / a, true, Integer.parseInt(tokens[i]));
						} else if (tokens[i].equals("3")) {
							grid[line - 2][i] = new Obstacles((line - 2), i, w / b, h / a, i * w / b,
									(line - 2) * h / a, true, Integer.parseInt(tokens[i]));
						} else if (tokens[i].equals("4")) {
							grid[line - 2][i] = new Obstacles((line - 2), i, w / b, h / a, i * w / b,
									(line - 2) * h / a, true, Integer.parseInt(tokens[i]));
						} else if (tokens[i].equals("5")) {
							grid[line - 2][i] = new Obstacles((line - 2), i, w / b, h / a, i * w / b,
									(line - 2) * h / a, true, Integer.parseInt(tokens[i]));
						} else {
							grid[line - 2][i] = new Portal((line - 2), i, w / b, h / a, i * w / b, (line - 2) * h / a,
									tokens[i]);
						}

					}
				}

			}
		} catch (IOException e) {
			System.out.println("Room File Reader Error");
		}
		//aspid.add(new Enemy(500, 400, "aspid_2.gif", p)); // x, y, image are parameters
		//aspidBoss.add(new Boss(500, 500, "aspid_boss.gif", p)); // x, y, image are parameters
	}
	
	
	public void removeEnemiesBosses() {
		for(int i = 0; i<aspidBoss.size();i++) {
			aspidBoss.remove(i);
			i--;
		}
		for(int i = 0; i<aspid.size();i++) {
			aspid.remove(i);
			i--;
		}
	}
	public void updateRoom() {
		
		p.updateKinematics(phy,grid);
		p.move(grid);
		p.deathCheck();
		p.attacks(left, right, up, p);
		for(int i = 0; i<aspid.size(); i++) {
			aspid.get(i).checkPlayer(p);
			aspid.get(i).checkPlatforms(grid);
			p.touchAspid(aspid.get(i));
			if(p.hitAspid(aspid.get(i))) {
				aspid.get(i).setAlive(false);
				aspid.remove(i);
				i--;
			}
		}
		for(int i = 0; i<aspidBoss.size(); i++) {
			aspidBoss.get(i).checkPlayer(p);
			aspidBoss.get(i).checkPlatforms(grid);
			p.hitBoss(aspidBoss.get(i));
			p.touchBoss(aspidBoss.get(i));
			if(!aspidBoss.get(i).isAlive()) {
				aspidBoss.remove(i);
				i--;
			}
		}
	}
	public void paint(Graphics g) {
		background.paint(g);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
//				System.out.println(j);
				if (grid[i][j] != null) {
					grid[i][j].paint(g);
				}
			}
		}
		
		if(roomfile.equals("L1R1.in")) {
			Hornet.paint(g);
		}
		p.paint(g, grid);
		left.paint(g);
		right.paint(g);
		up.paint(g);
		for(int i = 0; i<aspid.size(); i++) {
			aspid.get(i).paint(g, p);
		}
		Font myFont = new Font("Serif", Font.BOLD, 25);
		g.setFont(myFont);
		g.setColor(Color.white);
		for(int i = 0; i<aspidBoss.size(); i++) {
			aspidBoss.get(i).paint(g, p);
			g.drawString(aspidBoss.get(i).getHealth()+ " boss health", 525, 200);
		}
		if(roomfile.equals("L1R1.in")) {
			if(p.collidedNPC(Hornet, g)) { // if hornet and player intersect
				g.drawString("arrow keys to move, z to jump (can double jump), x to attack", 400, 100);
			}
		}
		mask.paint(g);
		g.drawString(p.lives + "", 75, 80);
		g.setColor(Color.ORANGE);
		int a =1;
		int b =0;
		g.drawRect(grid[a][b].getX(),grid[a][b].getY(),grid[a][b].getW(),grid[a][b].getH());
	}

	public boolean portalCheck() {
		
		
		
		return false;
	}
	
	public Platform getPlatform(int r, int c) {
		return grid[r][c];
	}
	public Player getPlayer() {
		return p;
	}

	public Platform[][] getGrid() {
		return grid;
	}

	public void setGrid(Platform[][] grid) {
		this.grid = grid;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public String getRoomfile() {
		return roomfile;
	}

	public void setRoomfile(String roomfile) {
		this.roomfile = roomfile;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getIx() {
		return ix;
	}

	public void setIx(int ix) {
		this.ix = ix;
	}

	public int getIy() {
		return iy;
	}

	public void setIy(int iy) {
		this.iy = iy;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public NPC getHornet() {
		return Hornet;
	}

	public void setHornet(NPC hornet) {
		Hornet = hornet;
	}

	public Physics getPhy() {
		return phy;
	}

	public void setPhy(Physics phy) {
		this.phy = phy;
	}
}
