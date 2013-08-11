package me.engine.asset;

import java.io.FileInputStream;

public enum AssetFileType
{
	PNG(AssetReaders.TEXTURE),
	MAP(AssetReaders.WORLD);
	
	private AssetReaders readerType;
	
	AssetFileType(AssetReaders readerType)
	{
		this.readerType = readerType;
	}

	public void read(AssetPack pack, String fileName, FileInputStream in)
	{
		readerType.read(pack, new AssetInfo(fileName, this), in);
	}
	
	public void unload(AssetPack pack, String fileName)
	{
		readerType.unload(pack, new AssetInfo(fileName, this));
	}
}
