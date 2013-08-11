package me.engine.asset;

import java.io.FileInputStream;

import me.engine.lib.LogHelper;

public enum AssetReaders
{
	TEXTURE(TextureReader.class),
	WORLD(WorldReader.class);
	
	private AssetReader reader;
	
	AssetReaders(Class<? extends AssetReader> clazz)
	{
		try
		{
			reader = clazz.newInstance();
		}
		catch (Exception e)
		{
			LogHelper.l.severe("Failed to make instance of 'AssetReaders." + name() + "' reader class");
			e.printStackTrace();
		}
	}

	public void read(AssetPack pack, AssetInfo info, FileInputStream in)
	{
		reader.read(pack, info, in);
	}
	
	public void unload(AssetPack pack, AssetInfo info)
	{
		reader.unload(pack, info);
	}
}
