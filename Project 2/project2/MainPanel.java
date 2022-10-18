//Chris Acebedo

package project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class MainPanel extends JPanel
{
	private int count;
	private JButton push;
	private JLabel lblPushes;
	private ImageIcon image;
	private JLabel recommend;
	private Collection allData = new Collection("./project2/netflixAllWeeksGlobalProcessed.txt");
	private JTextField textField;


	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed (ActionEvent event)
		{
			//constructor
		}
	}

	public MainPanel()
	{
		setLayout(null);
		setBackground(Color.RED);
		setPreferredSize(new Dimension(775, 500));

		header();
		recommendButton();
		displayTextInfo();
		picturePanel();
	}

	public void recommendButton() 
	{

		count = 0;
		push = new JButton ("Press for Recommendation");
		push.setBounds(26, 448, 206, 23);
		push.addActionListener(new ButtonListener());
		push.setActionCommand("rec");

		lblPushes = new JLabel ("Recommended Options: 0");
		lblPushes.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblPushes.setBounds(242, 449, 229, 23);

		changeRecommend();

		add (push);
		add(lblPushes);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		layeredPane.setForeground(Color.WHITE);
		layeredPane.setBackground(Color.WHITE);
		layeredPane.setBounds(10, 431, 755, 58);
		add(layeredPane);

		push.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				count++;
				lblPushes.setText ("Recommended Options: "+count);
			}

		});
	}

	public void displayImage()
	{
		image = new ImageIcon(this.getClass().getResource("/project2/netflix.gif"));
		JLabel label = new JLabel(image);
		label.setBounds(591, 0, 239, 92);
		add(label);


	}

	public void setTimerforRecommend()
	{
		//Thank god for Stack Overflow
		Timer t = new Timer(100, new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				remove(recommend);
				changeRecommend();

			}

		});
		t.setRepeats(false);
		t.start();
	}

	public void changeRecommend()
	{
		recommend = new JLabel("Title: " + allData.random_suggestion());
		recommend.setVisible(false);
		recommend.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		recommend.setBounds(456, 448, 309, 25);
		add(recommend);

		push.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getActionCommand().equals("rec"))
				{
					recommend.setVisible(true);
					setTimerforRecommend();

				}


			}

		});
	}

	public void header()
	{	
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 627, 92);
		add(panel);
		panel.setLayout(null);

		JLabel title = new JLabel("Welcome to Netflix");
		panel.add(title);
		title.setForeground(Color.WHITE);
		title.setBounds(10, 40, 480, 52);
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 45));

		displayImage();
	}

	public void displayTextInfo()
	{
//		JScrollPane txt = new JScrollPane();
//		txt.setBounds(10, 335, 755, 85);
//		add(txt);
//
//		JTextArea textArea = new JTextArea();
//		txt.setViewportView(textArea);
//		textArea.setText(allData.toString());

		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setBounds(116, 150, 89, 23);
		add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Enter Week:");
		lblNewLabel.setBounds(10, 137, 73, 14);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(10, 151, 96, 20);
		add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBounds(10, 103, 89, 23);
		add(btnNewButton_1);

		JComboBox movie = new JComboBox();
		movie.setMaximumRowCount(6);
		ArrayList <ShowInWeek> moviesInWeek = allData.getOneWeek("2021-07-04");
		
			String [] data = new String[moviesInWeek.size()];
			int index = 0;
			for (ShowInWeek sw : moviesInWeek) 
			{
				data[index] = sw.getShow_titles();
				index++;
			}

		movie.setModel(new DefaultComboBoxModel(data));	
		movie.setBounds(10, 175, 195, 22);
		add(movie);
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList <ShowInWeek> moviesInWeek = allData.getOneWeek(textField.getText());
				if (moviesInWeek != null && moviesInWeek.size() > 0) 
				{
					String [] data = new String[moviesInWeek.size()];
					int index = 0;
					for (ShowInWeek sw : moviesInWeek) 
					{
						data[index] = sw.getShow_titles();
						index++;
					}
					movie.setModel(new DefaultComboBoxModel(data));
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}

		});
		
		JButton btnNewButton_2 = new JButton("Edit");
		btnNewButton_2.setBounds(116, 261, 89, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Remove");
		btnNewButton_3.setBounds(59, 227, 89, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Add");
		btnNewButton_4.setBounds(10, 261, 89, 23);
		add(btnNewButton_4);
		
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String title = JOptionPane.showInputDialog("Enter Title");
				allData.purgeShow(title);
				doClose();
			}
			
			

		});
		
		
		
		btnNewButton_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String week = JOptionPane.showInputDialog("Enter Week");
				String catagory = JOptionPane.showInputDialog("Enter Catagory");
				String rank = JOptionPane.showInputDialog("Enter Rank");
				String title = JOptionPane.showInputDialog("Enter Title");
				String season = JOptionPane.showInputDialog("Enter Season");
				String hours = JOptionPane.showInputDialog("Enter Hours");
				String weeksTop10 = JOptionPane.showInputDialog("Enter Weeks in Top 10");
				
				ShowInWeek data_1 = new ShowInWeek(week, catagory, rank, title, season, hours, weeksTop10);
				allData.addData(data_1);
				doClose();
			}

		});
		
		btnNewButton_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String week = JOptionPane.showInputDialog("Enter Week");
				String catagory = JOptionPane.showInputDialog("Enter Catagory");
				String rank = JOptionPane.showInputDialog("Enter Rank");
				String title = JOptionPane.showInputDialog("Enter Title");
				String season = JOptionPane.showInputDialog("Enter Season");
				String hours = JOptionPane.showInputDialog("Enter Hours");
				String weeksTop10 = JOptionPane.showInputDialog("Enter Weeks in Top 10");
				
				ShowInWeek data_1 = new ShowInWeek(week, catagory, rank, title, season, hours, weeksTop10);
				allData.edit(data_1);
				doClose();
			}

		});
	}
	
	public void picturePanel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(224, 103, 541, 222);
		add(panel);
		
		ImageIcon st = new ImageIcon(this.getClass().getResource("/project2/stranger things file.jfif"));
		panel.setLayout(null);
		JLabel label = new JLabel(st);
		label.setBounds(10, 11, 233, 200);
		panel.add(label);
		
		ImageIcon gm = new ImageIcon(this.getClass().getResource("/project2/gray man file.jfif"));
		panel.setLayout(null);
		JLabel label2 = new JLabel(gm);
		label2.setBounds(253, 11, 278, 200);
		panel.add(label2);
		
	}

	public void doClose() 
	{
		// TODO Auto-generated method stub
		allData.doWrite("./project2/textWrite.txt");
	}
}
