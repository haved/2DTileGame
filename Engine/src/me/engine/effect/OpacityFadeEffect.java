package me.engine.effect;

import org.newdawn.slick.Color;

public class OpacityFadeEffect implements FadeEffect
{
	private Color groundColor;
	private int ticksDone;
	private int totalTicks;
	private float startAlpha;
	private float endAlpha;
	
	public OpacityFadeEffect(Color color, float startAlpha, float endAlpha, int ticks)
	{
		this.groundColor = new Color(color);
		this.startAlpha = startAlpha;
		this.endAlpha = endAlpha;
		this.totalTicks = ticks;
	}
	
	@Override
	public Color getColor()
	{
		return groundColor;
	}

	@Override
	public boolean isFading()
	{
		return ticksDone <= totalTicks;
	}

	@Override
	public void tick()
	{
		if(isFading() == false) return;
		
		float diff = endAlpha - startAlpha;
		
		diff = (diff * ticksDone) / totalTicks;
		
		groundColor.a = startAlpha + diff;
		
		ticksDone++;
	}
}
