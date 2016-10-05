package model;
import java.util.Random;

public class Player {
	private String name;
	private int numberOfCoin;
	private int numberOfObject;
	private int[] position; //position in each room
	private int hp; //health point
	private final int MAXHP = 100;
	private int ad; //attack damage
	private int hpPotion;
	private int block;
	
	public Player(){
		setName("");
		setNumberOfCoin(0);
		setNumberOfObject(0);
		position = null; //for player in the first room ((Map[3][3]))
		setHp(100);
		randomAd();
		setHpPotion(0);
		setBlock(0);
	}

	public Player(Player player){
		setName(player.getName());
		setNumberOfCoin(player.getNumberOfCoin());
		setNumberOfObject(player.getNumberOfObject());
		setPosition(player.getPosition());
		setHp(player.getHp());
		setAd(player.getAd());
		setHpPotion(player.getHpPotion());
		setBlock(player.getBlock());
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	protected String getName(){
		return name;
	}
	
	protected void setNumberOfCoin(int numberOfCoin){
		this.numberOfCoin = numberOfCoin;
	}
	
	protected int getNumberOfCoin(){
		return numberOfCoin;
	}
	
	protected void setNumberOfObject(int numberOfObject){
		this.numberOfObject = numberOfObject;
	}
	
	protected int getNumberOfObject(){
		return numberOfObject;
	}
	
	protected void setPosition(int[] position){
		this.position = position;
	}
	
	protected int[] getPosition(){
		return position;
	}
	
	protected void setMaxHp(){
		setHp(MAXHP);
	}
	
	protected void setHp(int hp){
		this.hp = hp;
	}
	
	protected int getHp(){
		return hp;
	}
	
	protected void randomAd(){
		int randNum = randomNumber(10, 25);
		setAd(randNum);
	}
	
	private void setAd(int ad){
		this.ad = ad;
	}
	
	protected int getAd(){
		return ad;
	}
	
	private int randomNumber(int min, int max){
		Random rand = new Random();
		int randNum = rand.nextInt(max) + min;
//		System.out.println("random number produced : " + randNum);
		
		return randNum;
	}
	
	protected void setHpPotion(int hpPotion){
		this.hpPotion = hpPotion;
	}
	
	protected int getHpPotion(){
		return hpPotion;
	}
	
	protected void setBlock(int block){
		this.block = block;
	}
	
	protected int getBlock(){
		return block;
	}
	
}


