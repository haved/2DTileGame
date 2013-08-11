package me.engine.world.layer;

import me.engine.physics.Collider;
import me.engine.world.World;
import me.engine.world.entity.Entity;
import me.engine.world.entity.PlayerEntity;

/**
 * MainLayer er et Layer som brukes i World-klassen
 * 
 * @author Havard
 */
public class MainLayer extends DynamicTilePhysicsLayer
{
	private PlayerEntity player;

	public MainLayer(World world)
	{
		super(world);
	}

	public void addPlayer(PlayerEntity player)
	{
		this.player = player;
		
		if(player instanceof Collider)
		{
			addCollider((Collider)player);
		}
	}

	public PlayerEntity getPlayer()
	{
		return player;
	}

	public void removePlayer()
	{
		if(player instanceof Collider)
		{
			removeCollider((Collider)player);
		}
		
		player = null;
	}

	protected void renderEntities()
	{
		for(Entity e : entities)
		{
			renderEntity(e);
		}
		if(player != null)
		{
			renderEntity(player);
		}
	}

	@Override
	protected void tickEntities()
	{
		for(Entity e : entities)
		{
			e.update(world, this);
		}
		if(player != null)
		{
			player.update(world, this);
		}
	}
}
