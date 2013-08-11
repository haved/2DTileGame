package me.engine.render;

import me.engine.math.RectangleI;

public class Sprite
{
	public String location;
	public RectangleI pos;
	
	public Sprite(String location, RectangleI pos)
	{
		this.location = location;
		this.pos = pos;
	}
	
	public void render(RectangleI pos)
	{
		if(location != null && pos != null)
		{
			RenderEngine.bind(location);
			RenderEngine.drawTexture(pos, this.pos);
		}
	}
	
	public void render(int x, int y, int width, int height)
	{
		render(new RectangleI(x, y, width, height));
	}
}
