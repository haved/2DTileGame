package me.engine.screen;

import me.engine.core.MainCanvas;

public abstract class GuiScreen
{
	public MainCanvas canvas;
	
	public void onFocus()
	{
		
	}
	
	public void offFocus()
	{
		
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}

	public boolean hasLoadingScreen()
	{
		return false;
	}
	
	public LoadingScreen getLoadingScreen()
	{
		return null;
	}
}
