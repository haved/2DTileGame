package me.engine.math;

public class Vector3f
{
	private float x;
	private float y;
	private float z;
	
	public Vector3f(){}
	
	public Vector3f(float x, float y, float z)
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);;
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
	
	public void setZ(float z)
	{
		this.z = z;
	}

	public float getZ()
	{
		return z;
	}

	public void move(float x, float y, float z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
	}
}
