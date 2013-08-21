package me.engine.world;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import me.engine.asset.AssetLib;

public class WorldPack
{
	private String name;
	private File location;
	private ArrayList<String> mapFiles;
	private HashMap<String, World> maps;
	
	public WorldPack(File location)
	{
		this.setName(location.getName());
		this.location = location;
		this.mapFiles = new ArrayList<String>();
		this.maps = new HashMap<String, World>();
	}
	
	public boolean openPack()
	{
		boolean hasConfig = false;
		File[] files = location.listFiles();
		
		for(File file:files)
		{
			if(file.getName().endsWith(".wpk"))
			{
				hasConfig = true;
				readConfig(file);
			}
			
			if(file.getName().endsWith(".map"))
			{
				mapFiles.add(file.getAbsolutePath());
			}
		}
			
		return hasConfig;
	}
	
	private void readConfig(File f)
	{
//		try
//		{
//			BufferedReader reader = new BufferedReader(new FileReader(f));
//			
//			String s = null;
//			while((s = reader.readLine()) != null)
//			{
//				if(s.startsWith("#")) continue;
//			}
//			
//			reader.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	
	
	public void load()
	{
		for(String mapFile:mapFiles)
		{
			File f = new File(mapFile);
			if(f.exists() && f.isFile())
			{
				loadWorld(f);
			}
		}
	}
	
	private void loadWorld(File f)
	{
		World world = WorldReader.getWorld(f);
		if(world != null)
		{
			maps.put(f.getName(), world);
			
			for(String pack:world.getPacks())
			{
				if(AssetLib.getPack(pack) != null)
				{
					AssetLib.getPack(pack).load();
				}
			}
		}
	}
	
	public void unload()
	{
		for(World world:maps.values())
		{
			unloadWorld(world);
		}
	}
	
	private void unloadWorld(World world)
	{
		for(String pack:world.getPacks())
		{
			if(AssetLib.getPack(pack) != null)
			{
				AssetLib.getPack(pack).unload();
			}
		}
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
