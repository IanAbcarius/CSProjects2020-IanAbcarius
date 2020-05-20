import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Room {
	Platform[][] grid;

	BufferedReader in;
	String roomfile;
	int w;
	int h;

	Player p;
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
					px = Integer.parseInt(tokens[2]);
					py = Integer.parseInt(tokens[3]);
					gravity = Double.parseDouble(tokens[4]);
					hs = Double.parseDouble(tokens[5]);
					vs = Double.parseDouble(tokens[6]);

					p = new Player(px, py, 30, 50, 10);
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
	
	public void updateRoom(Graphics g) {
		p.updateKinematics(phy,grid);
		p.move(grid,g);
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
		g.setColor(Color.ORANGE);
		int a =1;
		int b =0;
		g.drawRect(grid[a][b].getX(),grid[a][b].getY(),grid[a][b].getW(),grid[a][b].getH());
	}

	public Platform getPlatform(int r, int c) {
		return grid[r][c];
	}
}
