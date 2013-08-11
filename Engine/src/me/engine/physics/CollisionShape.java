package me.engine.physics;

public interface CollisionShape
{
	public boolean isHoldingUp(CollisionShape shape);
	
	public boolean isHoldingDown(CollisionShape shape);
	
	public boolean isHoldingLeft(CollisionShape shape);
	
	public boolean isHoldingRight(CollisionShape shape);
	
	public boolean intersect(CollisionShape shape);
}
