package me.engine.asset;

import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;

import me.engine.lib.TextureUtil;
import me.engine.render.RenderEngine;

public class TextureReader implements AssetReader
{
	@Override
	public Object read(AssetPack pack, AssetInfo info, FileInputStream in)
	{
		Texture texture = TextureUtil.loadTexture(info.fileType.name(), in);
		
		RenderEngine.addTexture(texture, pack.getName() + ";" + info.fileName);
		
		return null;
	}

	@Override
	public void unload(AssetPack pack, AssetInfo info)
	{
		RenderEngine.releaseTexture(pack.getName() + ";" + info.fileName);
	}
}
