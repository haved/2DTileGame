package me.engine.core;

public final class GameTime
{
	private static long start;
	private static long deltaTime;
	
	public static void start()
	{
		start = System.currentTimeMillis();
	}
	
	public static void finish()
	{
		setDeltaTime((System.currentTimeMillis() - start));
	}
	
	public static void setDeltaTime(long deltaTime)
	{
		GameTime.deltaTime = deltaTime;
	}
	
	public static long deltaTime()
	{
		return deltaTime;
	}
}
