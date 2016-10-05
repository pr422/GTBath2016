package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScreenPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9010815346096032089L;
	private final int LENGTH = 7;
	private JLabel[][] screen = new JLabel[LENGTH][LENGTH];
	
	public ScreenPanel(){
		setPreferredSize(new Dimension(LENGTH*100,LENGTH*100));
		setLayout(new GridLayout(LENGTH, LENGTH));

		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++){
				screen[i][j] = new JLabel();
//				screen[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				add(screen[i][j]);
			}
			
		}
	}
	
	protected void setScreen(char[][] screenArray){
		for (int i = 0; i < LENGTH; i++){
			for(int j = 0; j < LENGTH; j++){
				switch(screenArray[i][j]){
				case '.':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/blank.png")));
					break;
				case 'O':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/Blue-Monster.png")));
					break;
				case 'T':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/blank.png")));
					break;
				case 'X':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/horizontalWall.png")));
					break;
				case 'D':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/blank.png")));
					break;
				case 'E':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/exit.png")));
					break;
				case 'C':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/coin.png")));
					break;
				case 'P':
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/player.png")));
					break;
				}
				
				if (j == 0 || j == 6){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/verticalWall.png")));
				}
				
				if (i == 0 && j==0){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/topLeftCornerWall.png")));
				}
				
				if(i == 0 && j == 6){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/topRightCornerWall.png")));
				}
				
				if(i == 6 && j == 0){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/bottomLeftCornerWall.png")));
				}
				
				if(i == 6 && j == 6){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/bottomRightCornerWall.png")));
				}
				
				if(screenArray[i][j] == 'D'){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/blank.png")));
				}
				
				if(screenArray[i][j] == 'E'){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/exit.png")));
				}
				
				if(screenArray[i][j] == 'O'){
					screen[i][j].setIcon(new ImageIcon(getClass().getResource("asset/components/Blue-Monster.png")));
				}
				
				setBackground(Color.WHITE);
			}
		}
	}
	
	public void addKeyListener(KeyListener listener){
		addKeyListener(listener);
	}
}
