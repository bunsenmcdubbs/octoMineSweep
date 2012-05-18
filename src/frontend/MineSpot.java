package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import backend.*;
import backend.event.*;

public class MineSpot extends JButton implements ActionListener, OpenedSpotEventListener {
	
	public static final int SIZE = 50;
	
	private Spot spot;
	private boolean synced;
	
	public MineSpot(Spot s){
		setSpot(s);
		addActionListener(this);
		synced = false;
		spot.addEventListener(this);
	}
	
	public void setSpot(Spot s){
		spot = s;
	}
	
	@Override
	public void paint(Graphics g){
		if(spot.isOpen()){
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(Color.BLACK);
			g2.fillRect(10,10,10,10);
		}
		else
			super.paint(g);
	}

	/**
	 * Action Listener for the MineSpot button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		spot.discreetOpen();
		synced = true;
	}

	/**
	 * Event Listener for the Spot's opening event
	 */
	@Override
	public void handleEvent(OpenedSpotEvent e) {
		repaint();
	}

	
}
