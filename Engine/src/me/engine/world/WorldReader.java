package me.engine.world;

import java.io.File;

public final class WorldReader
{
	public static WorldInit worldInit;
	
	public static World getWorld(File f)
	{
		World out = worldInit.makeWorld();
		worldInit.initWorld(out);
		
		out.setPacks(worldInit.getPacks());
		
		return out;
	}
}
