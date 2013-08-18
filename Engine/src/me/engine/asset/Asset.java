package me.engine.asset;

public abstract class Asset
{
	protected String file;
	
	public abstract void load();
	
	public abstract void unload();
	
	public void setFile(String file)
	{
		this.file = file;
	}
	
	public String getFile()
	{
		return file;
	}
}
