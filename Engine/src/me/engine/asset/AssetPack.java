package me.engine.asset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class AssetPack
{
	private boolean stayLoaded;
	
	private File location;
	private String name;
	private HashMap<String, Asset> assets;
	
	private boolean loaded;
	
	public AssetPack(File f)
	{
		assets = new HashMap<String, Asset>();
		
		location = f;
		setName(f.getName());
	}
	
	public boolean openPack()
	{
		boolean hasConfing = false;
		File[] files = location.listFiles();
		
		for(File file:files)
		{
			if(file.getName().endsWith(".apk"))
			{
				hasConfing = true;
				readConfig(file);
			}
			else
			{
				openAsset(file);
			}
		}
		
		if(stayLoaded && hasConfing)
		{
			load();
		}
		
		return hasConfing;
	}
	
	public void load()
	{
		if(loaded == false)
		{
			loadPack();
		}
	}
	
	public void unload()
	{
		if(loaded & !stayLoaded)
		{
			unloadPack();
		}
	}
	
	private void openAsset(File f)
	{
		for(AssetTypes type:AssetTypes.values())
		{
			if(f.getName().toLowerCase().endsWith("." + type.getFileType().toLowerCase()))
			{
				Asset a = type.makeInstance();
				
				if(a != null)
				{
					a.setFile(f.getAbsolutePath());
					assets.put(f.getName(), a);
				}
			}
		}
	}
	
	private void readConfig(File f)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(f));
			
			String s = null;
			while((s = reader.readLine()) != null)
			{
				if(s.startsWith("stayLoaded") && s.endsWith("true"))
					stayLoaded = true;
			}
			
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void loadPack()
	{
		for(Asset a:assets.values())
		{
			a.load();
		}
		
		loaded = true;
	}
	
	private void unloadPack()
	{
		for(Asset a:assets.values())
		{
			a.unload();
		}
		
		loaded = false;
	}
	
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}

	public Asset getAsset(String name)
	{
		return assets.get(name);
	}
	
	public void setAsset(String name, Asset a)
	{
		assets.put(name, a);
	}
}
