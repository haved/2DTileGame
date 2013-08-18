package me.engine.screen;

import me.engine.core.GameType;
import me.engine.render.RenderEngine;
import me.engine.world.WorldPack;

public class WorldLoaderScreen extends GuiScreen
{
	private WorldPack pack;
	private String room;
	private Class<? extends GameType> gameType;
	private String point;
	
	private int progress;
	
	public WorldLoaderScreen(WorldPack pack, String room, Class<? extends GameType> gameType, String point)
	{
		this.pack = pack;
		this.room = room;
		this.gameType = gameType;
		this.point = point;
	}
	
	public void tick()
	{
		if(progress == 1)
		{
			progress = 2;
		}
	}
	
	public void render()
	{
		if(progress == 0)
		{
			progress = 1;
		}
		
		RenderEngine.setColorF(0.5f, 0.5f, 1f, 1f);
		RenderEngine.drawRectangle(0, 0, 1024, 640);
		
		if(progress == 1)
		{
			
		}
		else
		{
			
		}
	}
}
