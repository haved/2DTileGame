/**@author HKrogstie*/
package me.editor.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import me.editor.core.Camera;
import me.editor.core.MainFrame;

public class WorldPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener
{
	private static final long serialVersionUID = 8737833902118140473L;
	
	private int prevX;
	private int prevY;
	private int totalScroll = 10;
	
	private Camera cam;
	private MainFrame frame;
	
	public WorldPanel(MainFrame frame)
	{		
		setPreferredSize(new Dimension(1024, 640));
		
		addMouseListener(this);
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
		
		if(frame.getWorld() != null)
		{
			frame.getWorld().render((Graphics2D)g, getCamera());
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
	
	private void updatePrevPos(MouseEvent e)
	{
		prevX = e.getX();
		prevY = e.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e){}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e)
	{
		updatePrevPos(e);
	}

	@Override
	public void mouseReleased(MouseEvent e){}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		cam.dragPan(prevX - e.getX(), prevY - e.getY());
		updatePrevPos(e);
		
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		totalScroll = Math.max(totalScroll - e.getWheelRotation(), 1);
		cam.scale = totalScroll / 10f;
		
		repaint();
	}
}
