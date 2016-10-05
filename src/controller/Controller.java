package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import model.Model;
import view.Battle;
import view.View;

public class Controller {
	private View view;
	private Model model;
	
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;
		view.addKeyListener(new keyListener());
		view.addStartButtonListener(new StartButtonListener());
		view.addResetButtonListener(new ResetButtonListener());
		view.addBuyBlockButtonListener(new BuyBlockButtonListener());
		updateScreen();

	}
	
	private class keyListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			char keyPressed = arg0.getKeyChar();
			System.out.println(keyPressed);
			String moveResult = model.move(keyPressed);
			updateScreen();
			updateSidePanel();
			if (moveResult.equals("OBJECT")){
				battle();
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
				
		}
		
	}
	
	private class StartButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			view.nameEnabled(false);
			updateScreen();
		}
		
	}
	
	private class ResetButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.nameEnabled(true);
			view.setName("");

		}
		
	}
	
	private class BuyBlockButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String result = model.parseCommand(4);
			updateScreen();
			updateSidePanel();
			
			if (!result.equals("")){
				view.showMessageDialog(result,null);
			}
		}
		
	}
	
	private void updateScreen(){
		view.setScreen(model.getRoomContent());
	}
	
	private void updateSidePanel(){
		view.setObjectTextField(String.valueOf(model.getNumberCollectedObject()));
		view.setCoinTextField(String.valueOf(model.getCollectedCoin()));
		view.setHealthTextField(String.valueOf(model.getPlayerHealth()));
		view.setHealthPotionTextField(String.valueOf(model.getPotion()));
		view.setBlockTextField(String.valueOf(model.getBlock()));
		view.setBlockPriceTextField(String.valueOf("3"));
	}
	
	private void updateObjectGallery(){
		ArrayList<String> object = new ArrayList<String>(model.getCollectedObject());
		String [] objectArray = object.toArray(new String[object.size()]);
		view.setObjectGallery(objectArray);
		
	}
	
	private void battle(){
		final Battle battle = new Battle();
		
		System.out.println("model.getEnemyName(): " + model.getEnemyName());
		battle.setEnemyName(model.getEnemyName());
		battle.setEnemyHealth(String.valueOf(model.enemyHealth()));
		battle.setEnemyObject(model.getGameObjectName());
		
		battle.setPlayerName(model.getPlayerName());
		battle.setPlayerHealth(String.valueOf(model.getPlayerHealth()));
		battle.setPlayerhealthPot(String.valueOf(model.getPotion()));
		battle.setPlayerBlock(String.valueOf(model.getBlock()));
		battle.setPlayerCoin(String.valueOf(model.getCollectedCoin()));
		
		battle.addFightButtonListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
		
				String result = model.parseCommand(1);
//				battle.setEnemyName(model.getEnemyName());
				battle.setEnemyHealth(String.valueOf(model.enemyHealth()));
				battle.setEnemyObject(model.getGameObjectName());
				
				battle.setPlayerName(model.getPlayerName());
				battle.setPlayerHealth(String.valueOf(model.getPlayerHealth()));
				battle.setPlayerhealthPot(String.valueOf(model.getPotion()));
				battle.setPlayerBlock(String.valueOf(model.getBlock()));
				battle.setPlayerCoin(String.valueOf(model.getCollectedCoin()));
				if (result.equals("WIN")){
					updateObjectGallery();
					battle.dispose();
				}
				
				
			}
			
		});
		
		battle.addUseHealthPotButtonListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = model.parseCommand(2);
				System.out.println(result);
				battle.setEnemyName(model.getEnemyName());
				battle.setEnemyHealth(String.valueOf(model.enemyHealth()));
				battle.setEnemyObject(model.getGameObjectName());
				
				battle.setPlayerName(model.getPlayerName());
				battle.setPlayerHealth(String.valueOf(model.getPlayerHealth()));
				battle.setPlayerhealthPot(String.valueOf(model.getPotion()));
				battle.setPlayerBlock(String.valueOf(model.getBlock()));
				battle.setPlayerCoin(String.valueOf(model.getCollectedCoin()));
				
				if (!result.equals("")){
					battle.showMessageDialog(result,null);
				}
				
				
			}
			
		});
		
		battle.addBuyHPButtonListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = model.parseCommand(3);
				System.out.println(result);
				battle.setEnemyName(model.getEnemyName());
				battle.setEnemyHealth(String.valueOf(model.enemyHealth()));
				battle.setEnemyObject(model.getGameObjectName());
				
				battle.setPlayerName(model.getPlayerName());
				battle.setPlayerHealth(String.valueOf(model.getPlayerHealth()));
				battle.setPlayerhealthPot(String.valueOf(model.getPotion()));
				battle.setPlayerBlock(String.valueOf(model.getBlock()));
				battle.setPlayerCoin(String.valueOf(model.getCollectedCoin()));
				if (!result.equals("")){
					battle.showMessageDialog(result,null);
				}
				
				
			}
			
		});
	}	
	
}
