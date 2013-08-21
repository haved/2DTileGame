package me.engine.world;

import java.io.File;
import java.util.HashMap;

public class WorldPack
{
	private String name;
	private File location;
	private HashMap<String, World> maps;
	
	public WorldPack(File location)
	{
		this.setName(location.getName());
		this.location = location;
		this.maps = new HashMap<String, World>();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
