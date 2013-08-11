package me.engine.effect;

import org.newdawn.slick.Color;

public class ColorFadeEffect implements FadeEffect
{
	private Color startColor;
	private Color endColor;
	private Color current;
	private int ticksTotal;
	private int ticksDone;
	
	public ColorFadeEffect(Color startColor, Color endColor, int ticks)
	{
		this.startColor = startColor;
		this.endColor = endColor;
		this.ticksTotal = ticks;
		
		this.current = this.startColor;
	}
	
	public Color getColor()
	{
		return current;
	}
	
	public void tick()
	{
		if(isFading() == false) return;
		
		int[] colors = new int[4];
		
		colors[0] = (int)(endColor.r - startColor.r) * 1000;
		colors[1] = (int)(endColor.g - startColor.g) * 1000;
		colors[2] = (int)(endColor.b - startColor.b) * 1000;
		colors[3] = (int)(endColor.a - startColor.a) * 1000;
		
		float fade = ticksDone / ticksTotal;
		
		for(int i = 0; i < 4; i++)
		{
			colors[i] *= fade;
		}
		
		current = new Color(startColor.r + colors[0] / 1000f, startColor.g + colors[1] / 1000f,
							startColor.b + colors[2] / 1000f, startColor.a + colors[3] / 1000f);
		
		ticksDone++;
	}
	
	public boolean isFading()
	{
		return ticksDone <= ticksTotal;
	}
}