package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Enemy;
import model.Player;

public class Battle extends JFrame {

	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = -3280592947852239873L;
	
	EnemyPanel enemyPanel;
	PlayerPanel playerPanel;
	ControlPanel controlPanel;
	
	public static void main(String[] args){
		new Battle();
	}
	
	public Battle(){
		super("Battle");
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		setResizable(false);
		setVisible(true);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		enemyPanel = new EnemyPanel();
		playerPanel = new PlayerPanel();
		controlPanel = new ControlPanel();
		
		add(enemyPanel, BorderLayout.LINE_START);
		add(playerPanel, BorderLayout.LINE_END);
		add(controlPanel, BorderLayout.PAGE_END);
		
		pack();
	}
	
	class EnemyPanel extends JPanel{
		
		JTextField name = new JTextField();
		JTextField health = new JTextField();
		JTextField object = new JTextField();
		
		public EnemyPanel(){
			setPreferredSize(new Dimension(200,200));
			setBorder(BorderFactory.createLineBorder(Color.gray));

			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(10,0,0,0);
			
			c.gridx = 1;
			c.gridy = 0;
			JLabel enemyLabel = new JLabel("Monster");
			enemyLabel.setFont(new Font("Serif", Font.BOLD, 14));
			add(enemyLabel,c);
			
			c.gridx = 0;
			c.gridy = 1;
			add(new JLabel("Name"),c);
			
			c.gridx = 2;
			c.gridy = 1;
			
			name = new JTextField(5);
			name.setEnabled(false);
			add(name, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(new JLabel("Health"),c);
			
			c.gridx = 2;
			c.gridy = 2;
			health = new JTextField(5);
			health.setEnabled(false);
			add(health,c);
			
			c.gridx = 0;
			c.gridy = 4;
			add(new JLabel("Object"),c);
			
			c.gridx = 2;
			c.gridy = 4;
			object = new JTextField(5);
			object.setEnabled(false);
			add(object,c);
		}
		
		public void setEnemyName(String name){
			this.name.setText(name);
		}
		
		protected void setEnemyHealth(String health){
			this.health.setText(health);
		}
		
		protected void setEnemyObject(String object){
			this.object.setText(object);
		}
	}
	
	class PlayerPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -5024648086467045186L;
		JTextField name;
		JTextField health;
		JTextField healthPot;
		JTextField block;
		
		public PlayerPanel(){
			setPreferredSize(new Dimension(200,200));
			setBorder(BorderFactory.createLineBorder(Color.gray));

			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(10,0,0,0);
			
			c.gridx = 1;
			c.gridy = 0;
			JLabel enemyLabel = new JLabel("Explorer");
			enemyLabel.setFont(new Font("Serif", Font.BOLD, 14));
			add(enemyLabel,c);
			
			c.gridx = 0;
			c.gridy = 1;
//			add(new JLabel("Name"),c);
			
			c.gridx = 2;
			c.gridy = 1;
			
			name = new JTextField(4);
			name.setEnabled(false);
//			add(name, c);
			
			c.gridx = 0;
			c.gridy = 2;
			add(new JLabel("Health"),c);
			
			c.gridx = 2;
			c.gridy = 2;
			health = new JTextField(5);
			health.setEnabled(false);
			add(health,c);
			
			c.gridx = 0;
			c.gridy = 3;
			add(new JLabel("Health Pot"),c);
			
			c.gridx = 2;
			c.gridy = 3;
			healthPot = new JTextField(5);
			healthPot.setEnabled(false);
			add(healthPot,c);
			
			c.gridx = 0;
			c.gridy = 4;
			add(new JLabel("Block Left"),c);
			
			c.gridx = 2;
			c.gridy = 4;
			block = new JTextField(5);
			block.setEnabled(false);
			add(block,c);
			
		}
		
		protected void setPlayerName(String name){
			this.name.setText(name);
		}
		
		protected void setPLayerHealth(String health){
			this.health.setText(health);
		}
		
		protected void setPlayerhealthPot(String healthPot){
			this.healthPot.setText(healthPot);
		}
		
		public void setPlayerBlock(String playerBlock) {
			this.block.setText(playerBlock);
		}
	}
	
	private class ControlPanel extends JPanel{
		JButton fightButton;
		JButton useHPButton;
		JButton buyHPButton;
		JTextField coin;
		
		public ControlPanel(){
			setBorder(BorderFactory.createLineBorder(Color.gray));

			fightButton = new JButton("Fight");
			useHPButton = new JButton("Use Health Pot");
			buyHPButton = new JButton("Buy Health Pot");
			coin = new JTextField(5);
			coin.setEnabled(false);
					
			add(fightButton);
			add(useHPButton);
			add(buyHPButton);
			add(new JLabel("Coin: "));
			add(coin);
		}
		
		protected void addFightButtonListener(ActionListener listener){
			fightButton.addActionListener(listener);
		}
		protected void addUseHPButtonListener(ActionListener listener){
			useHPButton.addActionListener(listener);		
		}
		protected void addBuyHPButtonListener(ActionListener listener){
			buyHPButton.addActionListener(listener);
		}
		protected void setPlayerCoin(String coin){
			this.coin.setText(coin);
		}
	}

	public void setEnemyName(String enemyName) {
		enemyPanel.setEnemyName(enemyName);
	}

	public void setEnemyHealth(String enemyHealth) {
		enemyPanel.setEnemyHealth(enemyHealth);
		
	}

	public void setEnemyObject(String enemyObject) {
		enemyPanel.setEnemyObject(enemyObject);
		
	}

	public void setPlayerName(String playerName) {
		playerPanel.setPlayerName(playerName);
	}

	public void setPlayerHealth(String playerHealth) {
		playerPanel.setPLayerHealth(playerHealth);
	}
	
	public void setPlayerhealthPot(String playerhealthPot){
		playerPanel.setPlayerhealthPot(playerhealthPot);
	}

	public void setPlayerBlock(String playerBlock) {
		playerPanel.setPlayerBlock(playerBlock);
	}

	public void setPlayerCoin(String playerCoin) {
		controlPanel.coin.setText(playerCoin);
	}

	public void addFightButtonListener(ActionListener listener) {
		controlPanel.fightButton.addActionListener(listener);
	}

	public void addUseHealthPotButtonListener(ActionListener listener) {
		controlPanel.useHPButton.addActionListener(listener);

	}
	
	public void addUseHPButtonListener(ActionListener listener) {
		controlPanel.buyHPButton.addActionListener(listener);		
	}

	public void addBuyHPButtonListener(ActionListener listener) {
		controlPanel.buyHPButton.addActionListener(listener);		
	}
	
	public void showErrorMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
}
	
