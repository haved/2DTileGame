package me.engine.guiobject;

import org.newdawn.slick.Color;

import me.engine.lib.Input;
import me.engine.math.RectangleI;
import me.engine.render.RenderEngine;
import me.engine.render.TextEngine;

public class GuiObjectButton extends GuiObject
{
	private String text;
	private String texture;
	
	private ButtonListener listener;
	
	private boolean mouseOver;
	private boolean mouseDown;
	private boolean prevMouseDown;
	
	public GuiObjectButton(String text, String texture)
	{
		this.setText(text);
		this.texture = texture;
	}

	@Override
	public void update()
	{
		mouseOver = getBounds().isTouching(Input.getMousePosition());
		if(mouseOver & Input.isLeftMouseDown() & !prevMouseDown)
		{
			mouseDown = true;
		}
		
		if(Input.isLeftMouseDown() == false)
		{
			if(mouseOver & mouseDown & listener != null)
			{
				listener.onButtonPress(this);
			}
			
			mouseDown = false;
		}
		
		prevMouseDown = Input.isLeftMouseDown();
	}
	
	@Override
	public void render()
	{
		RenderEngine.bind(texture);
		RenderEngine.drawTexture(new RectangleI(0, 0, getWidth(), getHeight()),
				new RectangleI(0, getTextureCoord(), 200, 20));
		
		int textX = (getWidth() - TextEngine.getTextWidth(getText())) / 2;
		int textY = (getHeight() - TextEngine.getTextHeight(getText())) / 2;
		
		TextEngine.drawText(textX, textY, text, Color.white);
	}
	
	private int getTextureCoord()
	{
		if(mouseOver & mouseDown)
		{
			return 40;
		}
		else if(mouseOver)
		{
			return 20;
		}
		else
		{
			return 0;
		}
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
	
	public void setListener(ButtonListener listener)
	{
		this.listener = listener;
	}

	public ButtonListener getListener()
	{
		return listener;
	}
}
