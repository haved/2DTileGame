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
	
	public void load()
	{
		
	}
	
	public void unload()
	{
		
	}
}
