package me.engine.asset;

import java.io.FileInputStream;

import me.engine.lib.TextureUtil;

import org.newdawn.slick.opengl.Texture;

public class TextureAsset extends Asset
{
	private Texture texture;

	@Override
	public void load()
	{
		try
		{
			texture = TextureUtil.loadTexture("PNG", new FileInputStream(getFile()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void unload()
	{
		try
		{
			texture.release();
			texture = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
}
