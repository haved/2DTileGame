package me.engine.world.layer;

import java.util.ArrayList;

import me.engine.io.IOElement;
import me.engine.render.RenderEngine;
import me.engine.world.World;
import me.engine.world.entity.Entity;

public class DynamicLayer implements Layer
{
	protected float xScrollMove = 1;
	protected float yScrollMove = 1;
	
	protected World world;
	
	protected ArrayList<Entity> newEntities;
	protected ArrayList<Entity> entities;
	
	
	public DynamicLayer()
	{
		newEntities = new ArrayList<Entity>();
		entities = new ArrayList<Entity>();
	}
	
	public DynamicLayer(World world)
	{
		this();
		this.world = world;
	}
	
	@Override
	public void render(World world, int xScroll, int yScroll)
	{
		RenderEngine.pushMatrix();
		RenderEngine.translateF(-xScroll * xScrollMove, -yScroll * yScrollMove, 0);
		renderEntities();
		RenderEngine.popMatrix();
	}
	
	protected void renderEntities()
	{
		for(Entity e : entities)
		{
			renderEntity(e);
		}
	}

	protected void renderEntity(Entity e)
	{
		RenderEngine.pushMatrix();
		RenderEngine.translateF(e.getX(), e.getY(), 0);
		e.render(world, this);
		RenderEngine.popMatrix();
	}
	
	@Override
	public void tick(World world)
	{
		addNewEntities();
		tickEntities();
		cleanEntityCorps();
	}

	public void addEntity(Entity e)
	{
		newEntities.add(e);
	}

	protected void addNewEntities()
	{
		for(Entity e : newEntities)
		{
			addEntityToList(e);
		}

		newEntities.clear();
	}

	protected void addEntityToList(Entity e)
	{
		entities.add(e);
		
		onEntityAdded(e);
	}
	
	protected void onEntityAdded(Entity e)
	{
		if(e instanceof IOElement)
		{
			world.addIOElement((IOElement) e);
		}
	}

	protected void tickEntities()
	{
		for(Entity e : entities)
		{
			e.update(world, this);
		}
	}

	protected void cleanEntityCorps()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i).isAlive() == false)
			{
				removeEntityFromList(entities.get(i));
				i--;
			}
		}
	}

	protected void removeEntityFromList(Entity e)
	{
		entities.remove(e);
		
		onEntityRemoved(e);
	}
	
	protected void onEntityRemoved(Entity e)
	{
		if(e instanceof IOElement)
		{
			world.removeIOElement((IOElement) e);
		}
	}
}
