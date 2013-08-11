package me.engine.effect;

import org.newdawn.slick.Color;

public interface FadeEffect
{
	public void tick();
	
	public boolean isFading();
	
	public Color getColor();
}
