package me.engine.world;

import java.io.File;
import java.util.HashMap;

import me.engine.lib.LogHelper;

public class WorldLib
{
	public static String current;
	
	public static HashMap<String, WorldPack> packs = new HashMap<String, WorldPack>();
	
	public static void load(String ContentLoc)
	{
		File f = new File(ContentLoc + "/world/");
		
		if(f.isDirectory())
		{
			File[] files = f.listFiles();
			
			for(File file:files)
			{
				if(file.isDirectory())
				{
					openPack(file);
				}
			}
		}
	}
	
	private static void openPack(File f)
	{
		LogHelper.l.info("Opening world pack:" + f.getAbsolutePath());
		WorldPack pack = new WorldPack(f);
		if(pack.openPack())
		{
			packs.put(pack.getName(), pack);
		}
	}
	
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
