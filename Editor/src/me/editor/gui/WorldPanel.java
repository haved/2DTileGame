package me.editor.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import me.editor.core.MainFrame;

public class WorldPanel extends JPanel
{
	private static final long serialVersionUID = 8737833902118140473L;
	
	private MainFrame frame;
	
	public WorldPanel(MainFrame frame)
	{
		setPreferredSize(new Dimension(1024, 640));
		this.frame = frame;
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(frame.world != null)
		{
			frame.world.render((Graphics2D)g);
		}
	}
}
