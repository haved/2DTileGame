package me.engine.lib;

import me.engine.math.Vector2f;

/**
 * Hva tror du??
 * 
 * @author Havard
 */
public enum Axis
{
	X(0), Y(1);
	
	private int type;
	
	Axis(int type)
	{
		this.type = type;
	}
	
	public float getValue(Vector2f vect)
	{
		if(type == 0)
		{
			return vect.getX();
		}
		else
		{
			return vect.getY();
		}
	}
	
	public Vector2f setVector(Vector2f vect, float val)
	{
		if(type == 0)
		{
			vect.setX(val);
		}
		else
		{
			vect.setY(val);
		}
		
		return vect;
	}
}
