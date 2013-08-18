package me.engine.screen;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import me.depricated.asset.PackLoader;
import me.engine.core.MainCanvas;
import me.engine.effect.FlipTransistion;
import me.engine.lib.LogHelper;
import me.engine.render.RenderEngine;
import me.engine.render.TextEngine;
import me.engine.world.World;
import me.engine.world.WorldInit;
import me.engine.world.WorldUniverse;
import me.engine.world.entity.PlayerEntity;

public class WorldLoaderScreen extends GuiScreen
{
	protected PackLoader packs;
	protected String world;
	protected String point;
	protected Class<? extends PlayerEntity> player;
	
	private FlipTransistion flip;
	
	protected WorldInit init;
	
	protected TrueTypeFont font;
	
	public WorldLoaderScreen(PackLoader packs, String world, String point, Class<? extends PlayerEntity> player)
	{
		this.packs = packs;
		this.world = world;
		this.point = point;
		this.player = player;
		
		flip = new FlipTransistion(500, true);
		
		font = TextEngine.loadFont(new Font("Arial", Font.PLAIN, 30), true);
	}
	
	@Override
	public void update()
	{
		flip.tick();
		if(flip.isDone() == false) return;
		
		if(packs.tryWork()) return;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))
		{
			openWorld();
		}
	}
	
	@Override
	public void render()
	{
		RenderEngine.setColorF(0.5f, 0.7f, 0.9f, 1);
		RenderEngine.drawRectangle(0, 0, MainCanvas.resX, MainCanvas.resY);
		
		RenderEngine.setColorF(0.4f, 0.4f, 0.4f, 1);
		RenderEngine.drawRectangle(90, MainCanvas.resY - 150, MainCanvas.resX - 180, 60);
		
		RenderEngine.setColorF(0.5f, 0.5f, 0.5f, 1);
		RenderEngine.drawRectangle(100, MainCanvas.resY - 140, packs.getScaledProgress(MainCanvas.resX - 200), 40);
		
		if(packs.hasWork() == false)
		{
			TextEngine.setCustomFont(font);
			TextEngine.drawCustomFontText(MainCanvas.resX - 340, MainCanvas.resY - 40,
					"Press Enter to continue", Color.black);
		}
		
		flip.render();
	}
	
	protected void openWorld()
	{
		try
		{
			PlayerEntity player = this.player.newInstance();
			World world = WorldUniverse.getWorld(this.world);
			
			if(world == null)
			{
				throw new RuntimeException("The world '" + this.world + "' was not found!");
			}
			
			if(init != null)
			{
				init.initWorld(player, world);
			}
			
			player.moveToWorld(world, point);
			
			canvas.setGuiScreen(new WorldScreen(player));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LogHelper.l.warning("Could not make the world to be played");
		}
	}

	public void setWorldInit(WorldInit init)
	{
		this.init = init;
	}

	public WorldInit getWorldInit()
	{
		return init;
	}
}
