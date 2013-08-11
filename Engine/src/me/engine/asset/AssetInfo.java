package me.engine.asset;

public class AssetInfo
{
	public String fileName;
	public AssetFileType fileType;
	
	public AssetInfo(String fileName, AssetFileType fileType)
	{
		this.fileName = fileName;
		this.fileType = fileType;
	}
}
