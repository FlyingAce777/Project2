//Chris Acebedo

package project2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainFrame 
{
	public static void main(String[] args) 
	{
		//Frame
		JFrame frame = new JFrame("NetFlix");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainPanel panel = new MainPanel();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent we)
			{
				panel.doClose();
			}
		});

	}
}
