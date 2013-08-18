package me.engine.asset;

public class TextureAsset extends Asset
{
	public TextureAsset(String file)
	{
		super(file);
	}

	@Override
	public void load()
	{
		System.out.println("load:" + file);
	}

	@Override
	public void unload()
	{
		System.out.println("unload:" + file);
	}
}
