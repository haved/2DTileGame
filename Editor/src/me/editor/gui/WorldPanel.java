/**@author HKrogstie*/
package me.editor.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import me.editor.core.Camera;
import me.editor.core.MainFrame;

public class WorldPanel extends JPanel implements MouseMotionListener, MouseWheelListener
{
	private static final long serialVersionUID = 8737833902118140473L;
	
	private Point prev;
	private int totalScroll = 10;
	
	private Camera cam;
	private MainFrame frame;
	
	public WorldPanel(MainFrame frame)
	{		
		setPreferredSize(new Dimension(1024, 640));
		
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		this.cam = new Camera();
		this.frame = frame;
	}
	
	public void updateGUI()
	{
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(frame.world != null)
		{
			frame.world.render((Graphics2D)g, getCamera());
		}
		g.drawString("Camera: x=" + cam.scrollX + " y=" + cam.scrollY + " zoom=" + (int)(cam.scale * 100) + "%", 5, 10);
	}

	public Camera getCamera()
	{
		return cam;
	}

	public void setCamera(Camera cam)
	{
		this.cam = cam;
	}

	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		totalScroll = Math.max(totalScroll - e.getWheelRotation(), 1);
		
		cam.scale = totalScroll / 10f;
		
		repaint();
	}
	

	@Override
	public void mouseDragged(MouseEvent e)
	{
		cam.scrollX += ((prev.getX() - e.getX()) * (10f/totalScroll));
		cam.scrollY += ((prev.getY() - e.getY()) * (10f/totalScroll));
		
		prev = e.getPoint();
		
		repaint();
	}
	

	@Override
	public void mouseMoved(MouseEvent e)
	{
		prev = e.getPoint();
	}
}
