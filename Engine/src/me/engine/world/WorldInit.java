package me.engine.world;

import java.util.ArrayList;

public interface WorldInit
{
	public World makeWorld();
	
	public void initWorld(World world);
	
	public ArrayList<String> getPacks();
}
