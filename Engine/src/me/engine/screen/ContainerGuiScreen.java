package me.engine.screen;

import java.util.ArrayList;

import me.engine.guiobject.GuiObject;
import me.engine.render.RenderEngine;

public class ContainerGuiScreen extends GuiScreen
{
	private ArrayList<GuiObject> objects;
	
	public ContainerGuiScreen()
	{
		objects = new ArrayList<GuiObject>();
	}
	
	public void add(GuiObject object)
	{
		objects.add(object);
	}
	
	public void remove(GuiObject object)
	{
		objects.remove(object);
	}
	
	public void removeAll()
	{
		objects.clear();
	}
	
	@Override
	public void update()
	{
		updateObjects();
	}
	
	@Override
	public void render()
	{
		renderObjects();
	}
	
	public void updateObjects()
	{
		for(GuiObject object:objects)
		{
			object.update();
		}
	}
	
	public void renderObjects()
	{	
		for(GuiObject object:objects)
		{
			RenderEngine.pushMatrix();
			
			RenderEngine.translateF(object.getX(), object.getY(), 0);
			
			object.render();
			
			RenderEngine.popMatrix();
		}
	}
}
