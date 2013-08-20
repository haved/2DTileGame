package me.engine.world;

import java.util.HashMap;

public class WorldLib
{
	public static String current;
	
	public static HashMap<String, WorldPack> packs = new HashMap<String, WorldPack>();
	
	public static void changeWorldPack(String pack)
	{
		if(getPack(current) != null)
		{
			getPack(current).unload();
		}
		
		current = pack;
		
		if(getPack(current) != null)
		{
			getPack(current).load();
		}
	}
	
	public static WorldPack getPack(String name)
	{
		return packs.get(name);
	}
}
