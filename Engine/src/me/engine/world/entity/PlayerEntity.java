package me.engine.world.entity;

import me.engine.core.MainCanvas;
import me.engine.io.IOElement;
import me.engine.lib.Util;
import me.engine.math.RectangleI;
import me.engine.math.Vector2i;
import me.engine.world.World;

public class PlayerEntity extends DynamicEntity implements IOElement
{
	protected String ID;
	protected World currentWorld;
	
	public PlayerEntity()
	{
		
	}
	
	public void moveToWorld(World world, int x, int y)
	{
		if(currentWorld != null)
		{
			currentWorld.removePlayer();
		}
		
		currentWorld = world;
		setX(x);
		setY(y);
		
		if(currentWorld != null)
		{
			currentWorld.addPlayer(this);
		}
	}
	
	public void moveToWorld(World world, String pointName)
	{
		Vector2i point = world.getPoint(pointName);
		
		if(point != null)
		{
			moveToWorld(world, point.getX(), point.getY());
		}
		else
		{
			moveToWorld(world, 0, 0);
		}
	}
	
	public void updateWorld()
	{
		if(currentWorld != null)
		{
			currentWorld.update();
		}
	}
	
	public void renderWorld()
	{
		if(currentWorld != null)
		{
			currentWorld.render();
		}
	}

	public World getWorld()
	{
		return currentWorld;
	}
	
	public void setID(String ID)
	{
		this.ID = ID;
	}
	
	@Override
	public String getID()
	{
		return ID;
	}

	@Override
	public void onEvent(String event)
	{
		
	}

	public Vector2i getScroll(World world)
	{
		Vector2i scroll = new Vector2i(getX() - (getWidth() + MainCanvas.resX) / 2,
									   getY() - (getHeight() + MainCanvas.resY) / 2);
		scroll = Util.cap(scroll, new RectangleI(0, 0,
				world.getXSize() - MainCanvas.resX,
				world.getYSize() - MainCanvas.resY));
		
		return scroll;
	}
}
