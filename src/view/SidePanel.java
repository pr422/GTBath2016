package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SidePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4231304967321746399L;
	
	ControlPanel controlPanel;
	InfoPanel infoPanel;
	ObjectPanel objectPanel;
	
	public SidePanel(){
		setSize(300,700);
		
		controlPanel = new ControlPanel();
		infoPanel = new InfoPanel();
		objectPanel = new ObjectPanel();
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.VERTICAL;
//		add(controlPanel,c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridy = 1;
		add(infoPanel,c );
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridy = 2;
		add(objectPanel,c);
		
		
		
	}
	
	class ControlPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7639722458457542619L;
		JLabel nameLabel;
		JTextField name;
		JButton startButton;
		JButton resetButton;
		
		public ControlPanel(){
			setPreferredSize(new Dimension(600,75));
			setBorder(BorderFactory.createLineBorder(Color.gray));
			
			
			add(new JLabel("Name: "));
			name = new JTextField(20);
			add(name);
			
			startButton = new JButton("Start");
			add(startButton);
			
			resetButton = new JButton("Reset");
			add(resetButton);
		}
		
		protected void addStartButtonListener(ActionListener listener){
			startButton.addActionListener(listener);
		}
		
		protected void addResetButtonListener(ActionListener listener){
			resetButton.addActionListener(listener);
		}
		
		protected String getPlayerName(){
			String playerName = "";
			playerName = name.getText();
			System.out.println(playerName);
			return playerName;
		}
		
		protected void nameEnabled(boolean b){
			name.setEditable(b);
			name.setEnabled(b);
		}
		
	}
	
	class InfoPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6563021875967320569L;
		
		JTextField object;
		JTextField coin;
		JTextField health;
		JTextField damage;
		JTextField healthPotion;
		JButton buyHealthPotionButton;
		JTextField block;
		JTextField blockPrice;
		JButton buyBlockButton;
		
		public InfoPanel(){
			setPreferredSize(new Dimension(300,325));
			setBorder(BorderFactory.createTitledBorder("Player Stats"));

			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(10,0,10,0);
			c.gridx = 0;
			c.gridy = 0;
			add(new JLabel("Objects Collected "),c);
			
			c.gridx = 1;
			c.gridy = 0;
			object = new JTextField(3);
			object.setEnabled(false);
			add(object,c);
			
			c.gridx = 0;
			c.gridy = 1;
			add(new JLabel("Coins"),c);
			
			c.gridx = 1;
			c.gridy = 1;
			coin = new JTextField(3);
			coin.setEnabled(false);
			add(coin, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(new JLabel("Health"),c);
			
			c.gridx = 1;
			c.gridy = 2;
			health = new JTextField(3);
			health.setEnabled(false);
			add(health,c);
			
//			c.gridx = 0;
//			c.gridy = 3;
//			add(new JLabel("Max Damage"), c);
//			
//			c.gridx = 1;
//			c.gridy = 3;
//			damage = new JTextField(3);
//			damage.setEnabled(false);
//			add(damage,c);
//			
			c.gridx = 0;
			c.gridy = 4;
			add(new JLabel("Health Potions"),c);
			
			c.gridx = 1;
			c.gridy = 4;
			healthPotion = new JTextField(3);
			healthPotion.setEnabled(false);
			add(healthPotion, c);
			
			c.gridx = 0;
			c.gridy = 5;
			add(new JLabel("Blocks"),c);
			
			c.gridx = 1;
			c.gridy = 5;
			block = new JTextField(3);
			block.setEnabled(false);
			add(block,c);
			
			c.gridx = 0;
			c.gridy = 6;
			blockPrice = new JTextField(3);
			blockPrice.setEnabled(false);
			add(new JLabel("Block Cost: "),c);
			c.gridx = 1;
			c.gridy = 6;
			add(blockPrice,c);
			
			c.gridx = 3;
			c.gridy = 6;
			buyBlockButton = new JButton("Buy Block");
			buyBlockButton.setFocusable(false);
			add(buyBlockButton, c);
			
		}
		
		protected void setObject(String object){
			this.object.setText(object);
		}
		
		protected void setCoin(String coin){
			this.coin.setText(coin);
		}
		
		protected void setHealth(String health){
			this.health.setText(health);
		}
		
		protected void setDamage(String health){
			this.damage.setText(health);
		}
		
		protected void setHealthPotion(String healthPotion){
			this.healthPotion.setText(healthPotion);
		}
		
		protected void setBlock(String block){
			this.block.setText(block);
		}
		
		protected void buyBlockEnabled(boolean b){
			buyBlockButton.setEnabled(b);
		}
		
		protected void setBlockPrice(String blockPrice){
			this.blockPrice.setText(blockPrice);
		}
		
		protected void addBuyBlockButtonListener(ActionListener listener){
			buyBlockButton.addActionListener(listener);
		}
		
	}
	
	class ObjectPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7814859048461858795L;
		JLabel[] object = new JLabel[6];
		
		public ObjectPanel(){
			setPreferredSize(new Dimension(320,230));
			setLayout(new GridLayout(2,3));
			setBorder(BorderFactory.createTitledBorder("Collected Objects"));
			for (int i = 0; i < 6; i++){
				object[i] = new JLabel();
				object[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				add(object[i]);
			}
		}
		
		protected void setScreen(String[] objectArray){
			for (int i = 0; i < objectArray.length; i++){
				System.out.println("OBJECT: " + objectArray[i]);
				switch(objectArray[i]){
				case "Tooth":
					object[i].setIcon(new ImageIcon(getClass().getResource("asset/objects/tooth.png")));
					break;
				case "Urn":
					object[i].setIcon(new ImageIcon(getClass().getResource("asset/objects/urn.png")));
					break;
				case "Drum":
					object[i].setIcon(new ImageIcon(getClass().getResource("asset/objects/drum.png")));
					break;
				case "Brooch":
					object[i].setIcon(new ImageIcon(getClass().getResource("asset/objects/brooch.png")));
					break;
				case "Leopard":
					object[i].setIcon(new ImageIcon(getClass().getResource("asset/objects/leopard.png")));
					break;
				case "Skull":						
					object[i].setIcon(new ImageIcon(getClass().getResource("asset/objects/skull.png")));
					break;
				}
			}
		}
		
	}
	
	
}
