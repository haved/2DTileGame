package me.engine.physics;

import me.engine.lib.Util;
import me.engine.math.Vector2i;
import me.engine.render.RenderEngine;

public class TileMap
{
	private int tileWidth;
	private int tileHeight;

	private int mapWidth;
	private int mapHeight;

	private boolean[] map;

	public TileMap(Vector2i tileSize, int width, int height)
	{
		this.tileWidth = tileSize.getX();
		this.tileHeight = tileSize.getY();
		
		this.mapWidth = width;
		this.mapHeight = height;
		
		setMap(new boolean[width * height]);
	}
	
	public void randomTiles(int count)
	{
		for(int i = 0; i < count; i++)
		{
			map[Util.randomInt(map.length)] = true;
		}
	}
	
	public void makeWalls()
	{
		for(int i = 0; i < getMapWidth(); i++)
		{
			setValue(true, i, 0);
			setValue(true, i, getMapHeight() - 1);
		}
		
		for(int i = 0; i < getMapHeight(); i++)
		{
			setValue(true, 0, i);
			setValue(true, getMapWidth() - 1, i);
		}
	}
	
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
	}

	public int getTileWidth()
	{
		return tileWidth;
	}

	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
	}

	public int getTileHeight()
	{
		return tileHeight;
	}

	public void setMapWidth(int mapWidth)
	{
		this.mapWidth = mapWidth;
	}

	public int getMapWidth()
	{
		return mapWidth;
	}
	
	public void setMapHeight(int mapHeight)
	{
		this.mapHeight = mapHeight;
	}

	public int getMapHeight()
	{
		return mapHeight;
	}

	public void setMap(boolean[] map)
	{
		this.map = map;
	}

	public boolean[] getMap()
	{
		return map;
	}

	public void setValue(boolean val, int x, int y)
	{
		if(realTile(x, y) == false)
		{
			return;
		}
		
		map[x + y * mapWidth] = val;
	}

	public boolean getValue(int x, int y)
	{
		if(realTile(x, y) == false)
		{
			return false;
		}
		
		return map[x + y * mapWidth];
	}

	protected boolean realTile(int x, int y)
	{
		return x >= 0 & y >= 0 & x < mapWidth & y < mapHeight;
	}

	public void render()
	{
		RenderEngine.setColorF(0, 0.9f, 0.1f, 1);
		
		for(int x = 0; x < mapWidth; x++)
		{
			for(int y = 0; y < mapHeight; y++)
			{
				if(getValue(x, y))
				{
					RenderEngine.drawRectangle(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
				}
			}
		}
	}

	public boolean tileDown(CollisionBox box)
	{
		int top = box.getX() / tileWidth;
		int bottom = (box.getX2() -1) / tileWidth;
		
		int move = (box.getY2()) / tileHeight;
		
		for(int i = top; i <= bottom; i++)
		{
			if(getValue(i, move))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean tileUp(CollisionBox box)
	{
		int top = box.getX() / tileWidth;
		int bottom = (box.getX2() -1) / tileWidth;
		
		int move = (box.getY() - 1) / tileHeight;
		
		for(int i = top; i <= bottom; i++)
		{
			if(getValue(i, move))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean tileRight(CollisionBox box)
	{
		int top = box.getY() / tileHeight;
		int bottom = (box.getY2() -1) / tileHeight;
		
		int move = (box.getX2()) / tileWidth;
		
		for(int i = top; i <= bottom; i++)
		{
			if(getValue(move, i))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean tileLeft(CollisionBox box)
	{
		int top = box.getY() / tileHeight;
		int bottom = (box.getY2() -1) / tileHeight;
		
		int move = (box.getX() - 1) / tileWidth;
		
		for(int i = top; i <= bottom; i++)
		{
			if(getValue(move, i))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean tileDown(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return tileDown((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
	
	public boolean tileUp(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return tileUp((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
	
	public boolean tileLeft(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return tileLeft((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
	
	public boolean tileRight(CollisionShape shape)
	{
		if(shape instanceof CollisionBox)
		{
			return tileRight((CollisionBox) shape);
		}
		else
		{
			return false;
		}
	}
}