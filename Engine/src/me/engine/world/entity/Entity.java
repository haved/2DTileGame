package me.engine.world.entity;

import me.engine.math.RectangleI;
import me.engine.world.World;
import me.engine.world.layer.DynamicLayer;
import me.engine.world.layer.Layer;

public class Entity
{
	private boolean alive;
	protected int x, y, width, height;
	
	public Entity()
	{
		alive = true;
	}
	
	public Entity(int x, int y, int width, int height)
	{
		this();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update(World world, DynamicLayer layer)
	{
		
	}
	
	public void render(World world, Layer layer)
	{
		
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void kill()
	{
		alive = false;
	}
	
	public void setBounds(RectangleI bounds)
	{
		this.x = bounds.getX();
		this.y = bounds.getY();
		this.width = bounds.getWidth();
		this.height = bounds.getHeight();
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public RectangleI getBounds()
	{
		return new RectangleI(x, y, width, height);
	}
	
	public void setX(int x)
	{
		this.x = x;
	}

	public int getX()
	{
		return x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getY()
	{
		return y;
	}
	
	public int getX2()
	{
		return x + width;
	}
	
	public int getY2()
	{
		return y + height;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getWidth()
	{
		return width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getHeight()
	{
		return height;
	}
	
	public int getXCenter()
	{
		return getX() + getWidth() / 2;
	}
	
	public int getYCenter()
	{
		return getY() + getHeight() / 2;
	}
	
	public void move(int x, int y)
	{
		this.x += x;
		this.y += y;
	}
}