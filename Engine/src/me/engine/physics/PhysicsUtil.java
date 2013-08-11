package me.engine.physics;

import me.engine.lib.Axis;
import me.engine.world.entity.MovingEntity;

public final class PhysicsUtil
{
	public static float slowValue(float val, float rate)
	{
		val *= rate;
		
		return val > 1f | val < -1f ? val : 0;
	}
	
	public static void collide(Axis a, MovingEntity ent1, MovingEntity ent2)
	{
		float moment1 = a.getValue(ent1.getSpeed()) * ent1.getMass();
		float moment2 = a.getValue(ent2.getSpeed()) * ent2.getMass();
		
		float out = moment1 + moment2 / 2;
		
		ent1.setSpeed(a.setVector(ent1.getSpeed(), out));
		ent2.setSpeed(a.setVector(ent2.getSpeed(), out));
	}
}
