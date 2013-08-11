package me.engine.asset;

import java.io.File;
import java.io.FileInputStream;

import me.engine.lib.LogHelper;

public final class AssetLoader
{
	public static AssetInfo readAsset(AssetPack pack,File file)
	{
		if(canReadFile(file) == false) return null;
		
		return tryReadFile(pack, file);
	}
	
	private static boolean canReadFile(File file)
	{
		if(file == null)
		{
			LogHelper.l.warning("Failed to load file as Asset. It was null!");
			return false;
		}
		
		if(file.isDirectory())
		{
			LogHelper.l.warning("Trying to load directory '" + file.getAbsolutePath() + "' as an Asset!");
			return false;
		}
		
		if(file.exists() == false | file.canRead() == false)
		{
			LogHelper.l.warning("File '" + file.getAbsolutePath() + "' can not be read");
			return false;
		}
		
		return true;
	}

	private static AssetInfo tryReadFile(AssetPack pack, File file)
	{
		for(AssetFileType type:AssetFileType.values())
		{
			if(file.getName().endsWith("." + type.name().toLowerCase()))
			{
				read(pack, type, file);
				return new AssetInfo(file.getName(), type);
			}
		}
		LogHelper.l.warning("No AssetFileType found for " + file.getName());
		return null;
	}
	
	private static void read(AssetPack pack, AssetFileType fileType, File file)
	{
		try
		{
			fileType.read(pack, file.getName(), new FileInputStream(file));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
