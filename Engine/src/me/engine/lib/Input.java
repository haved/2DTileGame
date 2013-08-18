package me.engine.lib;

import me.engine.core.Window;
import me.engine.math.Vector2i;

import org.lwjgl.input.Mouse;

public final class Input
{
	public static boolean isLeftMouseDown()
	{
		return Mouse.isButtonDown(0);
	}
	
	public static boolean isRightMouseDown()
	{
		return Mouse.isButtonDown(1);
	}
	
	public static Vector2i getMousePosition()
	{
		return new Vector2i(Mouse.getX(), Window.getHeight() - Mouse.getY());
	}
}
