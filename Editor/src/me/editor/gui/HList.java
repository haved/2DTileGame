package me.editor.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class HList extends JComponent implements MouseListener, MouseMotionListener
{	
	private static final long serialVersionUID = 8961161555586775711L;
	private static final int TEXT_HEIGHT = 16;
	private static final Color selectedColor = new Color(92, 172, 238);
	
	private int currentSize = 0;
	private int currentIndex = 0;
	
	private ListController controller;
	
	public HList(ListController controller)
	{
		this.controller = controller;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.GRAY);
	}
	
	public void setListController(ListController controller)
	{
		this.controller = controller;
	}
	
	public ListController getListController()
	{
		return controller;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int index = controller.getSelectedElement();
		Object[] elements = controller.getElements();
		
		if(elements == null)
		{
			return;
		}
		
		currentIndex = index;
		currentSize = elements.length;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), TEXT_HEIGHT * elements.length);
		
		if(index >= 0 & index < elements.length)
		{
			g.setColor(selectedColor);
			g.fillRect(0, TEXT_HEIGHT * index, getWidth(), TEXT_HEIGHT);
		}
		
		g.setColor(Color.BLACK);
		for(int i = 0; i < elements.length; i++)
		{
			g.drawString(elements[i].toString(), 3, TEXT_HEIGHT * i + 12);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		int newIndex = e.getY() / TEXT_HEIGHT;
		
		if(newIndex >= 0 & newIndex < currentSize & newIndex != currentIndex)
		{
			controller.onSelectionChange(newIndex);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}

	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouseClicked(e);
	}
	

	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}
}
