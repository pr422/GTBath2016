package model;

import java.util.ArrayList;
import java.util.Random;
//not for final boss
public class GameObject {
	private String objectName;
	private String[] OBJECTNAME = {"Tooth", "Urn", "Drum", "Brooch", "Leopard", "Skull"};
	private final int MAXOBJECT = 6;
	private int[] position;
	
	public GameObject(){
		setName("");
		position = null;
	}
	
	protected void setName(String objectName){
		this.objectName = objectName;
	}
	
	protected String getName(){
		return objectName;
	}
	
	private int randomNumber(int min, int max){
		Random rand = new Random();
		int randNum = rand.nextInt(max) + min;
//		System.out.println("random number produced : " + randNum);
		
		return randNum;
	}
	
	protected String randomObjectName(){
		int num = randomNumber(0,OBJECTNAME.length);
		String tempName = OBJECTNAME[num];
		
		for (int i = 0; i < OBJECTNAME.length; i++)
		{
		    if (OBJECTNAME[i].equals(tempName))
		    {
		    	OBJECTNAME[i] = null;
		        break;
		    }
		}
		
		return tempName;
	}
	
	private int getMaxNumberObject(){
		return MAXOBJECT;
	}
	
	protected void randomPosition(ArrayList<int[]> invalidPositions, ArrayList<int[]> usedPositions){
		int x = randomNumber(1,5);
		int y = randomNumber(1,5);
		
		int[] objectPosition = {x,y};
		if(checkDuplicatePosition(objectPosition, invalidPositions, usedPositions)){
			//true if duplicate
			randomPosition(invalidPositions, usedPositions);
		}else{
			setPosition(objectPosition);
		}
	}
	
	private boolean checkDuplicatePosition(int[] objectPosition, ArrayList<int[]> invalidPositions, ArrayList<int[]> usedPositions){
		boolean isMatch = false;
		for(int[] p: invalidPositions){
			if(p[0] == objectPosition[0] && p[1] == objectPosition[1]){
				isMatch = true;
			}
		}
		if(usedPositions.size() != 0){
			for(int[] p: usedPositions){
				if(p!= null && p[0] == objectPosition[0] && p[1] == objectPosition[1]){
					isMatch = true;
				}
			}
		}
		return isMatch;
	}
	
	protected void setPosition(int[] position){
		System.out.println("object position is set");
		this.position = position;
	}
	
	protected int[] getPosition(){
		System.out.println("getPosition gameObject" + position);
		return position;
	}
	
}
