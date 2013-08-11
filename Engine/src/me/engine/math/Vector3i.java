package me.engine.math;

public class Vector3i
{
	private int x;
	private int y;
	private int z;
	
	public Vector3i(){}
	
	public Vector3i(int x, int y, int z)
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);;
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
	
	public void setZ(int z)
	{
		this.z = z;
	}

	public int getZ()
	{
		return z;
	}

	public void move(int x, int y, int z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
	}
}
