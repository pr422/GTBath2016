package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
	private char[][] wholeMap;
	private Room[][] map;
	private int height; //number of row
	private int width; //number of column
	private static int numberOfGame = 0;
	
	public Map(){
		wholeMap = null;
		height = 0;
		width = 0;
		map = null;
	}
	
	private void openMap(){
		BufferedReader reader = null;
		String mapName = "Map1.txt";
		try {
			reader = new BufferedReader(new FileReader(new File("maps",mapName)));
		} catch (FileNotFoundException e) {
				System.err.println("no valid map name given " + mapName +  " not found");
				System.exit(-1);
		}
		
		try {
			wholeMap = loadMap(reader);
		} catch (IOException e){
			System.err.println("map file invalid or wrongly formatted");
			System.exit(-1);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private char[][] loadMap(BufferedReader reader) throws IOException{
		boolean error = false;
		ArrayList<char[]> tempMap = new ArrayList<char[]>();
		int width = 0;
		String in = reader.readLine();
		
		if (in.charAt(0) == 'X' && in.length() > 1){
			width = in.trim().length();
		}
		
		while (in != null && !error)
		{
			char[] row = new char[in.length()];
			if  (in.length() != width){
				error = true;
				System.out.println("error with width");
			}
			
			for (int i = 0; i < in.length(); i++)
			{
				row[i] = in.charAt(i);
			}

			tempMap.add(row);

			in = reader.readLine();
		}
		
		if (error) {
			return null;
		}
				
		char[][] map = new char[tempMap.size()][width];
		
		for (int i=0;i<tempMap.size();i++){
			map[i] = tempMap.get(i);
		}
		
		setHeight(tempMap.size());
		setWidth(width);
		
		return map;
	}
	
	private void loadRoom(){
		char[][] allRoom = getWholeMap();
		int heightRoom = getHeight()/4;
		int widthRoom = getWidth()/4;
		int a = 0; //index in getWholeMap
		int b = 0; //index in getWholeMap
		int numberOfRoom = 0;
		boolean isExit = true; //is the last room in the map?
		int maxRoom = getHeight()/7;
		map = new Room[maxRoom][maxRoom];
		final int MAXTOTALROOM = 16;
		
		for(int x = 0; x < maxRoom; x++){
			for(int y = 0; y < maxRoom; y++){
				char[][] room = new char[heightRoom][widthRoom];

			if(b == 28){ 
				a = 7*x;
				b = 0;
			}
			for(int i = 0; i < heightRoom; i++){
				for(int j = 0; j < widthRoom; j++){
					room[i][j] = allRoom[i + a][j + b];
				}
			}
			
			if(x != 0 || y != 0){
				isExit = false;
			}
			
			
			Room theRoom = new Room(heightRoom, widthRoom, isExit);
			theRoom.setRoom(room);
			if(x == 3 && y == 3){
				System.out.println("x: " + x + " y:" + y);
				int[] initialPosition = {5,5};
				theRoom.getPlayer().setPosition(initialPosition);
			}
			if((MAXTOTALROOM - numberOfRoom) == (theRoom.getMaxGame() - numberOfGame)){
				theRoom.setIsGame(numberOfGame, true);
				numberOfGame++;
			}
			else{
				theRoom.setIsGame(numberOfGame, false);
				if(theRoom.getIsGame()){
					numberOfGame++;
				}
			}
			
			setRoom(x,y, theRoom);
			a = a;
			b = b + 7;
			numberOfRoom++;	
			}
		}
	}
	
	private void setRoom(int i, int j, Room theRoom){
		map[i][j] = new Room(theRoom);
	}
	
	private void printWholeMap(){
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				System.out.print(wholeMap[i][j]);
			}
			System.out.println("");
		}
	}
	
	private void printEachRoom(){
		for(int i = 0; i < getHeight()/7; i++){
			for(int j = 0; j < getWidth()/7; j++){
				map[i][j].printRoom();
			}
		}
	}
	
	private void setWholeMap(char[][] wholeMap){
		this.wholeMap = wholeMap;
	}
	
	private char[][] getWholeMap(){
		return wholeMap;
	}
	
	private void setHeight(int height){
		this.height = height;
	}
	
	private int getHeight(){
		return height;
	}
	
	private void setWidth(int width){
		this.width = width;
	}
	
	private int getWidth(){
		return width;
	}
	
	private void setMap(Room[][] map){
		this.map = map;
	}
	
	protected Room[][] getMap(){
		openMap();
		loadRoom();
		return map;
	}

}
