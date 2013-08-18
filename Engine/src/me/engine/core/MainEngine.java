package me.engine.core;

import me.engine.lib.GameTime;
import me.engine.lib.InitializationInfo;
import me.engine.lib.LogHelper;
import me.engine.lib.Util;

public class MainEngine
{
	public static MainEngine instance;
	
	public MainCanvas canvas;
	private boolean running;
	
	public MainEngine()
	{
		
	}
	
	public void init(InitializationInfo info)
	{
		Window.setInfo(info);
		Window.create(info.windowWidth, info.windowHeight);
		Window.setFps(info.fps);
		
		canvas = new MainCanvas(info);
	}
	
	public void start()
	{
		if(running != true)
		{
			running = true;
		}
		
		gameLoop();
	}
	
	public void stop()
	{
		if(running == true)
		{
			running = false;
		}
	}
	
	public void gameLoop()
	{
		while(running)
		{
			GameTime.start();
			
			if(Window.isCloseRequested()) stop();
			
			canvas.update();
			
			canvas.render();
			
			Window.update();
			Window.sync();
			
			GameTime.finish();
		}
		
		dispose();
	}
	
	private void dispose()
	{
		Window.dispose();
	}
	
	
	public static void initEngine(InitializationInfo info)
	{
		if(info == null)
		{
			LogHelper.l.severe("Did not get an InitializationInfo");
			Util.forceStop();
		}
		
		instance = new MainEngine();
		instance.init(info);
		instance.start();
		
		LogHelper.l.info("Natrual Shutdown");
	}
}
