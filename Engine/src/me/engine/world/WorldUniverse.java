package me.engine.world;

import java.util.HashMap;

public final class WorldUniverse
{
	private static HashMap<String, World> worlds = new HashMap<String, World>();
	
	public static World getWorld(String name)
	{
		return worlds.get(name);
	}
	
	public static void addWorld(World world, String name)
	{
		worlds.put(name, world);
	}
	
	public static void removeWorld(String name)
	{
		worlds.remove(name);
	}
	
	public static void clear()
	{
		worlds.clear();
	}
}
