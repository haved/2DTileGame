package me.engine.guiobject;

import me.engine.math.RectangleI;

public abstract class GuiObject
{
	private RectangleI bounds = new RectangleI();
	
	public abstract void update();
	
	public abstract void render();
	
	public void setBounds(RectangleI bounds)
	{
		this.bounds = bounds;
	}
	
	public RectangleI getBounds()
	{
		return bounds;
	}
	
	public void setX(int x)
	{
		bounds.setX(x);
	}

	public int getX()
	{
		return bounds.getX();
	}

	public void setY(int y)
	{
		bounds.setY(y);
	}

	public int getY()
	{
		return bounds.getY();
	}

	public void setWidth(int width)
	{
		bounds.setWidth(width);
	}

	public int getWidth()
	{
		return bounds.getWidth();
	}

	public void setHeight(int height)
	{
		bounds.setHeight(height);
	}

	public int getHeight()
	{
		return bounds.getHeight();
	}
}
