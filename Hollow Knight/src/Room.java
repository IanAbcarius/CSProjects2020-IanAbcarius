import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;

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
	Enemy aspid;
	Physics phy;

	public Room(String file, int wi, int hi) {
		roomfile = file;

		grid = new Platform[0][0];
		w = wi;
		h = hi;
		int a = 0;
		int b = 0;
		int px = 0;
		int py = 0;
		int ix;
		int iy;
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
					ix = Integer.parseInt(tokens[2]);
					iy = Integer.parseInt(tokens[3]);
					px = ix;
					py = iy;
					gravity = Double.parseDouble(tokens[4]);
					hs = Double.parseDouble(tokens[5]);
					vs = Double.parseDouble(tokens[6]);

					p = new Player(px, py, ix,iy, 30, 50, "knight.png");
					Hornet = new NPC("hornet.png");
					aspid = new Enemy(500, 400, "aspid.png", p); // x, y, image are parameters
					
					phy = new Physics(gravity, hs, vs);
					grid = new Platform[a][b];
					
					continue;
				}

				String[] tokens = x.split(" ");
				for (int i = 0; i < tokens.length; i++) {
//						System.out.println(i);
					if (tokens[i].equals("1")) {
						grid[line - 2][i] = new Platform((line - 2), i, w / b, h / a, i * w / b, (line - 2) * h / a,true);
					} else if (tokens[i].equals("0")) {
						grid[line - 2][i] = new Platform((line - 2), i, w / b, h / a, i * w / b, (line - 2) * h / a,false);
					} else {
						grid[line - 2][i] = new Obstacles((line - 2), i, w / b, h / a, i * w / b, (line - 2) * h / a,true,
								Integer.parseInt(tokens[i]));
					}

				}

			}
		} catch (IOException e) {
			System.out.println("Room File Reader Error");
		}
	}
	
	public void updateRoom() {
		p.updateKinematics(phy,grid);
		p.move(grid);
		p.deathCheck();
		if(p.checkAttack(aspid) && p.isAttacking) {
			aspid.alive = false; //aspid not painting if alive is false
		}
		aspid.checkPlayer(p);
		aspid.checkPlatforms(grid);
	}
	public void paint(Graphics g) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
//				System.out.println(j);
				if (grid[i][j] != null) {
					grid[i][j].paint(g);
				}
			}
		}
		p.paint(g);
		Hornet.paint(g);
		aspid.paint(g, p);
		if(p.collidedNPC(Hornet, g)) { // if hornet and player intersect
			Font myFont = new Font("Serif", Font.BOLD, 25);
			g.setFont(myFont);
			g.setColor(Color.black);
			g.drawString("arrow keys to move, z to jump (can double jump)", 400, 100);
		}
		g.setColor(Color.ORANGE);
		int a =1;
		int b =0;
		g.drawRect(grid[a][b].getX(),grid[a][b].getY(),grid[a][b].getW(),grid[a][b].getH());
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
