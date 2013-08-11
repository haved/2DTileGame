package me.game.world.entity;

import me.engine.math.RectangleI;
import me.engine.physics.Collider;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionShape;
import me.engine.render.RenderEngine;
import me.engine.render.Sprite;
import me.engine.world.World;
import me.engine.world.entity.Entity;
import me.engine.world.layer.Layer;

public class GrassEntity extends Entity implements Collider
{
	private Sprite sprite;

	public GrassEntity()
	{

	}

	public GrassEntity(int x, int y, int width, int height)
	{
		this();
		setBounds(x, y, width, height);
	}
	
	public GrassEntity(int x, int y, int width, int height, Sprite sprite)
	{
		this(x, y, width, height);
		setSprite(sprite);
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public void setSprite(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	@Override
	public void render(World world, Layer layer)
	{
		if(sprite != null)
		{
			RenderEngine.resetColor();
			sprite.render(getTexturePos());
		}
	}
	
	private RectangleI getTexturePos()
	{
		if((System.currentTimeMillis() / 100) % 2 == 0)
		{
			return new RectangleI(0, 0, getWidth(), getHeight());
		}
		else
		{
			return new RectangleI(getWidth(), 0, -getWidth(), getHeight());
		}
	}

	public boolean ghost()
	{
		return true;
	}

	public CollisionShape getShape()
	{
		return new CollisionBox(getX(), getY(), getX2(), getY2());
	}

	public boolean acceptForce(){return false;}

	public float getXMoment(){return 0;}

	public void setXMoment(float xMoment){}

	public void sendXMoment(float xMoment){}

	public void addXMoment(float xMoment){}
	
	public float getYMoment(){return 0;}

	public void setYMoment(float yMoment){}

	public void sendYMoment(float xMoment){}

	public void addYMoment(float yMoment){}

	public float getHardness()
	{
		return 0;
	}
}
