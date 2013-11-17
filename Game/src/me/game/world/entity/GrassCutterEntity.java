package me.game.world.entity;

import me.engine.math.RectangleI;
import me.engine.physics.Collider;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionPool;
import me.engine.physics.CollisionShape;
import me.engine.physics.Trigger;
import me.engine.physics.TriggerOwner;
import me.engine.render.RenderEngine;
import me.engine.render.Sprite;
import me.engine.world.World;
import me.engine.world.entity.DynamicEntity;
import me.engine.world.layer.DynamicLayer;
import me.engine.world.layer.DynamicPhysicsLayer;
import me.engine.world.layer.Layer;
import me.engine.world.layer.MainLayer;

public class GrassCutterEntity extends DynamicEntity implements Collider, TriggerOwner
{
	private Sprite sprite;
	private Trigger trigger;
	
	private boolean right;
	
	public GrassCutterEntity(int x, int y, int width, int height)
	{
		setUpVariables();
		setBounds(x, y, width, height);
		
		trigger = new Trigger(this);
		sprite = new Sprite("Tutorial;Spritesheet.png", new RectangleI(0, 0, 128, 52));
	}
	
	private void setUpVariables()
	{
		gravity = 1;
		mass = 7;
		hardness = 0.75f;
		airFric = 2;
		groundFric =  3;
		tileColliding = true;
		boxColliding = true;
	}

	@Override
	public void update(World world, DynamicLayer layer)
	{
		updateSpeed((CollisionPool) layer);
		
		if(layer instanceof DynamicPhysicsLayer)
		{
			trigger.lookForTriggers(world, (DynamicPhysicsLayer)layer);
		}
		
		if(xSpeed > 0)
		{
			right = true;
		}
		else if(xSpeed < 0)
		{
			right = false;
		}
		move(world, (MainLayer) layer);
	}

	@Override
	public void render(World world, Layer layer)
	{
		RenderEngine.resetColor();
		sprite.render(getTexturePos());
	}
	
	private RectangleI getTexturePos()
	{
		if(right)
		{
			return new RectangleI(0, 0, getWidth(), getHeight());
		}
		else
		{
			return new RectangleI(getWidth(), 0, -getWidth(), getHeight());
		}
	}

	public CollisionShape getShape()
	{
		return new CollisionBox(getX(), getY(), getX2(), getY2());
	}

	public boolean acceptForce()
	{
		return true;
	}

	public boolean ghost()
	{
		return false;
	}

	public CollisionShape getTriggerShape()
	{
		int xPos = 9;
		
		if(right)
		{
			xPos = 128 - 9;
		}
		
		return new CollisionBox(getX() + xPos, getY2() - 10, getX() + xPos + 2, getY2());
	}

	public void onTriggerTouched(World world, DynamicPhysicsLayer layer, Collider c)
	{
		if(c instanceof GrassEntity)
		{
			((GrassEntity) c).kill();
		}
	}
}
