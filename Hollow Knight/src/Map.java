import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Map {
	
	 String[][] layout;
	 String mapfile;
	 int cR;
	 int cC;
	 public Map(String file){
		 layout = new String[0][0];
		 mapfile = file;
			try
	        {
	            BufferedReader in = new BufferedReader(new FileReader(new File(mapfile)));
	           

	            int line = 0;
	            for (String x = in.readLine(); x != null; x = in.readLine())
	            {
	                line++;
	                if(line == 1){
	               	 String[] tokens = x.split(" ");
	               	 int a =Integer.parseInt(tokens[0]);
	               	 int b = Integer.parseInt(tokens[0]);
	               	layout = new String[a][b];
	               	cR = a-1;
	               	cC = 0;
	                }
	                else{
	               	 String[] tokens = x.split(" ");
	               	 for(int i = 0; i<tokens.length; i++){
//	               		 System.out.println(tokens[i]);
	               		 layout[line-2][i] = tokens[i];
	               	 }
	                }
	               
	            }
	        } catch (IOException e)
	        {
	            System.out.println("File I/O error!");
	        }
			
		
            
           
             
             
         }
		public Room getRoom(int w, int h){
			System.out.println(layout[cR][cC]);
			return (new Room(layout[cR][cC], w, h));
		}
		
	 }

