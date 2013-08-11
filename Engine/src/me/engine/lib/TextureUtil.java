package me.engine.lib;

import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class TextureUtil
{
	public static Texture loadTexture(String fileType, FileInputStream in)
	{
		try
		{
			return TextureLoader.getTexture(fileType, in);
		}
		catch(Exception e)
		{
			LogHelper.l.warning("Failed to load texture '" + in.toString() + "' of type '" + fileType + "'");
			e.printStackTrace();
			return null;
		}
	}
}
