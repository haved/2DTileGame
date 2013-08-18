package me.engine.core;

import org.newdawn.slick.Color;

import me.engine.screen.GuiScreen;
import me.engine.asset.AssetLib;
import me.engine.lib.DialogHelper;
import me.engine.lib.InitializationInfo;
import me.engine.lib.LogHelper;
import me.engine.render.RenderEngine;
import me.engine.render.RenderHelper;
import me.engine.render.TextEngine;

public class MainCanvas
{
	public static final boolean SHOW_FPS = true;
	private int average;
	private int amount;
	private int frames;
	private int time;
	
	public static int resX, resY;
	
	private GuiScreen newGuiScreen;
	private GuiScreen screen;
	
	public MainCanvas(InitializationInfo info)
	{
		resX = info.resolutionX;
		resY = info.resolutionY;
		
		RenderHelper.initGL(info.resolutionX, info.resolutionY);
		RenderEngine.init();
		TextEngine.init();
		
		AssetLib.load(info.content);
		
		trySetGuiScreen(info.defaultGuiScreen);
	}
	
	public void trySetGuiScreen(String className)
	{
		try
		{
			Class<?> clazz = Class.forName(className);
			
			Object obj = clazz.newInstance();
			
			if(obj instanceof GuiScreen)
			{
				changeToGuiScreen((GuiScreen) obj);
			}
			else
			{
				LogHelper.l.severe("Class '" + clazz.getName() + "' is not a subclass of GuiScreen");
				DialogHelper.showErrorMessage("Class '" + clazz.getName() + "' is not a subclass of GuiScreen");
			}
		}
		catch(Exception e)
		{
			LogHelper.l.severe("Could not initialize guiScreen " + className);
			DialogHelper.showException(e, "Could not initialize guiScreen " + className);
		}
	}
	
	public void setGuiScreen(GuiScreen newScreen)
	{
		this.newGuiScreen = newScreen;
	}
	
	public void update()
	{
		if(newGuiScreen != null)
		{
			changeToGuiScreen(newGuiScreen);
			newGuiScreen = null;
		}
		
		if(screen != null)
		{
			screen.update();
		}
	}
	
	private void changeToGuiScreen(GuiScreen newScreen)
	{
		if(screen != null)
		{
			screen.offFocus();
		}
		
		screen = newScreen;
		screen.canvas = this;
		
		if(screen != null)
		{
			screen.onFocus();
		}
		else
		{
			LogHelper.l.severe("New GuiScreen is null!");
		}
	}
	
	public void render()
	{
		RenderHelper.clear();
		
		RenderEngine.pushMatrix();
		
		if(screen != null)
		{
			renderScreen();
		}
		
		if(SHOW_FPS)
		{
			renderFPS();
		}
		RenderEngine.popMatrix();
	}
	
	private void renderScreen()
	{
		if(screen.hasLoadingScreen() && screen.getLoadingScreen() != null)
		{
			screen.getLoadingScreen().render();
		}
		else
		{
			screen.render();
		}
	}

	public void renderFPS()
	{
		if(GameTime.deltaTime() == 0) return;
		
		time += GameTime.deltaTime();
		amount += 1000 / GameTime.deltaTime();
		frames++;
		
		if(time >= 1000)
		{
			average = amount / frames;
			time = 0;
			amount = 0;
			frames = 0;
		}
		
		RenderEngine.resetColor();
		TextEngine.drawText(10, 10, "FPS: " + 1000 / GameTime.deltaTime() + " (" + average + ")", Color.black);
	}
}