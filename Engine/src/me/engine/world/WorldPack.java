package me.engine.world;

import java.io.File;
import java.util.HashMap;

public class WorldPack
{	
	private File location;
	private HashMap<String, World> maps;
	
	public WorldPack(File location)
	{
		this.location = location;
		maps = new HashMap<String, World>();
	}
	
	public boolean openPack()
	{
		return true;
	}
	
	public void load()
	{
		
	}
	
	public void unload()
	{
		
	}
	
	public World getWorld(String name)
	{
		return maps.get(name);
	}
}
