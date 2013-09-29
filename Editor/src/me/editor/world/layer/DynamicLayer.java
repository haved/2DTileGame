/**@author HKrogstie*/
package me.editor.world.layer;

import java.util.ArrayList;

import me.editor.world.entity.Entity;

public class DynamicLayer extends Layer implements IEntityLayer
{
	@Override
	public String getName()
	{
		return "DynamicLayer";
	}

	@Override
	public ArrayList<Entity> getEntityList()
	{
		return null;
	}
}
