package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class View extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8215973777232473220L;

	private ScreenPanel screenPanel;
	private SidePanel sidePanel;
	
	public View(){
		super("Game Name");
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1020,700);
		setResizable(false);
		setVisible(true);
		setFocusable(true);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		screenPanel = new ScreenPanel();
		sidePanel = new SidePanel();
		
		add(screenPanel, BorderLayout.LINE_START);
		add(sidePanel, BorderLayout.LINE_END);
		
		requestFocusInWindow();
		pack();
	}
	
	public void addStartButtonListener(ActionListener listener){
		sidePanel.controlPanel.addStartButtonListener(listener);
	}
	
	public void addResetButtonListener(ActionListener listener){
		sidePanel.controlPanel.addResetButtonListener(listener);
	}
	
	public void addBuyBlockButtonListener(ActionListener listener){
		sidePanel.infoPanel.addBuyBlockButtonListener(listener);
	}
	
	public String getPlayerName(){
		return sidePanel.controlPanel.getPlayerName();
	}
	
	public void nameEnabled(boolean b){
		sidePanel.controlPanel.nameEnabled(b);
	}
	
	public void setObjectTextField(String object){
		sidePanel.infoPanel.setObject(object);
	}
	
	public void setCoinTextField(String coin){
		sidePanel.infoPanel.setCoin(coin);
	}
	
	public void setHealthTextField(String health){
		sidePanel.infoPanel.setHealth(health);
	}
	
	public void setHealthPotionTextField(String healthPotion){
		sidePanel.infoPanel.setHealthPotion(healthPotion);
	}
	
	public void setBlockTextField(String block){
		sidePanel.infoPanel.setBlock(block);
	}
	
	public void setBlockPriceTextField(String blockPrice){
		sidePanel.infoPanel.setBlockPrice(blockPrice);
	}
	
	public void buyBlockEnabled(boolean b){
		sidePanel.infoPanel.buyBlockEnabled(b);
	}

	public void setScreen(char[][] screenArray) {
		screenPanel.setScreen(screenArray);
	}
	
	public void setObjectGallery(String[] objectArray){
		sidePanel.objectPanel.setScreen(objectArray);
	}
	
	public void showErrorMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
