/**@author HKrogstie*/
package me.editor.world.layer;

import java.awt.Graphics2D;

import me.editor.core.Camera;

public abstract class Layer
{
	public void render(Graphics2D g, Camera cam)
	{
		
	}
	
	public abstract String getName();
	
	public String toString()
	{
		return getName();
	}
}
