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
		AssetPack pack = new AssetPack(f);
		if(pack.openPack())
		{
			packs.put(pack.getName(), pack);
		}
	}
}
