package me.engine.world.entity;

import me.engine.core.GameTime;
import me.engine.physics.CollisionPool;

public class DynamicEntity extends MovingEntity
{
	protected float gravity;
	
	protected boolean onGround;
	
	protected float groundFric = 1;
	protected float airFric = 0.3f;
	
	public void updateSpeed(CollisionPool pool)
	{
		slow();
		addYSpeed(gravity * GameTime.deltaTime());
	}
	
	protected void slow()
	{
		if(xSpeed > 0)
		{
			addXSpeed(-getFric() * GameTime.deltaTime());
			xSpeed = Math.max(xSpeed, 0);
		}
		else if(xSpeed < 0)
		{
			addXSpeed(getFric() * GameTime.deltaTime());
			xSpeed = Math.min(xSpeed, 0);
		}
	}
	
	protected void updateGroundData(CollisionPool pool)
	{
		onGround = (tileColliding && tileDown(pool)) || (boxColliding && blockDown(pool) != null);
	}
	
	protected float getFric()
	{
		if(onGround)
		{
			return groundFric;
		}
		else
		{
			return airFric;
		}
	}
	
	public void setGravity(float grav)
	{
		this.gravity = grav;
	}
	
	public float getGravity()
	{
		return this.gravity;
	}
}
