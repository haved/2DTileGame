package me.engine.lib;

public class InitializationInfo
{
	public static final String CONTENT = "bin/Content";
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 640;
	public static final boolean RESIZEABLE = false;
	
	public static final int RESOLUTION_X = 800;
	public static final int RESOLUTION_Y = 640;
	
	public static final int FPS = 48;
	
	public static final String TITLE = "Game";
	
	public static final boolean VSYNC = true;
	
	
	public String content = CONTENT;
	
	public int windowWidth = WINDOW_WIDTH;
	public int windowHeight = WINDOW_HEIGHT;
	public boolean resizable = RESIZEABLE;
	
	public int resolutionX = RESOLUTION_X;
	public int resolutionY = RESOLUTION_Y;
	
	public int fps = FPS;
	
	public String title = TITLE;
	
	public boolean vsync = VSYNC;
	
	public String defaultGuiScreen;
	
	public InitializationInfo(String defaultGuiScreen)
	{
		this.defaultGuiScreen = defaultGuiScreen;
	}
}
