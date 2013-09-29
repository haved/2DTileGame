package me.editor.world.layer;

import javax.swing.JOptionPane;

public enum LayerTypes
{
	SIMPLE_LAYER("SimpleLayer", SimpleLayer.class),
	DYNAMIC_LAYER("DynamicLayer", DynamicLayer.class),
	DYNAMIC_COLLISION_LAYER("DynamicCollisionLayer", DynamicCollisionLayer.class),
	TILE_LAYER("DynamicTileCollisionLayer", DynamicTileCollisionLayer.class);
	
	private String name;
	private Class<? extends Layer> clazz;
	
	LayerTypes(String name, Class<? extends Layer> clazz)
	{
		this.name = name;
		this.clazz = clazz;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Layer newInstance()
	{
		try
		{
			return clazz.newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Eror making the layer instance", "No layer created", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
}
