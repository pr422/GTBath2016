package model;

import java.util.ArrayList;
import java.util.Random;

public class Coin {
	
	private ArrayList<int[]> coinPositions;
	private int numberOfCoin;
	private ArrayList<int[]> invalidPositions;
	private ArrayList<int[]> usedPositions;
	
	public Coin(ArrayList<int[]> invalidP, ArrayList<int[]> usedP){
		this.coinPositions = new ArrayList();
		this.invalidPositions = new ArrayList(invalidP);
		this.usedPositions = new ArrayList(invalidP);
		randomNumberOfCoin();
	}

	private int randomNumber(int min, int max){
		Random rand = new Random();
		int randNum = rand.nextInt(max) + min;
//		System.out.println("random number produced : " + randNum);
		
		return randNum;
	}
	
	private void randomCoinPosition(){
		//check whether new coin position is duplicate or not
		int x = randomNumber(1,5);  
		int y = randomNumber(1,5);
		int[] coinPosition = {x,y};
		if(coinPositions.size() != 0 && checkDuplicatePosition(coinPosition)){
			//true if duplicate
			randomCoinPosition();
		}else{
			//add to arraylist
			addCoinPosition(coinPosition);
		}
	}
	
	private boolean checkDuplicatePosition(int[] coinPosition){
		boolean isMatch = false;
		for (int[]  p : getCoinPositions()) {
			if(p[0] == coinPosition[0] && p[1] == coinPosition[1]){
				isMatch = true;
			}
		}
		for(int[] p: invalidPositions){
			if(p[0] == coinPosition[0] && p[1] == coinPosition[1]){
				isMatch = true;
			}
		}
		for(int[] p: usedPositions){
			if(p[0] == coinPosition[0] && p[1] == coinPosition[1]){
				isMatch = true;
			}
		}
		return isMatch;
	}
	
	private void addCoinPosition(int[] coinPosition){
		coinPositions.add(coinPosition);
	}
	
	protected void removeCoinPosition(int[] coinPosition){
		printCoinPositions();
		System.out.println("This coin is contain" + coinPositions.contains(coinPosition));
		for ( int i = 0;  i < coinPositions.size(); i++){
            int[] p = coinPositions.get(i);
            if(p[0] == coinPosition[0] && p[1] == coinPosition[1])
            {
                coinPositions.remove(i);
            }
        }
		
		System.out.println("This coin is contain" + coinPositions.contains(coinPosition));
	}
	
	private void setCoinPositions(ArrayList<int[]> coinPositions){
		this.coinPositions = coinPositions;
	}
	
	private void printCoinPositions(){
		for(int[] p: coinPositions){
			System.out.println("[0]" + p[0] + " [1]" + p[1]);
		}
	}
	protected ArrayList<int[]> getCoinPositions(){
		return coinPositions;
	}
	
	private void randomNumberOfCoin(){
		int randNum = randomNumber(0,5);
		setNumberOfCoin(randNum);
		if(randNum != 0){
			for(int i = 0; i < randNum; i++){
				randomCoinPosition();
			}
		}
	}
	
	private void setNumberOfCoin(int numberOfCoin){
		this.numberOfCoin = numberOfCoin;
	}
	
	protected int getNumberOfCoin(){
		return numberOfCoin;
	}
	
	
}
