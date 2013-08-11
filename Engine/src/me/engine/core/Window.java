package me.engine.core;

import me.engine.lib.DialogHelper;
import me.engine.lib.InitializationInfo;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public final class Window
{
	public static int fps = 24;
	
	public static void setFps(int fps)
	{
		Window.fps = fps;
	}
	
	public static int getFps()
	{
		return fps;
	}
	
	public static void update()
	{
		Display.update();
	}
	
	public static void sync()
	{
		if(fps > 0)
		{
			Display.sync(fps);
		}
		else
		{
			Display.sync(9999);
		}
	}
	
	public static void setInfo(InitializationInfo info)
	{
		Display.setTitle(info.title);
		Display.setVSyncEnabled(info.vsync);
		Display.setResizable(info.resizable);
	}
	
	public static void create(int width, int height)
	{
		try
		{
			initDisplayWindow(width, height);
		}
		catch(Exception e)
		{
			DialogHelper.showException(e, "Unable to open window");
			System.exit(0);
		}
	}
	
	private static void initDisplayWindow(int width, int height) throws Exception
	{
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();
		Mouse.create();
		Keyboard.create();
	}
	
	public static void setSize(int width, int height)
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
		}
		catch(Exception e)
		{
			DialogHelper.showException(e, "Unable to set window size");
		}
	}
	
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static boolean isActive()
	{
		return Display.isActive();
	}
	
	public void setResizable(boolean val)
	{
		Display.setResizable(val);
	}
	
	public static int getWidth()
	{
		return Display.getWidth();
	}
	
	public static int getHeight()
	{
		return Display.getHeight();
	}
	
	public static void dispose()
	{
		Display.destroy();
		Mouse.destroy();
		Keyboard.destroy();
	}
}