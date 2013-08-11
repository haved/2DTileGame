package me.game.world.entity;

import me.engine.physics.Collider;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionShape;
import me.engine.render.RenderEngine;
import me.engine.world.World;
import me.engine.world.entity.Entity;
import me.engine.world.layer.DynamicLayer;
import me.engine.world.layer.Layer;

public class BreakableEntity extends Entity implements Collider
{
	private float current;
	private float threshold;
	
	public BreakableEntity(float threshold)
	{
		setThreshold(threshold);
	}
	
	public BreakableEntity(int x, int y, int width, int height, float threshold)
	{
		super(x, y, width, height);
		setThreshold(threshold);
	}
	
	@Override
	public void update(World world, DynamicLayer layer)
	{
		if(current >= threshold)
		{
			kill();
		}
		current = 0;
	}
	
	@Override
	public void render(World world, Layer layer)
	{
		RenderEngine.setColorF(65f / 255, 105f / 255, 225f / 255, 1);
		RenderEngine.drawRectangle(0, 0, getWidth(), getHeight());
	}
	
	public float getThreshold()
	{
		return threshold;
	}

	public void setThreshold(float threshold)
	{
		this.threshold = threshold;
	}

	public boolean ghost()
	{
		return false;
	}

	public CollisionShape getShape()
	{
		return new CollisionBox(getX(), getY(), getX2(), getY2());
	}

	public boolean acceptForce()
	{
		return true;
	}

	public void sendXMoment(float xMoment)
	{
		current += Math.abs(xMoment);
	}

	public void sendYMoment(float yMoment)
	{
		current += Math.abs(yMoment);
	}
}
