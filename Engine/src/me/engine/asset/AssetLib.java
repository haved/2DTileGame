package me.engine.asset;

import java.io.File;
import java.util.HashMap;

import me.engine.lib.LogHelper;

public class AssetLib
{
	public static HashMap<String, AssetPack> packs = new HashMap<String, AssetPack>();
	
	public static void load(String ContentLoc)
	{
		File loc = new File(ContentLoc + "/assets");
		
		if(loc.exists() && loc.isDirectory())
		{
			File[] files = loc.listFiles();
			
			for(File f:files)
			{
				if(f.isDirectory())
				{
					openPack(f);
				}
			}
		}
		else
		{
			LogHelper.l.severe("Asset folder could not be found");
		}
	}
	
	private static void openPack(File f)
	{
		LogHelper.l.info("Opening asset pack:" + f.getAbsolutePath());
		AssetPack pack = new AssetPack(f);
		if(pack.openPack())
		{
			packs.put(pack.getName(), pack);
		}
	}

	public static AssetPack getPack(String name)
	{
		return packs.get(name);
	}
	
	public static Asset getAsset(String pack, String assetName)
	{
		if(getPack(pack) != null)
		{
			return getPack(pack).getAsset(assetName);
		}
		
		return null;
	}
	
	public static Asset getAsset(String path)
	{
		String pack = path.split(";")[0];
		String asset = path.split(";")[1];
		
		return getAsset(pack, asset);
	}
}
