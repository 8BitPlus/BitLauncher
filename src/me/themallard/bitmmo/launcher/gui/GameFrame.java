package me.themallard.bitmmo.launcher.gui;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel appletPanel;

	public GameFrame() {
		super("Bit+ Launcher");

		this.appletPanel = new JPanel();
		this.appletPanel.setPreferredSize(new Dimension(1280, 720));

		// prevents the canvas being drawn ontop of the other components
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());

		this.add(this.appletPanel);
		
		this.setResizable(false);

		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void addApplet(Applet a) {
		this.appletPanel.add(a);
		this.pack();
	}
}