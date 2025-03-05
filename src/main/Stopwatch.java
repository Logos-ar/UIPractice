package main;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class Stopwatch implements ActionListener {
	
	JFrame frame = new JFrame("Watch");
	JButton resetButton = new JButton("Reset");
	JButton startButton = new JButton("Start");
	JLabel timeLabel = new JLabel();
	
	int elapsedTime; 
	int seconds; 
	int minutes;
	int hours;
	boolean hasStarted = false;
	
	
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	Timer timer = new Timer(1000, new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			elapsedTime = elapsedTime + 1000;
			hours = elapsedTime/ 3600000;
			minutes = (elapsedTime/60000) % 60;
			seconds = (elapsedTime/1000) % 60;
			
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		}
		
	});
	
	Stopwatch(){
		
		//Adding hours, minutes, and seconds separated by :
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		timeLabel.setBounds(100, 100, 250, 100); //x pos, y pos, width, height
		timeLabel.setFont(new Font("Verdana", Font.PLAIN ,35 ));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		//Start button parameters
		startButton.setBounds(100, 200, 125, 50);
		startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		//ResetButton parameters
		resetButton.setBounds(225, 200, 125, 50);
		resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		//Adding components to frame
		frame.add(timeLabel);
		frame.add(startButton);
		frame.add(resetButton);
		
		//Sets up frame parameters
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(445, 420);
		frame.setLayout(null);
		//frame.pack(); //Sizes the frame so that all its contents are at or above their preferred size
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == startButton) {
			
			if (hasStarted == false) {
				hasStarted = true;
				startButton.setText("Pause");
				start();
			} else {
				hasStarted = false;
				startButton.setText("Continue");
				stop();
			}
		}
		if (e.getSource() == resetButton) {
			reset();
			hasStarted = false;
		}
		
	}
	
	void start() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}
	void reset() {
		timer.stop();
		startButton.setText("Start");
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
	}
}
