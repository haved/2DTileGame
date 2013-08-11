package me.engine.math;

public class RectangleI
{
	private Vector2i pos;
	private Vector2i size;
	
	public RectangleI()
	{
		this.setLocation(new Vector2i());
		this.setSize(new Vector2i());
	}
	
	public RectangleI(Vector2i loaction, Vector2i size)
	{
		this.setLocation(loaction);
		this.setSize(size);
	}
	
	public RectangleI(int x, int y, int width, int height)
	{
		pos = new Vector2i(x, y);
		size = new Vector2i(width, height);
	}

	public void setLocation(Vector2i pos)
	{
		this.pos = pos;
	}

	public Vector2i getLocation()
	{
		return pos;
	}

	public void setSize(Vector2i size)
	{
		this.size = size;
	}

	public Vector2i getSize()
	{
		return size;
	}

	public int getX()
	{
		return pos.getX();
	}
	
	public int getX2()
	{
		return getX() + getWidth();
	}
	
	public void setX(int x)
	{
		pos.setX(x);
	}
	
	public int getY()
	{
		return pos.getY();
	}
	
	public int getY2()
	{
		return getY() + getHeight();
	}
	
	public void setY(int y)
	{
		pos.setY(y);
	}
	
	public int getWidth()
	{
		return size.getX();
	}
	
	public void setWidth(int width)
	{
		size.setX(width);
	}
	
	public int getHeight()
	{
		return size.getY();
	}
	
	public void setHeight(int height)
	{
		size.setY(height);
	}

	public boolean isTouching(Vector2i point)
	{
		return point.getX() >= getX() & point.getY() >= getY() &
		point.getX() <= getX2() & point.getY() <= getY2();
	}
	
	@Override
	public String toString()
	{
		return "RectangleI(" +  getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() + ")";
	}
}