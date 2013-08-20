package me.engine.screen;

import me.engine.core.GameType;
import me.engine.render.RenderEngine;
import me.engine.world.WorldLib;
import me.engine.world.WorldPack;

public class WorldLoaderScreen extends GuiScreen
{
	private String packName;
	private String room;
	private Class<? extends GameType> gameType;
	private String point;
	
	private int progress;
	
	public WorldLoaderScreen(String packName, String room, Class<? extends GameType> gameType, String point)
	{
		this.packName = packName;
		this.room = room;
		this.gameType = gameType;
		this.point = point;
	}
	
	public void tick()
	{
		if(progress == 1)
		{
			loadWorld();
			progress = 2;
		}
	}
	
	public void loadWorld()
	{
		WorldLib.changeWorldPack(packName);
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
