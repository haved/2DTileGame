package me.game.world.entity;

import me.engine.io.IOElement;
import me.engine.lib.GameTime;
import me.engine.lib.IDHelper;
import me.engine.math.RectangleI;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionPool;
import me.engine.physics.CollisionShape;
import me.engine.physics.DynamicCollider;
import me.engine.render.RenderEngine;
import me.engine.render.Sprite;
import me.engine.world.World;
import me.engine.world.entity.DynamicEntity;
import me.engine.world.layer.DynamicLayer;
import me.engine.world.layer.Layer;
import me.engine.world.layer.MainLayer;

public class BoxEntity extends DynamicEntity implements DynamicCollider, IOElement
{
	public static Sprite sprite;
	
	private String ID;
	
	public BoxEntity(int x, int y, int width, int height)
	{
		if(sprite == null)
		{
			sprite = new Sprite("core;Spritesheet.png", new RectangleI(64, 64, 32, 32));
		}
		
		gravity = 1;
		mass = 4;
		hardness = 1f;
		airFric = 0.2f;
		groundFric = 0.5f;
		tileColliding = true;
		boxColliding = true;
		setBounds(x, y, width, height);
	}

	public BoxEntity(int x, int y, int width, int height, float mass)
	{
		this(x, y, width, height);
		setMass(mass);
	}

	@Override
	public void update(World world, DynamicLayer layer)
	{
		if(layer instanceof CollisionPool)
		{
			updateGroundData((CollisionPool) layer);
			updateSpeed((CollisionPool) layer);
		}

		move(world, (MainLayer) layer);
	}

	@Override
	public void render(World world, Layer layer)
	{
		RenderEngine.resetColor();
		sprite.render(0, 0, getWidth(), getHeight());
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
	
	public void setID(String ID)
	{
		this.ID = ID;
	}
	
	public String getID()
	{
		if(ID == null || ID.equals(""))
		{
			ID = getClass().getName() + ":" + IDHelper.getID();
		}
		
		return ID;
	}

	public void onEvent(String event)
	{
		if(event.equals("Fly"))
		{
			addYMoment(-10 * GameTime.deltaTime());
		}
		if(event.equals("Kill"))
		{
			kill();
		}
	}
}
