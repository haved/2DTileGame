package me.engine.physics;

public interface Collider
{
	public boolean ghost();
	
	public CollisionShape getShape();
	
	public boolean acceptForce();
	
	public void sendXMoment(float xMoment);
	
	public void sendYMoment(float yMoment);
}
