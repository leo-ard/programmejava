import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame windows;
	public static GameSave gameSave;
	
	public static void main(String Args[]){
		
		windows = new JFrame();
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windows.setResizable(true);
		windows.setMinimumSize(new Dimension(760,650));
		
		gameSave = new GameSave();
		gameSave.load();
		
		windows.setContentPane(new GamePane());
		
		windows.pack();
		windows.setLocationRelativeTo(null);
		windows.setVisible(true);
		
	}

}
