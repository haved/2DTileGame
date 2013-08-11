package me.engine.lib;

import me.engine.core.MainEngine;
import me.engine.math.RectangleI;
import me.engine.math.Vector2i;

public final class Util
{
	public static void stopEngine()
	{
		MainEngine.instance.stop();
	}
	
	public static void forceStop()
	{
		LogHelper.l.warning("Force stopped!");
		System.exit(0);
	}
	
	public static void delay(long ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			LogHelper.l.warning("Unable to sleep thread '" + Thread.currentThread().getName() + "' for " + ms + "milliseconds");
		}
	}
	
	public static int capInt(int i, int min, int max)
	{
		i = Math.max(i, min);
		i = Math.min(i, max);
		
		return i;
	}
	
	public static float capFloat(float i, float min, float max)
	{
		i = Math.max(i, min);
		i = Math.min(i, max);
		
		return i;
	}
	
	public static RectangleI[] makeTable(RectangleI... i)
	{
		return i;
	}
	
	public static Vector2i cap(Vector2i vec, RectangleI rect)
	{
		vec.setX(Math.max(vec.getX(), rect.getX()));
		vec.setY(Math.max(vec.getY(), rect.getY()));
		vec.setX(Math.min(vec.getX(), rect.getX2()));
		vec.setY(Math.min(vec.getY(), rect.getY2()));
		
		return vec;
	}
	
	/**
	 * Gives you a random number that can go from 0, all the way to 1 under the parameter you give.
	 * 
	 * @param max is the maximum number this method can return
	 * @return a random number between 0 and max. (0 to max-1)
	 */
	public static int randomInt(int max)
	{
		return (int) (Math.random() * max);
	}
	
	public static float randomFloat(int max)
	{
		return (float) (Math.random() * max);
	}
}