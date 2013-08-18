package me.engine.effect;

import me.engine.core.MainCanvas;
import me.engine.lib.GameTime;
import me.engine.render.RenderEngine;

public class FlipTransistion
{
	private boolean startBlack;
	private int progression;
	private int totalTime;
	
	public FlipTransistion(int totalTime, boolean startBlack)
	{
		this.totalTime = totalTime;
		this.startBlack = startBlack;
	}
	
	public void render()
	{
		RenderEngine.pushMatrix();
		
		if(startBlack) RenderEngine.translateF(MainCanvas.resX, MainCanvas.resY, 0);
		else 	RenderEngine.translateF(0, MainCanvas.resY, 0);
		
		RenderEngine.rotateF(getRot(), 0, 0, 1);
		RenderEngine.setColorF(0, 0, 0, 1);
		
		if(startBlack) RenderEngine.drawRectangle(0, -MainCanvas.resX, MainCanvas.resY, MainCanvas.resX);
		else RenderEngine.drawRectangle(0, -MainCanvas.resY, MainCanvas.resX, MainCanvas.resY);
		RenderEngine.popMatrix();
	}
	
	public void tick()
	{
		if(isDone() == false)
		{
			progression += GameTime.deltaTime();
		}
	}
	
	public boolean isDone()
	{
		return progression >= totalTime;
	}
	
	public float getRot()
	{
		float rot = 0f;
		
		return rot;
	}
}
