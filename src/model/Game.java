package model;

public class Game {
	private Enemy enemy;
	private boolean isWin;
	private Player player;
	private int rounds;

	public Game(){
		this.player = new Player();
		this.enemy = new Enemy();
		setIsWin(false);
		setRounds(3);
	}
	
	public Game(Player player){
		this.player = new Player(player);
		this.enemy = new Enemy();
		setIsWin(false);
		setRounds(3);
	}
	
	protected String parseCommand(int choice, int coin){
		boolean isError = false;
		String msg = "";
		switch(choice){
			case 1: //attack
				updateEnemyHp();
//				updatePlayerHp();
				break;
			case 2: //use hp potion
				isError = useHpPotion();
				break;
			case 3: //buy hp potion
				isError = buyHpPotion();
				break;
			case 4: //buy block
				isError = buyBlock(coin);
				break;
		}
		if(!isError && getIsWin()){
			//if player win return object name
			msg = "WIN";
		}
		else if(isError){
			msg = getMessage(choice);
		}
		return msg;
	}
	
	String getMessage(int choice){
		String msg = "";
		switch(choice){
		case 2:
			msg = "No HP Potion";
			break;
		case 3:
			msg = "Cannot purchase HP potion";
			break;
		case 4:
			msg = "Cannot purchase block";
			break;
		}
		return msg;
	}
	void updateEnemyHp(){
		int tempHp = enemy.getHp() - player.getAd();
		if(tempHp > 0 && !getIsWin()){
			enemy.setHp(tempHp);
			player.randomAd();
		}else{
			setIsWin(true);
			int tempObject = player.getNumberOfObject() + 1;
			player.setNumberOfObject(tempObject);
			player.setMaxHp();
		}
	}
	
	void updatePlayerHp(int block){
		System.out.println("---enemy ad " + enemy.getAd());
		int tempHp = player.getHp() - enemy.getAd();
		System.out.println("Block: " + player.getBlock());
//		if(player.getBlock() > 0){
		if(block > 0){

			//block is used
			//player hp remain the same
			int tempBlock = player.getBlock() - 1;
			player.setBlock(tempBlock);
//			enemy.randomAd();
			System.out.println("Have " + player.getBlock() +" block, use block");
		}else{
			System.out.println(">>>>round : " + getRounds());
			if(tempHp > 0){
				player.setHp(tempHp);
				enemy.randomAd();
				System.out.println("####player hp is updated " + player.getHp());
			}else{
				//hp = 0 player lose
				System.out.println("hp damaged: " + tempHp);
				if(getRounds() > 0){
					int tempRounds = getRounds() - 1;
					setRounds(tempRounds);
				}
				else{
					//game over
					System.exit(0);
				}
			}
		}
	}
	
	boolean useHpPotion(){
		boolean error = false;
		final int MAXHP = 100;
		if(player.getHpPotion() == 0){
			error = true;
		}
		if(player.getHp() == MAXHP){
			error = true;
		}
		else{
			int tempPlayerHp = player.getHp() + 5;
			player.setHp(tempPlayerHp);
		}
		return error;
	}
	
	boolean buyHpPotion(){
		boolean error = false;
		final int MAX = 5;
		if(player.getNumberOfCoin() <= 0){
			error = true;
		}
		else if(player.getHpPotion() == MAX && !getIsWin()){
			error = false;
		}else{
			int tempCoin = player.getNumberOfCoin() - 1;
			int tempHpPotion = player.getHpPotion() + 1;
			player.setNumberOfCoin(tempCoin);
			player.setHpPotion(tempHpPotion);
		}
		return error;
	}
	
	private boolean buyBlock(int coin){
		boolean error = false;
		final int MAX = 3;
		if(coin < 3 || player.getBlock() >= MAX){
			error = true;
		}
//		else if(!getIsWin()){
////			error = true;
//		}
		else{
			error = false;
			int tempCoin = player.getNumberOfCoin() - 3;
			int tempBlock = player.getBlock() + 1;
			player.setNumberOfCoin(tempCoin);
			player.setBlock(tempBlock);
		}
		return error;
	}
	
	protected Player getPlayer(){
		return player;
	}
	//setEnemy
	
	protected Enemy getEnemy(){
		return enemy;
	}
	
	protected GameObject getGameObject(){
		return enemy.getGameObject();
	}
	
	private void setIsWin(boolean isWin){
		this.isWin = isWin;
	}
	
	boolean getIsWin(){
		return isWin;
	}
	
	private void setRounds(int rounds){
		this.rounds = rounds;
	}
	
	private int getRounds(){
		return rounds;
	}
	
}
