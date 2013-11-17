package me.game.world.entity;

import org.lwjgl.input.Keyboard;

import me.engine.lib.GameTime;
import me.engine.lib.IDHelper;
import me.engine.lib.Util;
import me.engine.math.RectangleI;
import me.engine.physics.CollisionBox;
import me.engine.physics.CollisionPool;
import me.engine.physics.CollisionShape;
import me.engine.physics.DynamicCollider;
import me.engine.render.AnimatedSprite;
import me.engine.render.RenderEngine;
import me.engine.world.World;
import me.engine.world.entity.PlayerEntity;
import me.engine.world.layer.DynamicLayer;
import me.engine.world.layer.Layer;
import me.engine.world.layer.MainLayer;

public class MyPlayerEntity extends PlayerEntity implements DynamicCollider
{
	private AnimatedSprite sprite;
	public float jumpForce;
	public int speed = 300;
	public float accel = 10;
	
	private int move = 0;
	private boolean right;
	
	public MyPlayerEntity()
	{
		sprite = new AnimatedSprite("core;Spritesheet.png");
		sprite.texturePos = Util.makeTable(new RectangleI(5 * 32, 0, 32, 3 * 32),
				new RectangleI(6 * 32, 0, 32, 3 * 32), new RectangleI(7 * 32, 0, 32, 3 * 32),
				new RectangleI(8 * 32, 0, 32, 3 * 32));
		
		setBounds(0, 0, 32, 32 * 3);
		mass = 10;
		gravity = 1;
		jumpForce = 550 * mass;
		
		hardness = 0.95f;
		
		tileColliding = true;
		boxColliding = true;
	}

	@Override
	public void update(World world, DynamicLayer layer)
	{
		updateGroundData((CollisionPool) layer);
		input(layer);
		updateSpeed((CollisionPool) layer);
		xSpeed = Util.capFloat(xSpeed, -speed, +speed);
		
		move(world, (MainLayer) layer);
	}
	
	@Override
	protected void moveLeft(CollisionPool pool)
	{
		move += 1;
		super.moveLeft(pool);
	}
	
	@Override
	protected void moveRight(CollisionPool pool)
	{
		move += 1;
		super.moveRight(pool);
	}
	
	protected void input(Layer layer)
	{
		float current = accel * getFric();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			right = false;
			addXSpeed(-current * GameTime.deltaTime());
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			right = true;
			addXSpeed(+current * GameTime.deltaTime());
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP) & canJump(layer))
		{
			setYSpeed(-jumpForce / mass);
		}
		
		if(Math.abs(getXSpeed()) < 1)
		{
			move = 0;
		}
	}

	protected boolean canJump(Layer layer)
	{
		if(getYSpeed() < 0) return false;

		if(layer instanceof MainLayer)
		{
			return tileDown((MainLayer) layer) || blockDown((MainLayer) layer) != null;
		}

		return true;
	}

	@Override
	public void render(World world, Layer layer)
	{
		RenderEngine.resetColor();
		sprite.render((move / 40) % sprite.texturePos.length, getTexturePos());
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
	
	@Override
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
			addYMoment(-50 * GameTime.deltaTime());
		}
	}
}
