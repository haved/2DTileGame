/**@author HKrogstie*/
package me.editor.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import me.editor.core.Camera;
import me.editor.world.layer.Layer;
import me.editor.world.layer.MainLayer;

public class World implements Serializable
{
	private static final long serialVersionUID = 2682184515624389813L;
	
	public int width, height;
	
	public ArrayList<Layer> layers;
	public MainLayer mainLayer;
	
	public HashMap<String, Point> points;
	
	public Color color;
	
	public World(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.layers = new ArrayList<Layer>();
		this.mainLayer = new MainLayer();
		layers.add(mainLayer);
		
		this.color = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
	}
	
	public void render(Graphics2D g, Camera cam)
	{
		g.scale(cam.scale, cam.scale);
		
		drawLayers(g, cam);
		
		resetTransform(g);
	}
	
	private void drawLayers(Graphics2D g, Camera cam)
	{
		drawColorBg(clone(g), cam);
		for(Layer l:layers)
		{
			l.render(clone(g), cam);
		}
	}
	
	private void drawColorBg(Graphics2D g, Camera cam)
	{
		g.translate(-cam.scrollX, -cam.scrollY);
		
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(-1, -1, width + 1, height + 1);
	}
	
	private static Graphics2D clone(Graphics2D g)
	{
		return (Graphics2D) g.create();
	}
	
	private static void resetTransform(Graphics2D g)
	{
		g.setTransform(new AffineTransform(1, 0, 0, 1, 0, 0));
	}
}
