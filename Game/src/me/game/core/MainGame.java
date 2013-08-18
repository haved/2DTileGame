package me.game.core;

import me.engine.core.MainEngine;
import me.engine.lib.InitializationInfo;

public class MainGame
{
	public static void main(String[] args)
	{
		InitializationInfo info = new InitializationInfo("me.game.screen.MainMenuScreen");
		
		info.content = "bin/Content";
		
		info.resolutionX = 1024;
		info.resolutionY = 640;
		
		info.windowWidth = 1024;
		info.windowHeight = 640;
		
		info.fps = 0;
		info.vsync = false;
		
		MainEngine.initEngine(info);
	}
}