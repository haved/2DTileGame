package me.engine.physics;

import me.engine.world.World;
import me.engine.world.layer.DynamicPhysicsLayer;

public interface TriggerOwner
{
	public CollisionShape getTriggerShape();
	
	public void onTriggerTouched(World world, DynamicPhysicsLayer layer, Collider c);
}
