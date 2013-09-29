package me.editor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import me.editor.core.IONames;
import me.editor.core.MainFrame;
import me.editor.world.Layer;

public class LayerListPane extends JPanel
{
	private static final long serialVersionUID = 1759077322549837964L;
	
	private MainFrame mainFrame;
	
	private JList<String> layerList;
	private JPanel buttonPanel;
	private JButton[] buttons;
	
	public LayerListPane(MainFrame frame)
	{	
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 640));
		
		this.setMainFrame(frame);
		this.layerList = new JList<String>();
		add(layerList, BorderLayout.CENTER);
		
		initButtons(frame);
	}
	
	private void initButtons(ActionListener listener)
	{ 
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		
		buttons = new JButton[5];
		
		buttons[0] = new JButton("+");
		buttons[0].setName(IONames.LAYER_ADD);
		buttons[0].setPreferredSize(new Dimension(50, 50));
		buttons[0].addActionListener(listener);
		buttonPanel.add(buttons[0]);
		
		buttons[1] = new JButton("-");
		buttons[1].setName(IONames.LAYER_REMOVE);
		buttons[1].setPreferredSize(new Dimension(50, 50));
		buttons[1].addActionListener(listener);
		buttonPanel.add(buttons[1]);
		
		buttons[2] = new JButton("/\\");
		buttons[2].setName(IONames.LAYER_UP);
		buttons[2].setPreferredSize(new Dimension(50, 50));
		buttonPanel.add(buttons[2]);
		
		buttons[3] = new JButton("\\/");
		buttons[3].setName(IONames.LAYER_DOWN);
		buttons[3].setPreferredSize(new Dimension(50, 50));
		buttons[3].addActionListener(listener);
		buttonPanel.add(buttons[3]);
		
		buttons[4] = new JButton("P");
		buttons[4].setName(IONames.LAYER_PROP);
		buttons[4].setPreferredSize(new Dimension(50, 50));
		buttons[4].addActionListener(listener);
		buttonPanel.add(buttons[4]);
		
		add(buttonPanel, BorderLayout.NORTH);
	}
	
	public void updateGUI()
	{
		updateLayerList();
	}
	
	public void updateLayerList()
	{
		if(getMainFrame().world != null)
		{
			ArrayList<Layer> layers = getMainFrame().world.layers;
			
			String[] s = new String[layers.size()];
			for(int i = 0; i < s.length; i++)
			{
				s[i] = layers.get(i).getName();
			}
			layerList.setListData(s);
		}
	}
	
	public MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
}
