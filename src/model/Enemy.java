package model;

import java.util.Random;

public class Enemy {
	private String name;
	private String rank;
	private final String[] ENEMYNAME = new String[] {"Billy", "Darwin", "Ethan", "Natasha", "Jackie", "Leon", "Javis"};
	private int hp; //health point
	private int ad; //attack damage
	private static int numberOfEnemyUsed = 0;
	//Javis is big boss
	private GameObject gameObject;
	private final int MAXENEMY = 7;
	
	public Enemy(){
		setName("");
		this.rank = "";
		setHp(0);
		randomAd();
		this.gameObject = new GameObject();
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	protected String getName(){
		return name;
	}
	
	private void setRank(int i){
		if(i != 6){
			this.rank = "Boss";
		}else{
			this.rank = "Final Boss";
		}
	}
	
	private String getRank(){
		return rank;
	}
	
	private int randomNumber(int min, int max){
		Random rand = new Random();
		int randNum = rand.nextInt(max) + min;
//		System.out.println("random number produced : " + randNum);
		
		return randNum;
	}
	
	protected void randomHp(){
		if(!getRank().equals("Final Boss")){
			int randNum = randomNumber(80,120);
			setHp(randNum);
		}else{
			setHp(200);
		}
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
	
	private String getEnemyName(int i){
		return ENEMYNAME[i];
	}
	
	protected String randomEnemyName(){
		int i = randomNumber(0,5);
		String tempName = ENEMYNAME[i];
		setRank(i);
		randomHp();
		return tempName;
	}

	protected void setFinalBoss(){
		int i = MAXENEMY - 1;
		setName(ENEMYNAME[i]);
		setRank(i);
		randomHp();
	}
	
	protected int getNumberOfEnemyUsed(){
		return numberOfEnemyUsed;
	}
	
	protected GameObject getGameObject(){
		return gameObject;
	}
	
}
