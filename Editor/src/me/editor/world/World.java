/**@author HKrogstie*/
package me.editor.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import me.editor.core.Camera;
import me.editor.core.Util;
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
	}
	
	private void drawLayers(Graphics2D g, Camera cam)
	{
		drawColorBg(Util.cloneGraphics(g), cam);
		for(Layer l:layers)
		{
			l.render(Util.cloneGraphics(g), cam);
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
}
