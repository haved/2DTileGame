package me.engine.math;

public class Vector2i
{
	private int x;
	private int y;
	
	public Vector2i(){}
	
	public Vector2i(int XY)
	{
		this(XY, XY);
	}
	
	public Vector2i(int x, int y)
	{
		this.setX(x);
		this.setY(y);
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
	
	public void move(int x, int y)
	{
		this.x += x;
		this.y += y;
	}
	
	@Override
	public String toString()
	{
		return "Vector2i(" + getX() + ", " + getY() + ")";
	}
}
