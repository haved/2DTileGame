package me.engine.render;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import static org.lwjgl.opengl.GL11.*;

public final class TextEngine
{
	private static TrueTypeFont defaultFont;
	private static TrueTypeFont customFont;
	
	public static void init()
	{
		initDefaultFont();
	}
	
	private static void initDefaultFont()
	{
		defaultFont = loadFont(new Font("Arial", Font.PLAIN, 20), true);
		customFont = defaultFont;
	}
	
	public static TrueTypeFont loadFont(Font font, boolean antiAlias)
	{
		return new TrueTypeFont(font, antiAlias);
	}

	public static void setCustomFont(TrueTypeFont font)
	{
		if(font != null)
		{
			TextEngine.customFont = font;
		}
	}
	
	public static void drawText(float x, float y, String text, Color color)
	{
		drawText(x, y, defaultFont, text, color);
	}
	
	public static void drawCustomFontText(float x, float y, String text, Color color)
	{
		drawText(x, y, customFont, text, color);
	}
	
	private static void drawText(float x, float y, TrueTypeFont font, String text, Color color)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		font.drawString(x, y, text, color);
		glDisable(GL_BLEND);
	}

	public static int getTextWidth(String text)
	{
		return defaultFont.getWidth(text);
	}
	
	public static int getTextHeight(String text)
	{
		return defaultFont.getHeight(text);
	}
	
	public static int getCustomFontWidth(String text)
	{
		return customFont.getWidth(text);
	}
	
	public static int getCustomFontHeight(String text)
	{
		return customFont.getHeight(text);
	}
}
