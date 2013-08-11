package me.engine.asset;

import java.io.FileInputStream;

import me.engine.world.World;
import me.engine.world.WorldUniverse;


public final class WorldReader implements AssetReader
{
	@Override
	public Object read(AssetPack pack, AssetInfo info, FileInputStream in)
	{
		WorldUniverse.addWorld(new World(2048, 640), pack.getName() + ";" + info.fileName);
		
		return null;
	}

	@Override
	public void unload(AssetPack pack, AssetInfo info)
	{
		WorldUniverse.removeWorld(pack.getName() + ";" + info.fileName);
	}
}
