package me.engine.math;

public class Vector2f
{
	private float x;
	private float y;
	
	public Vector2f(){}
	
	public Vector2f(float x, float y)
	{
		this.setX(x);
		this.setY(y);
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getX()
	{
		return x;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getY()
	{
		return y;
	}
	
	public void move(float x, float y)
	{
		this.x += x;
		this.y += y;
	}
}
