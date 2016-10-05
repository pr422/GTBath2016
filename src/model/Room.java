package model;
import java.util.ArrayList;
import java.util.Random;

public class Room {
	private char[][] room;
	private Coin coin;
	private int height;
	private int width;
	private boolean isExit;
	private Game game;
	private boolean isGame;
	private final int MAXGAME = 6;
	private Player player;
	private ArrayList<int[]> invalidPositions;
	private ArrayList<int[]> usedPositions;
	private ArrayList<String> objectList;
	private ArrayList<String> enemyList;
	
	public Room(){
		this.usedPositions = new ArrayList();
		this.invalidPositions = new ArrayList();
		addInvalidPositions();
		this.player = new Player();
		updateUsedPositions(player.getPosition());
		this.coin = new Coin(invalidPositions, usedPositions);
		updateUsedPositionsList(coin.getCoinPositions());
		this.game = new Game(player);
		setHeight(0);
		setWidth(0);
		setRoom(null);
		setIsExit(false);
		this.objectList = new ArrayList();
		this.enemyList = new ArrayList();
	}
	
	public Room(int height, int width, boolean isExit){
		this.usedPositions = new ArrayList();
		this.invalidPositions = new ArrayList();
		addInvalidPositions();
		this.player = new Player();
		updateUsedPositions(player.getPosition());
		this.coin = new Coin(invalidPositions, usedPositions);
		updateUsedPositionsList(coin.getCoinPositions());
		this.game = new Game(player);
		setHeight(height);
		setWidth(width);
		setRoom(room);
		setIsExit(isExit);
		this.objectList = new ArrayList();
		this.enemyList = new ArrayList();
	}
	
	public Room(Room room){
		this.usedPositions = room.getUsedPositions();
		this.invalidPositions = room.getInvalidPositions();
		this.room = room.getRoom();
		this.player = room.getPlayer();
		this.coin = room.getCoin();
		this.game = room.getGame();
		this.height = room.getHeight();
		this.width = room.getWidth();
		this.isExit = room.getIsExit();
		this.isGame = room.getIsGame();
		this.player = room.getPlayer();
		this.coin = room.getCoin();
		this.objectList = room.getObjectList();
		this.enemyList = room.getEnemyList();
		
	}
	
	protected void printRoom(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				System.out.print(room[i][j]);
			}
			System.out.println("");
		}
	}
	
	protected void setRoom(char[][] theRoom){
		room = theRoom;
		
	}
	
	private void updateRoom(){
		updatePlayerPosition();
		updateCoinPosition();
		updateObjectPosition();
	}
	
	protected void updatePlayerPosition(){
		int[] p = player.getPosition();
		if(p != null){
			int x = p[0];
			int y = p[1];
			this.room[x][y] = 'P';
		}
	}
	
	protected void removeContent(int[] oldPlayerPosition){
		int x = oldPlayerPosition[0];
		int y = oldPlayerPosition[1];
		this.room[x][y] = '.';
	}
	
	protected String movePlayer(int[] newPosition){
//		check duplicate position with used position
		String msg = "";
		if(isMoveToWall(newPosition)){
			msg = "Player cannot move to a wall";
		}else if(isMoveToDoor(newPosition)){
			msg = moveToDoorMsg(newPosition);
		}
		return msg;
	}
	
	protected boolean isMoveToCoin(int[] newPosition){
		boolean isMatch = false;
		int x = newPosition[0];
		int y = newPosition[1];
		char c = room[x][y];
		if(c == 'C'){
			collectCoinPosition(newPosition);
			this.room[x][y] = '.';
			isMatch = true;
		}
		return isMatch;
	}
	
	protected boolean isMoveToObject(int[] newPosition){
		boolean isMatch = false;
		int x = newPosition[0];
		int y = newPosition[1];
		char c = room[x][y];
		if(c == 'O'){
			this.room[x][y] = '.';
			isMatch = true;
		}
		return isMatch;
	}
	
	protected boolean isMoveToExit(int[] newPosition){
		boolean isMatch = false;
		int x = newPosition[0];
		int y = newPosition[1];
		char c = room[x][y];
		if(c == 'E'){
			isMatch = true;
		}
		return isMatch;
	}
	
	private boolean isMoveToWall(int[] newPosition){
		boolean isMatch = false;
		int x = newPosition[0];
		int y = newPosition[1];
		char c = room[x][y];
		if(c == 'X'){
			isMatch = true;
		}
		return isMatch;
	}
	
	private boolean isMoveToDoor(int[] newPosition){
		boolean isMatch = false;
		int x = newPosition[0];
		int y = newPosition[1];
		char c = room[x][y];
		if(c == 'D'){
			System.out.println("move to door");
			isMatch = true;
		}
		return isMatch;
	}
	
	private String moveToDoorMsg(int[] newPosition){
		String wallMsg = "";
		int x = newPosition[0];
		int y = newPosition[1];
		if(x == 0 && y == 3){
			wallMsg = "DOOR N";
		}else if(x == 3 && y == 0){
			wallMsg = "DOOR W";
		}else if(x == 3 && y == 6){
			wallMsg = "DOOR E";
		}else if(x == 6 && y == 3){
			wallMsg = "DOOR S";
		}
		return wallMsg;
	}
	
	private void updateCoinPosition(){
		ArrayList<int[]> list = new ArrayList<int[]>(coin.getCoinPositions());
		System.out.println("update coin position: " + coin.getCoinPositions().size());
		for(int[] p: list){
			int x = p[0];
			int y = p[1];
			this.room[x][y] = 'C';
		}
	}
	
	protected void collectCoinPosition(int[] coinPosition){
		System.out.println("oldposition" + coinPosition[0] + " " + coinPosition[1]);
		System.out.println("before remove: " + coin.getCoinPositions().size());
		coin.removeCoinPosition(coinPosition);
		System.out.println("After remove: " + coin.getCoinPositions().size());
		removeUsedPosition(coinPosition);
	}
	
	private void updateObjectPosition(){
		if(getIsGame()){
			System.out.println("is game");
			System.out.println("game exist" + getGame());
			System.out.println("");
			int[] p = getGameObject().getPosition();
			int x = p[0];
			int y = p[1];
			if(x == 0 && y == 0){
				
			}else{
				this.room[x][y] = 'O';
			}
		}
	}
	
	protected void collectObjectPosition(){
		System.out.println("object is collected");
		int[] tempObjectPosition = game.getGameObject().getPosition();
		removeUsedPosition(tempObjectPosition);
		tempObjectPosition[0] = 0;
		tempObjectPosition[1] = 0;
		game.getGameObject().setPosition(tempObjectPosition);
	}
	
	protected char[][] getRoom(){
		updateRoom();
//		System.out.println("room is update" + player.getPosition()[0] + " " + player.getPosition()[1]);
		return room;
	}
	
	private int getNumberOfCoin(){
		return coin.getNumberOfCoin();
	}
	
	private Coin getCoin(){
		return coin;
	}
	
	private int randomNumber(int min, int max){
		Random rand = new Random();
		int randNum = rand.nextInt(max) + min;
//		System.out.println("random number produced : " + randNum);
		
		return randNum;
	}
	
	private void setHeight(int height){
		this.height = height;
	}
	
	protected int getHeight(){
		return height;
	}
	
	private void setWidth(int width){
		this.width = width;
	}
	
	protected int getWidth(){
		return width;
	}
	
	private void setIsExit(boolean isExit){
		this.isExit = isExit;
	}
	
	protected boolean getIsExit(){
		return isExit;
	}
	
	protected void setIsGame(int numberOfGame, boolean isGame){
		if(!isGame){
			int randNum = randomNumber(1,2);
				if((randNum == 1 && !getIsExit()) && numberOfGame != MAXGAME){
					this.isGame = true;
					this.game = new Game(getPlayer());
					isMatchEnemyName();
					game.getGameObject().randomPosition(getInvalidPositions(), getUsedPositions());
					isMatchObjectName();
					System.out.println("setIsGame random object position " + getGameObject().getPosition()[0] + getGameObject().getPosition()[1]);
					updateUsedPositions(game.getGameObject().getPosition());
				}
				else if(getIsExit()){
					this.isGame = true;
					this.game = new Game(getPlayer());
					getEnemy().setFinalBoss();
					int[] objectPosition = {3,0};
					game.getGameObject().setPosition(objectPosition);
					updateUsedPositions(game.getGameObject().getPosition());
				}
				else{
					this.isGame = false;
				}
		}else{
			this.isGame = true;
		}
	}
	
	protected boolean getIsGame(){
		return isGame;
	}
	
	protected int getMaxGame(){
		return MAXGAME;
	}
	
	protected Game getGame(){
		return game;
	}
	
	protected GameObject getGameObject(){
		return game.getGameObject();
	}
	
	protected Enemy getEnemy(){
		return game.getEnemy();
	}
	
	protected Player getPlayer(){
		return player;
	}
	
	protected Player getGamePlayer(){
		return game.getPlayer();
	}
	
	private void addInvalidPositions(){
		int[] tempArray = new int[2];
		tempArray[0] = 0;
		tempArray[1] = 3;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 3;
		tempArray[1] = 0;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 3;
		tempArray[1] = 6;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 6;
		tempArray[1] = 3;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 5;
		tempArray[1] = 3;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 3;
		tempArray[1] = 5;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 3;
		tempArray[1] = 1;
		invalidPositions.add(tempArray);
		
		tempArray[0] = 1;
		tempArray[1] = 3;
		invalidPositions.add(tempArray);
		System.out.println("invalidPositions size " + invalidPositions.size());
	}
	private void setInvalidPositions(ArrayList<int[]> invalidPositions){
		this.invalidPositions = invalidPositions;
	}
	
	private ArrayList<int[]> getInvalidPositions(){
		return invalidPositions;
	}
	
	private void addUsedPosition(int[] position){
		usedPositions.add(position);
	}
	
	private void updateUsedPositions(int[] positions){
		usedPositions.add(positions);
		System.out.println("usedPositions.size" + usedPositions.size());
	}
	
	private void updateUsedPositionsList(ArrayList<int[]> positions){
		for(int[] p: positions){
			usedPositions.add(p);
		}
		System.out.println("usedPositions.size" + usedPositions.size());
	}
	
	private void removeUsedPosition(int[] oldPosition){
		System.out.println("remove usedPositions.size" + usedPositions.size());
		for ( int i = 0;  i < usedPositions.size(); i++){
            int[] p = usedPositions.get(i);
            
            if(p!= null && p[0] == oldPosition[0] && p[1] == oldPosition[1])
            {
                usedPositions.remove(i);
            }
        }
	}
	
	private void setUsedPositions(ArrayList<int[]> usedPositions){
		this.usedPositions = usedPositions;
	}
	
	protected ArrayList<int[]> getUsedPositions(){
		return usedPositions;
	}
	
	private void setObjectList(ArrayList<String> objectList){
		this.objectList = objectList;
	}
	
	private ArrayList<String> getObjectList(){
		return objectList;
	}
	
	private void setEnemyList(ArrayList<String> enemyList){
		this.enemyList = enemyList;
	}
	
	private ArrayList<String> getEnemyList(){
		return enemyList;
	}
	
	private void isMatchEnemyName(){
		String enemyName = getEnemy().randomEnemyName();
		if(isMatchArrayListString(getEnemyList(), enemyName)){
			getEnemy().randomEnemyName();
		}else{
			enemyList.add(enemyName);
			getEnemy().setName(enemyName);
		}
	}
	
	private void isMatchObjectName(){
		String objectName = getGameObject().randomObjectName();
		if(isMatchArrayListString(getObjectList(), objectName)){
			 getGameObject().randomObjectName();
		}else{
			objectList.add(objectName);
			 getGameObject().setName(objectName);
		}
	}
	
	private boolean isMatchArrayListString(ArrayList<String> arr, String str){
		boolean isMatch = false;
		for(int i = 0; i < arr.size(); i++){
			if(arr.get(i).equals(str)){
				isMatch = true;
			}
		}
		return isMatch;
	}
	
}
