package me.editor.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import me.editor.gui.EntityListPane;
import me.editor.gui.LayerListPane;
import me.editor.gui.MyMenuBar;
import me.editor.gui.WorldPanel;
import me.editor.world.World;
import me.editor.world.WorldEdit;

public class MainFrame extends JFrame implements EventListener, ActionListener
{
	private static final long serialVersionUID = -8390504980693964466L;
	
	private JFileChooser fileChooser;
	
	private World world;
	private WorldEdit worldEdit;
	
	private WorldPanel worldPanel;
	private EntityListPane entityList;
	private LayerListPane layerList;
	
	private MyMenuBar bar;
	
	public MainFrame(String title)
	{
		super(title);
		
		fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		
		makeFrame();
		updateGUI();
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
		worldPanel = new WorldPanel(this);
		entityList = new EntityListPane(this);
		layerList = new LayerListPane(this);
		
		JSplitPane entityWorldLayer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane worldLayer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		worldLayer.add(worldPanel);
		worldLayer.add(layerList);
		worldLayer.setResizeWeight(1.0);
		entityWorldLayer.add(entityList);
		entityWorldLayer.add(worldLayer);
		add(entityWorldLayer, BorderLayout.CENTER);
		
		bar = new MyMenuBar(this);
		add(bar, BorderLayout.NORTH);
	}
	
	public void showFrame()
	{
		setVisible(true);
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public void setWorld(World newWorld)
	{
		this.world = newWorld;
		setWorldEdit(new WorldEdit(this));
		updateGUI();
	}
	
	public WorldEdit getWorldEdit()
	{
		return worldEdit;
	}

	public void setWorldEdit(WorldEdit worldEdit)
	{
		this.worldEdit = worldEdit;
	}
	
	public boolean hasWorld()
	{
		return this.getWorld() != null && this.getWorldEdit() != null;
	}

	public void onEvent(String event)
	{
		switch(event)
		{
		case IONames.FILE_NEW: newFile(); return;
		case IONames.FILE_OPEN: openFile(); return;
		case IONames.FILE_SAVE: saveFile(); return;
		case IONames.FILE_EXIT: System.exit(0); return;
		default: return;
		}
	}
	
	public void newFile()
	{
		setWorld(new World(1024, 640));
	}
	
	public void openFile()
	{
		if(fileChooser.showOpenDialog(this) != 0)
		{
			return;
		}
		
		File f = fileChooser.getSelectedFile();
		
		if(f != null && f.exists() & f.isFile())
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				
				Object object = ois.readObject();
				
				ois.close();
				
				if(object instanceof World)
				{
					setWorld((World) object);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "This file was not a world:" + "\n" + f.getName(), "Could not open", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Failed to read file:" + "\n" + f.getName(), "Could not open", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void saveFile()
	{
		if(world == null)
		{
			JOptionPane.showMessageDialog(this, "No world to save", "Could not save", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(fileChooser.showSaveDialog(this) != 0)
		{
			return;
		}
		
		File f = fileChooser.getSelectedFile();
		
		if(f == null)
		{
			return;
		}
		
		if(f.exists() & f.isFile())
		{
			int out = JOptionPane.showConfirmDialog(this, "Are you sure you want to override:" + "\n" + f.getName(), "Save over?", JOptionPane.YES_NO_OPTION);
			if(out == JOptionPane.NO_OPTION)
			{
				return;
			}
		}
		
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			
			oos.writeObject(world);
			
			oos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to save to file:" + "\n" + f.getName(), "Could not save", JOptionPane.ERROR_MESSAGE);
		}
		
		repaint();
	}
	
	public void updateGUI()
	{
		worldPanel.updateGUI();
		entityList.updateGUI();
		layerList.updateGUI();
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof Component)
		{
			onEvent(((Component) e.getSource()).getName());
		}
	}
}
