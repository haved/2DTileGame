package me.engine.world.layer;

import me.engine.core.MainCanvas;
import me.engine.math.RectangleI;
import me.engine.render.RenderEngine;
import me.engine.world.World;

public class SimpleBackground implements Layer
{
	private String location;
	private float scrollX;
	private float scrollY;
	private int xMove;
	private int yMove;
	
	public SimpleBackground(String location, float scrollX, float scrollY)
	{
		this.location = location;
		this.scrollX = scrollX;
		this.scrollY = scrollY;
	}
	
	public SimpleBackground(String location, float scrollX, float scrollY, int xMove, int yMove)
	{
		this(location, scrollX, scrollY);
		this.xMove = xMove;
		this.yMove = yMove;
	}
	
	public void render(World world, int xScroll, int yScroll)
	{
		RenderEngine.pushMatrix();
		
		RenderEngine.bind(location);
		
		RenderEngine.drawTexture(new RectangleI(0, 0, MainCanvas.resX, MainCanvas.resY),
			new RectangleI((int)(xScroll * scrollX + xMove), (int)(yScroll * scrollY + yMove),
					MainCanvas.resX, MainCanvas.resY));
		
		RenderEngine.popMatrix();
	}
	
	@Override
	public void tick(World world)
	{

	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getLocation()
	{
		return location;
	}

	public void setScrollX(float scrollX)
	{
		this.scrollX = scrollX;
	}

	public float getScrollX()
	{
		return scrollX;
	}

	public void setScrollY(float scrollY)
	{
		this.scrollY = scrollY;
	}

	public float getScrollY()
	{
		return scrollY;
	}
}
