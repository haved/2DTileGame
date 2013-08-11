package me.engine.physics;

import me.engine.world.World;
import me.engine.world.layer.DynamicPhysicsLayer;

public class Trigger
{
	private TriggerOwner owner;
	
	public Trigger(TriggerOwner owner)
	{
		this.setOwner(owner);
	}
	
	public void lookForTriggers(World world, DynamicPhysicsLayer layer)
	{
		if(owner == null) return;
		
		for(Collider c:layer.getColliders())
		{
			if(c.getShape().intersect(owner.getTriggerShape()))
			{
				owner.onTriggerTouched(world, layer, c);
			}
		}
	}
	
	public TriggerOwner getOwner()
	{
		return owner;
	}

	public void setOwner(TriggerOwner owner)
	{
		this.owner = owner;
	}
}
