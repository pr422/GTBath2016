package model;

import java.util.ArrayList;

public class Model {
	
	private Map map;
	private Room[][] wholeRoom;
	private Room room;
	private final int MAXROOM = 16;
	private int posX;
	private int posY;
	private int collectedCoin;
	private ArrayList<String> collectedObject;
	private int potion;
	private int block;
	public Model(){
		map = new Map();
		wholeRoom = null;
		setWholeRoom(map.getMap());
		setPosX(3);
		setPosY(3);
		setRoom(getPosX(),getPosY());
		collectedCoin = 0;
		potion = 0;
		block = 0;
		collectedObject = new ArrayList<String>();
	}
	
	public void setWholeRoom(Room[][] wholeRoom){
		int l = MAXROOM/4;
		this.wholeRoom = new Room[l][l];
		for(int i = 0; i < (l); i++){
			for(int j = 0; j < (l); j++){
				this.wholeRoom[i][j] = wholeRoom[i][j];
			}
		}
	}
	
	public Room[][] getWholeRoom(){
		return wholeRoom;
	}
	
	public void setRoom(int i, int j){
		setPosX(i);
		setPosY(j);
		this.room = wholeRoom[i][j];
	}
	
	public Room getRoom(int i, int j){
		return room;
	}
	
	public char[][] getRoomContent(){
		return room.getRoom();
	}
	
	public Enemy getEnemy(){
		return room.getEnemy();
	}
	
	public GameObject getGameObject(){
		return room.getGameObject();
	}
	
	public Game getGame(){
		return room.getGame();
	}
	
	public Player getPlayer(){
		return room.getPlayer();
	}
	
	private Player getGamePlayer(){
		return room.getGamePlayer();
	}
	
	public String move(char keyPressed) {
		int[] tempPosition = getPlayer().getPosition();
		int[] newPosition = new int[2];
		switch(keyPressed){
		case 'w': //north
			newPosition[0] = tempPosition[0] - 1;
			newPosition[1] = tempPosition[1];
			break;
		case 'a': //west
			newPosition[0] = tempPosition[0];
			newPosition[1] = tempPosition[1] - 1;
			break;
		case 'd': //east
			newPosition[0] = tempPosition[0];
			newPosition[1] = tempPosition[1] + 1;
			break;
		case 's': //south
			newPosition[0] = tempPosition[0] + 1;
			newPosition[1] = tempPosition[1];
			break;
		}
		
		String msg = movePlayer(newPosition);
		if(msg.equals("")){
			//update player position
			boolean collectedCoin = room.isMoveToCoin(newPosition);
			boolean objectPosition = room.isMoveToObject(newPosition);
			boolean exitPosition = room.isMoveToExit(newPosition);
			room.removeContent(tempPosition);
			getPlayer().setPosition(newPosition);
			if(exitPosition && getNumberCollectedObject() != 6){
				msg = "Cannot eixt the game, have to collect all objects";
			}
			if(objectPosition || (exitPosition && getNumberCollectedObject() == 6)){
				msg = "OBJECT";
			}
			else if(collectedCoin){
				int coin = getCollectedCoin() + 1;
				getPlayer().setNumberOfCoin(coin);
				setCollectedCoin(coin);
				System.out.println("number of coin collected " + getPlayer().getNumberOfCoin());
			}
			
		}
		else if(msg.startsWith("DOOR")){
			//go to next room
			//set new position
			room.removeContent(tempPosition);
			moveNextRoom(newPosition, msg);
		}
		room.printRoom();
		return msg;
	}

	private void moveNextRoom(int[] newPosition, String wallMsg){
		int x = getPosX();
		int y = getPosY();
		String[] moveRoom = wallMsg.trim().split(" ");
		char direction = moveRoom[1].charAt(0);
		switch(direction){
		case 'N':
			setRoom(x-1,y);
			break;
		case 'W':
			setRoom(x,y-1);
			break;
		case 'E':
			setRoom(x,y+1);
			break;
		case 'S':
			setRoom(x+1,y);
			break;
		}
		moveNextRoomPosition(direction);
	}
	
	private void moveNextRoomPosition(char direction){
		//player position
		int[] newPosition = new int[2];
		switch(direction){
		case 'N':
			newPosition[0] = 5;
			newPosition[1] = 3;
			break;
		case 'W':
			newPosition[0] = 3;
			newPosition[1] = 5;
			break;
		case 'E':
			newPosition[0] = 3;
			newPosition[1] = 1;
			break;
		case 'S':
			newPosition[0] = 1;
			newPosition[1] = 3;
			break;
		}
		getPlayer().setPosition(newPosition);
//		System.out.println(newPosition[0] + " " +newPosition[1]);
	}
	
	public String getEnemyName() {
		String enemyName = getEnemy().getName();
		return enemyName;
	}

	public int enemyHealth() {
		int hp = getEnemy().getHp();
		return hp;
	}

	public String getGameObjectName() {
		String objectName = getGameObject().getName();
		return objectName;
	}

	public void setPlayerName(String name){
		getPlayer().setName(name);
	}
	
	public String getPlayerName() {
		String playerName = getPlayer().getName();
		return playerName;
	}

	public int getPlayerHealth() {
		int hp = getGamePlayer().getHp();
		System.out.println("HP: " + hp);
		return hp;
	}

	public int getPlayerBlock() {
//		int block = getGamePlayer().getBlock();
		int block = getPlayer().getBlock();

		return block;
	}
	
//	public void setPlayerBlock(){
//		getPlayer().setBlock(block);
//	}

	public int getPlayerCoin() {
		int coin = getPlayer().getNumberOfCoin();
		return coin;
	}
	
	public void setPlayerCoin() {
		getPlayer().setNumberOfCoin(collectedCoin);
	}
	
	public int getPlayerObject(){
		int object = getPlayer().getNumberOfObject();
		return object;
	}
	
	public int getPlayerPotion(){
//		int potion = getGamePlayer().getHpPotion();
		int potion = getPlayer().getHpPotion();
		return potion;
	}
	
//	public void collectCoin(int[] playerPosition){
//		room.collectCoinPosition(playerPosition);
//	}
	
//	public void collectObject(int[] playerPosition){
//		room.collectObjectPosition();
//	}
	
	private String movePlayer(int[] newPosition){
		String moveMsg = room.movePlayer(newPosition);
		return moveMsg;
	}
	
	public String parseCommand(int choice){
		System.out.println("Model Block: " + getBlock());
		getPlayer().setBlock(block);

		String msg = parseGameCommand(choice);
//		int numBlock = getGamePlayer().getBlock() - getBlock();
//		int numPotion = getGamePlayer().getHpPotion() - getPotion();
		
//		int newNumBlock = getBlock() + numBlock;
//		int newNumPotion = getPotion() + numPotion;
//		getPlayer().setBlock(block);
		getPlayer().setHpPotion(potion);
//		setBlock(newNumBlock);
//		setPotion(newNumPotion);
		
		
		if(msg.equals("WIN")){
			addCollectedObject(getGameObject().getName());
			room.collectObjectPosition();
			getPlayer().setNumberOfObject(getNumberCollectedObject());
		}
		
		if (msg.equals("")){
			switch(choice){
			case 3:
//				collectedCoin--;
//				setPlayerCoin();
//				potion++;
//				getPlayer().setHpPotion(potion);
				break;
			case 4:
//				collectedCoin -= 3;
//				setPlayerCoin();
//				block++;
//				setPlayerBlock();
				break;
			}
		}
		
		return msg;
	}
	
	
	protected String parseGameCommand(int choice){
		boolean isError = false;
		String msg = "";
		switch(choice){
			case 1: //attack
				getGame().updateEnemyHp();
				getGame().updatePlayerHp(block);
				if (block > 0){
					block--;
				}
				break;
			case 2: //use hp potion
				msg = useHpPotion();
				break;
			case 3: //buy hp potion
				msg = buyHpPotion();
				break;
			case 4: //buy block
				msg = buyBlock();
				break;
		}
		if(!isError && getGame().getIsWin()){
			//if player win return object name
			msg = "WIN";
		}
		else if(isError){
			msg = getGame().getMessage(choice);
		}
		return msg;
	}
	
	
	private String buyBlock(){
		String msg = "";
		final int MAX = 3;
		if(collectedCoin < 3){
			msg = "Insufficient Coin";
		}
		
		else if(block >= MAX){
			msg = "Maximum Number Of Blocks";
		}
//		else if(!getIsWin()){
////			error = true;
//		}
		else{
			msg = "";
			block++;
			collectedCoin -= 3;
			getPlayer().setNumberOfCoin(collectedCoin);
			getPlayer().setBlock(block);
		}
		return msg;
	}
	
	private String buyHpPotion(){
		String msg = "";
		final int MAX = 5;
		if(collectedCoin < 1){
			msg = "Insufficient Coin";
		}
		
		else if(potion >= MAX){
			msg = "Maximum Number Of Potions";
		}
		else{
			msg = "";
			potion++;
			collectedCoin--;
			getPlayer().setNumberOfCoin(collectedCoin);
			getPlayer().setHpPotion(potion);
		}
		return msg;
	}
	

	private String useHpPotion(){
		String msg = "";
		final int MAXHP = 100;
		System.out.println("Potion Count: " + potion);
		if(potion < 1){
			msg = "No Potion";
		}
		else{

			msg = "";
			
			System.out.println("Use HP");
			int hp = getPlayerHealth()+10;
			getGamePlayer().setHp(hp);
			getPlayer().setHp(hp);

			potion--;
			getPlayer().setHpPotion(potion);
		}
		return msg;
	}
	
	private void setPosX(int posX){
		this.posX = posX;
	}
	
	private int getPosX(){
		return posX;
	}
	
	private void setPosY(int posY){
		this.posY = posY;
	}
	
	private int getPosY(){
		return posY;
	}
	
	private void setCollectedCoin(int collectedCoin){
		this.collectedCoin = collectedCoin;
	}
	
	public int getCollectedCoin(){
		return collectedCoin;
	}
	
	private void addCollectedObject(String objectName){
		collectedObject.add(objectName);
	}
	
	private void setCollectedObject(ArrayList<String> collectedObject){
		this.collectedObject = collectedObject;
	}
	
	public ArrayList<String> getCollectedObject(){
		return collectedObject;
	}
	
	public int getNumberCollectedObject(){
		return collectedObject.size();
	}
	
	private void setPotion(int potion){
		this.potion = potion;
	}
	
	public int getPotion(){
		System.out.println("getPotion(): " + potion);
		return potion;
	}
	private void setBlock(int block){
		this.block = block;
	}
	
	public int getBlock(){
		return block;
	}
	
}
