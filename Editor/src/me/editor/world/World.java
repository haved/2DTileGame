package me.editor.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class World implements Serializable
{
	private static final long serialVersionUID = 2682184515624389813L;
	
	public int width, height;
	
	public ArrayList<Layer> bg;
	public MainLayer layer;
	public ArrayList<Layer> fg;
	
	public HashMap<String, Point> points;
	
	public Color color;
	
	public World(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.color = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}
}
