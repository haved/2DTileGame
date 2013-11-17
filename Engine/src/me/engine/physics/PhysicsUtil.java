package me.engine.physics;

public final class PhysicsUtil
{
	public static float slowValue(float val, float rate)
	{
		val *= rate;
		
		return val > 1f | val < -1f ? val : 0;
	}
	
	public static float getMoment(float mass, float speed)
	{
		return mass * speed;
	}
	
	public static float getSpeed(float mass, float moment)
	{
		return moment / mass;
	}
	
	public static float getTransMoment(float moment, float hardness)
	{
		return moment * hardness;
	}
}
