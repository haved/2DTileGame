package me.engine.world.layer;

import me.engine.math.Vector2i;
import me.engine.physics.TileMap;
import me.engine.render.RenderEngine;
import me.engine.world.World;

public class DynamicTilePhysicsLayer extends DynamicPhysicsLayer
{
	protected TileMap map;
	
	public DynamicTilePhysicsLayer(World world)
	{
		super(world);
		map = new TileMap(new Vector2i(32), world.getXSize() / 32, world.getYSize() / 32);
	}
	
	@Override
	public void render(World world, int xScroll, int yScroll)
	{
		RenderEngine.pushMatrix();
		RenderEngine.translateF(-xScroll * xScrollMove, -yScroll * yScrollMove, 0);
		map.render();
		renderEntities();
		RenderEngine.popMatrix();
	}
	
	@Override
	public void tick(World world)
	{
		addNewEntities();
		tickEntities();
		cleanEntityCorps();
	}
	
	@Override
	public TileMap getTileMap()
	{
		return map;
	}
	
	@Override
	public boolean hasTileMap()
	{
		return map != null; //Oftest sant
	}
}
