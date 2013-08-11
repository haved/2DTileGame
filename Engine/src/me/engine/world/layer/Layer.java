package me.engine.world.layer;

import me.engine.world.World;

public interface Layer
{
	public void tick(World world);
	
	public void render(World world, int xScroll, int yScroll);
}
