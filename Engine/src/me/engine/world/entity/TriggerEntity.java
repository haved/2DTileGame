package me.engine.world.entity;

import me.engine.physics.Collider;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionShape;
import me.engine.physics.Trigger;
import me.engine.physics.TriggerOwner;
import me.engine.world.World;
import me.engine.world.layer.DynamicLayer;
import me.engine.world.layer.DynamicPhysicsLayer;

public class TriggerEntity extends Entity implements TriggerOwner
{
	private Trigger trigger;
	
	public TriggerEntity()
	{
		trigger = new Trigger(this);
	}
	
	public TriggerEntity(int x, int y, int width, int height)
	{
		this();
		setBounds(x, y, width, height);
	}
	
	@Override
	public void update(World world, DynamicLayer layer)
	{
		if(layer instanceof DynamicPhysicsLayer)
		{
			trigger.lookForTriggers(world, (DynamicPhysicsLayer)layer);
		}
	}
	
	public CollisionShape getTriggerShape()
	{
		return new CollisionBox(getX(), getY(), getX2(), getY2());
	}

	@Override
	public void onTriggerTouched(World world, DynamicPhysicsLayer layer, Collider c)
	{
		
	}
}
