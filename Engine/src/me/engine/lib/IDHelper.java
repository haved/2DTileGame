package me.engine.lib;

public final class IDHelper
{
	private static int prevNumber = 0;
	
	public static int getID()
	{
		prevNumber++;
		
		if(prevNumber == 0)
		{
			prevNumber += 2;
			return prevNumber;
		}
		else
		{
			prevNumber++;
			return prevNumber;
		}
	}
}
