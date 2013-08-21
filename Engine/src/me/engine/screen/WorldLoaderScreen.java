package me.engine.screen;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import me.engine.core.GameType;
import me.engine.render.RenderEngine;
import me.engine.render.TextEngine;
import me.engine.world.World;
import me.engine.world.WorldLib;
import me.engine.world.entity.PlayerEntity;

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
	
	public void update()
	{
		if(progress == 1)
		{
			loadWorld();
			progress = 2;
		}
		else if(progress == 2)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))
			{
				openWorld();
			}
		}
	}
	
	private void loadWorld()
	{
		WorldLib.changeWorldPack(packName);
	}
	
	private void openWorld()
	{
		try
		{
			World w = WorldLib.getRoom(room);
			
			if(w != null)
			{
				PlayerEntity player = gameType.newInstance().getPlayerEntity().newInstance();
				player.moveToWorld(w, point);
				WorldScreen s = new WorldScreen(player);
				canvas.setGuiScreen(s);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
			TextEngine.drawText(1024 - 400, 640 - 100, "Please Wait", Color.black);
		}
		else
		{
			TextEngine.drawText(1024 - 400, 640 - 100, "Press Enter to continue", Color.black);
		}
	}
}
