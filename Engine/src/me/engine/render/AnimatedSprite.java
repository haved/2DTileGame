package me.engine.render;

import me.engine.math.RectangleI;

public class AnimatedSprite
{
	public String location;
	public RectangleI[] texturePos;
	
	public AnimatedSprite(String location, RectangleI[] texturePos)
	{
		this.location = location;
		this.texturePos = texturePos;
	}
	
	public AnimatedSprite(String location)
	{
		this.location = location;
	}
	
	public void render(int i, RectangleI pos)
	{
		if(location != null && pos != null)
		{
			RenderEngine.bind(location);
			RenderEngine.drawTexture(pos, texturePos[i]);
		}
	}
	
	public void render(int i, int x, int y, int width, int height)
	{
		render(i, new RectangleI(x, y, width, height));
	}
}
