package me.editor.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import me.editor.gui.LayerListPane;

public class MainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -8390504980693964466L;
	
	private LayerListPane layerList;
	
	public MainFrame(String title)
	{
		super(title);
		makeFrame();
	}
	
	private void makeFrame()
	{
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		addComponents();
		
		pack();
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
	}
	
	private void addComponents()
	{
		layerList = new LayerListPane(this);
		add(layerList, BorderLayout.EAST);
	}
	
	public void showFrame()
	{
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof Component)
		{
			String event = ((Component) e.getSource()).getName();
			
			System.out.println(event);
		}
	}
}
