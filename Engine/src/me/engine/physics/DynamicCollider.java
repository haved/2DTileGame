package me.engine.physics;

public interface DynamicCollider extends Collider
{
public float getXMoment();
	
	public void setXMoment(float xMoment);
	
	public void addXMoment(float xMoment);
	
	public float getYMoment();
	
	public void setYMoment(float yMoment);
	
	public void addYMoment(float yMoment);
	
	public float getHardness();
}
