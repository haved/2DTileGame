package me.game.world.entity;

import me.engine.io.Event;
import me.engine.physics.Collider;
import me.engine.render.RenderEngine;
import me.engine.world.World;
import me.engine.world.entity.TriggerEntity;
import me.engine.world.layer.DynamicPhysicsLayer;
import me.engine.world.layer.Layer;

public class MyTrigger extends TriggerEntity
{
	private Event event;
	
	public MyTrigger()
	{
		super();
	}
	
	public MyTrigger(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}

	@Override
	public void render(World world, Layer layer)
	{
		RenderEngine.setColorF(0, 1, 0, 1);
		RenderEngine.drawRectangle(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void onTriggerTouched(World world, DynamicPhysicsLayer layer, Collider c)
	{
		if(event != null)
		{
			world.callEvent(event);
		}
	}
}
