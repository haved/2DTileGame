package me.engine.world.entity;

import me.engine.lib.GameTime;
import me.engine.math.Vector2f;
import me.engine.physics.Collider;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionPool;
import me.engine.physics.CollisionShape;
import me.engine.physics.DynamicCollider;
import me.engine.physics.PhysicsUtil;
import me.engine.world.World;
import me.engine.world.layer.DynamicLayer;

public class MovingEntity extends Entity
{
	protected float hardness = 0.9f;
	protected float bounce = 0.1f;
	
	protected boolean boxColliding;
	protected boolean tileColliding;
	
	protected float slowX;
	protected float slowY;
	
	protected float mass;
	
	protected float xSpeed; 
	protected float ySpeed;
	
	public MovingEntity()
	{
		mass = 1;
	}
	
	public void setXSpeed(float xSpeed)
	{
		this.xSpeed = xSpeed;
	}
	
	public void addXSpeed(float xSpeed)
	{
		this.xSpeed += xSpeed;
	}
	
	public float getXSpeed()
	{
		return this.xSpeed;
	}
	
	public void setYSpeed(float ySpeed)
	{
		this.ySpeed = ySpeed;
	}
	
	public void addYSpeed(float ySpeed)
	{
		this.ySpeed += ySpeed;
	}
	
	public float getYSpeed()
	{
		return this.ySpeed;
	}
	
	public Vector2f getSpeed()
	{
		return new Vector2f(xSpeed, ySpeed);
	}
	
	public void setMass(float mass)
	{
		this.mass = mass;
	}
	
	public float getMass()
	{
		return mass;
	}
	
	public void setHardness(float hardness)
	{
		this.hardness = hardness;
	}
	
	public float getHardness()
	{
		return hardness;
	}
	
	public float getXMoment()
	{
		return PhysicsUtil.getMoment(getMass(), getXSpeed());
	}
	
	public float getYMoment()
	{
		return mass * ySpeed;
	}
	
	public void setXMoment(float xMoment)
	{
		setXSpeed(PhysicsUtil.getSpeed(getMass(), xMoment));
	}
	
	public void setYMoment(float yMoment)
	{
		setYSpeed(PhysicsUtil.getSpeed(getMass(), yMoment));
	}
	
	public void addXMoment(float xMoment)
	{
		addXSpeed(PhysicsUtil.getSpeed(getMass(), xMoment));
	}
	
	public void addYMoment(float yMoment)
	{
		addYSpeed(PhysicsUtil.getSpeed(getMass(), yMoment));
	}
	
	public void sendXMoment(float xMoment)
	{
		addXMoment(PhysicsUtil.getTransMoment(xMoment, getHardness()));
	}
	
	public void sendYMoment(float yMoment)
	{
		addYMoment(PhysicsUtil.getTransMoment(yMoment, getHardness()));
	}
	
	@Override
	public void update(World world, DynamicLayer layer)
	{
		super.update(world, layer);
		
		move(world, layer);
	}
	
	public void move(World world, Object object)
	{
		slowX += getXSpeed() * GameTime.deltaTime();
		slowY += getYSpeed() * GameTime.deltaTime();
		
		if(object instanceof CollisionPool)
		{
			moveCollision(world, (CollisionPool) object);
		}
		else
		{
			moveFreely(world);
		}
	}
	
	public void moveFreely(World world)
	{
		x += slowX / 1000;
		y += slowY / 1000;
		
		slowX = 0;
		slowY = 0;
	}
	
	public void moveCollision(World world, CollisionPool pool)
	{
		while(slowX >= 1000) // Right -------------------------------------------
		{
			slowX -= 1000;
			
			moveRight(pool);
		}
		
		while(slowX <= -1000) // Left--------------------------------------------------------------
		{
			slowX += 1000;
			
			moveLeft(pool);
		}
		
		while(slowY >= 1000) // Down------------------------------------
		{
			slowY -= 1000;
			
			moveDown(pool);
		}
		
		while(slowY <= -1000) // Up-------------------------------------------------------
		{
			slowY += 1000;
			
			moveUp(pool);
		}
	}
	
	protected void moveUp(CollisionPool pool)
	{
		if(tileColliding && tileUp(pool))
		{
			ySpeed = -ySpeed * bounce;
			slowY = 0;
			return;
		}
		
		if(boxColliding)
		{
			Collider c = blockUp(pool);
			
			if(c != null)
			{
				slowY = 0;
				if(c.acceptForce() & this.canSendForce())
				{
					if(c instanceof DynamicCollider)
					{
						dynamicCollideY((DynamicCollider) c);
					}
					else
					{
						c.sendYMoment(this.getYMoment() * this.getHardness());
						this.setYMoment(0);
					}
				}
				else
				{
					setYSpeed(0);
				}
				
				return;
			}
		}
		
		y--;
	}
	
	protected void moveDown(CollisionPool pool)
	{
		if(tileColliding && tileDown(pool))
		{
			ySpeed = -ySpeed * bounce;
			slowY = 0;
			return;
		}
		
		if(boxColliding)
		{
			Collider c = blockDown(pool);
			
			if(c != null)
			{
				slowY = 0;
				if(c.acceptForce() & this.canSendForce())
				{
					if(c instanceof DynamicCollider)
					{
						dynamicCollideY((DynamicCollider) c);
					}
					else
					{
						c.sendYMoment(this.getYMoment() * this.getHardness());
						this.setYMoment(0);
					}
				}
				else
				{
					setYSpeed(0);
				}
				
				return;
			}
		}
		
		y++;
		return;
	}
	
	protected void moveLeft(CollisionPool pool)
	{
		if(tileColliding && tileLeft(pool))
		{
			xSpeed = -xSpeed * bounce;
			slowX = 0;
			return;
		}
		
		if(boxColliding)
		{
			Collider c = blockLeft(pool);
			
			if(c != null)
			{
				slowX = 0;
				if(c.acceptForce() & this.canSendForce())
				{
					if(c instanceof DynamicCollider)
					{
						dynamicCollideX((DynamicCollider) c);
					}
					else
					{
						c.sendXMoment(this.getXMoment() * this.getHardness());
						this.setXMoment(0);
					}
				}
				else
				{
					setXSpeed(0);
				}
				
				return;
			}
		}

		x--;
	}
	
	protected void moveRight(CollisionPool pool)
	{
		if(tileColliding && tileRight(pool))
		{
			xSpeed = -xSpeed * bounce;
			slowX = 0;
			return;
		}
		
		if(boxColliding)
		{
			Collider c = blockRight(pool);
			
			if(c != null)
			{
				slowX = 0;
				if(c.acceptForce() & this.canSendForce())
				{
					if(c instanceof DynamicCollider)
					{
						dynamicCollideX((DynamicCollider) c);
					}
					else
					{
						c.sendXMoment(this.getXMoment() * this.getHardness());
						this.setXMoment(0);
					}
				}
				else
				{
					setXSpeed(0);
				}
				
				return;
			}
		}
		
		x++;
	}
	
	public void dynamicCollideY(DynamicCollider dc)
	{
		float myMoment = getYMoment() * getHardness();
		float cMoment = dc.getYMoment() * dc.getHardness();
		
		addYMoment(-myMoment);
		dc.addYMoment(-cMoment);
		
		dc.sendYMoment(myMoment);
		sendYMoment(cMoment);
	}
	
	public void dynamicCollideX(DynamicCollider dc)
	{
		float myMoment = getXMoment() * getHardness();
		float cMoment = dc.getXMoment() * dc.getHardness();
		
		addXMoment(-myMoment);
		dc.addXMoment(-cMoment);
		
		dc.sendXMoment(myMoment);
		sendXMoment(cMoment);
	}
	
	public Collider blockDown(CollisionPool pool)
	{
		for(Collider c:pool.getColliders())
		{
			if(c.ghost() == false && c.getShape().isHoldingUp(getShape()))
			{
				return c;
			}
		}
		
		return null;
	}
	
	public Collider blockUp(CollisionPool pool)
	{
		for(Collider c:pool.getColliders())
		{
			if(c.ghost() == false && c.getShape().isHoldingDown(getShape()))
			{
				return c;
			}
		}
		
		return null;
	}
	
	public Collider blockLeft(CollisionPool pool)
	{
		for(Collider c:pool.getColliders())
		{
			if(c.ghost() == false && c.getShape().isHoldingRight(getShape()))
			{
				return c;
			}
		}
		
		return null;
	}
	
	public Collider blockRight(CollisionPool pool)
	{
		for(Collider c:pool.getColliders())
		{
			if(c.ghost() == false && c.getShape().isHoldingLeft(getShape()))
			{
				return c;
			}
		}
		
		return null;
	}
	
	public boolean tileDown(CollisionPool pool)
	{
		if(pool.hasTileMap())
		{
			return pool.getTileMap().tileDown(getShape());
		}
		
		return false;
	}
	
	public boolean tileUp(CollisionPool pool)
	{
		if(pool.hasTileMap())
		{
			return pool.getTileMap().tileUp(getShape());
		}
		
		return false;
	}
	
	public boolean tileLeft(CollisionPool pool)
	{
		if(pool.hasTileMap())
		{
			return pool.getTileMap().tileLeft(getShape());
		}
		
		return false;
	}
	
	public boolean tileRight(CollisionPool pool)
	{
		if(pool.hasTileMap())
		{
			return pool.getTileMap().tileRight(getShape());
		}
		
		return false;
	}
	
	public void resetSpeed()
	{
		setXSpeed(0);
		setYSpeed(0);
	}
	
	public void setSpeed(float x, float y)
	{
		setXSpeed(x);
		setYSpeed(y);
	}
	
	public void setSpeed(Vector2f vect)
	{
		setXSpeed(vect.getX());
		setYSpeed(vect.getY());
	}
	
	public CollisionShape getShape()
	{
		return new CollisionBox(getX(), getY(), getX2(), getY2());
	}

	public boolean canSendForce()
	{
		return true;
	}
}
