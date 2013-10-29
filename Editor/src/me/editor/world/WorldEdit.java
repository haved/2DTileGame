/**@author HKrogstie*/
package me.editor.world;

import me.editor.core.MainFrame;
import me.editor.world.layer.Layer;

public class WorldEdit
{
	private MainFrame frame;
	
	public int selectedLayerIndex;
	
	public WorldEdit(MainFrame frame)
	{
		this.frame = frame;
	}
	
	public Layer getSelectedLayer()
	{
		if(frame.getWorld() != null && frame.getWorld().layers != null)
		{
			return frame.getWorld().layers.get(selectedLayerIndex);
		}
		
		return null; 
	}
}
