package me.engine.world.layer;

import java.util.ArrayList;

import me.engine.physics.Collider;
import me.engine.physics.CollisionPool;
import me.engine.physics.TileMap;
import me.engine.world.World;
import me.engine.world.entity.Entity;

public class DynamicPhysicsLayer extends DynamicLayer implements CollisionPool
{
	private ArrayList<Collider> colliders;

	public DynamicPhysicsLayer(World world)
	{
		super(world);

		colliders = new ArrayList<Collider>();
	}

	@Override
	protected void onEntityAdded(Entity e)
	{
		super.onEntityAdded(e);

		if(e instanceof Collider)
		{
			addCollider((Collider) e);
		}
	}
	
	@Override
	protected void onEntityRemoved(Entity e)
	{
		super.onEntityAdded(e);
		
		if(e instanceof Collider)
		{
			removeCollider((Collider) e);
		}
	}

	@Override
	public void addCollider(Collider c)
	{
		colliders.add(c);
	}

	@Override
	public void removeCollider(Collider c)
	{
		if(hasCollider(c))
		{
			colliders.remove(c);
		}
	}

	@Override
	public ArrayList<Collider> getColliders()
	{
		return colliders;
	}

	@Override
	public boolean hasCollider(Collider c)
	{
		return colliders.contains(c);
	}

	
	@Override
	public TileMap getTileMap()
	{
		return null;
	}
	

	@Override
	public boolean hasTileMap()
	{
		return false;
	}
}
