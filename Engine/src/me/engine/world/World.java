package me.engine.world;

import java.util.ArrayList;
import java.util.HashMap;

import me.engine.io.Event;
import me.engine.io.IOElement;
import me.engine.math.Vector2i;
import me.engine.render.RenderEngine;
import me.engine.world.entity.Entity;
import me.engine.world.entity.PlayerEntity;
import me.engine.world.layer.Layer;
import me.engine.world.layer.MainLayer;

public class World
{
	private ArrayList<String> packs;
	
	private ArrayList<Layer> bgs;
	private ArrayList<Layer> fgs;
	private MainLayer mainLayer;
	
	private int xSize;
	private int ySize;
	
	private HashMap<String, IOElement> io;
	private HashMap<String, Vector2i> points;
	
	public World()
	{
		this(0, 0);
	}
	
	public World(int width, int height)
	{
		setXSize(width);
		setYSize(height);
		
		setPacks(new ArrayList<String>());
		
		bgs = new ArrayList<Layer>();
		fgs = new ArrayList<Layer>();
		
		io = new HashMap<String, IOElement>();
		points = new HashMap<String, Vector2i>();
		
		setMainLayer(new MainLayer(this));
	}
	
	public int getXSize()
	{
		return xSize;
	}

	public void setXSize(int xSize)
	{
		this.xSize = xSize;
	}

	public int getYSize()
	{
		return ySize;
	}

	public void setYSize(int ySize)
	{
		this.ySize = ySize;
	}

	public ArrayList<String> getPacks() {
		return packs;
	}

	public void setPacks(ArrayList<String> packs) {
		this.packs = packs;
	}

	public void addPlayer(PlayerEntity player)
	{
		mainLayer.addPlayer(player);
		addIOElement(player);
	}
	
	public void removePlayer()
	{
		removeIOElement(mainLayer.getPlayer());
		mainLayer.removePlayer();
	}
	
	public void addIOElement(IOElement e)
	{	
		io.put(e.getID(), e);
	}
	
	public void removeIOElement(IOElement e)
	{	
		io.remove(e.getID());
	}
	
	public void addPoint(String name, Vector2i point)
	{
		System.out.println("Added point " + point.toString());
		points.put(name, point);
	}
	
	public void removePoint(String name)
	{
		points.remove(name);
	}
	
	public Vector2i getPoint(String name)
	{
		return points.get(name);
	}
	
	public void callEvent(String id, String event)
	{
		IOElement element = io.get(id);
		
		if(element != null)
		{
			element.onEvent(event);
		}
	}
	
	public void callEvent(Event event)
	{
		callEvent(event.id, event.event);
	}
	
	/**
	 * Adds an entity to the MAIN layer;
	 * 
	 * @param e
	 */
	public void addEntity(Entity e)
	{
		mainLayer.addEntity(e);
	}
	
	public void addBackground(Layer bg)
	{
		bgs.add(bg);
	}
	
	public void addForground(Layer fg)
	{
		fgs.add(fg);
	}
	
	public void setMainLayer(MainLayer mainLayer)
	{
		this.mainLayer = mainLayer;
	}

	public MainLayer getMainLayer()
	{
		return mainLayer;
	}
	
	public void update()
	{
		mainLayer.tick(this);
	}
	
	public void render()
	{
		Vector2i scroll = getMainLayer().getPlayer().getScroll(this);
		
		renderBackgrounds(scroll);
		mainLayer.render(this, scroll.getX(), scroll.getY());
		renderForground(scroll);
	}
	
	private void renderBackgrounds(Vector2i scroll)
	{
		for(Layer bg:bgs)
		{
			RenderEngine.resetColor();
			bg.render(this, scroll.getX(), scroll.getY());
		}
	}
	
	private void renderForground(Vector2i scroll)
	{
		for(Layer fg:fgs)
		{
			fg.render(this, scroll.getX(), scroll.getY());
		}
	}

}