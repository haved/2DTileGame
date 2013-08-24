package me.game.world;

import java.util.ArrayList;

import me.engine.lib.Util;
import me.engine.math.RectangleI;
import me.engine.math.Vector2i;
import me.engine.physics.TileMap;
import me.engine.render.Sprite;
import me.engine.world.World;
import me.engine.world.WorldInit;
import me.engine.world.layer.SimpleBackground;
import me.game.world.entity.BoxEntity;
import me.game.world.entity.GrassEntity;

public class WorldDebugger implements WorldInit
{
	public World makeWorld()
	{
		return new World(2048, 640);
	}
	
	public void initWorld(World world)
	{		
		//makeBoxes(world, 700, 300, 10, 5);
		
		BoxEntity box = new BoxEntity(400, 200, 50, 64);
		world.addEntity(box);
		
		initTileMap(world);
		
		world.addPoint("Start", new Vector2i(200, 200));
		world.addBackground(new SimpleBackground("factory;bigBG.png", 0.1f, 0.1f, 300, 600));
	}
	
	public void initTileMap(World world)
	{
		TileMap map = world.getMainLayer().getTileMap();
		map.makeWalls();
		
//		for(int i = 11; i < 13; i++)
//		{
//			map.setValue(true, i, 5);
//		}
//		
//		for(int i = 13; i < 24; i++)
//		{
//			map.setValue(true, i, 5 + (i - 13));
//		}
		
//		map.setValue(true, 26, map.getMapHeight() - 2);
	}
	
	public void makeBoxes(World world, int x, int y, int xAmount, int yAmount)
	{
		BoxEntity box;
		
		for(int j = 0; j < yAmount; j++)
		{
			for(int i = 0; i < xAmount; i++)
			{
				box = new BoxEntity(x + (i*32), y + (j*32), 32, 32);
				world.addEntity(box);
			}
		}
	}
	
	public void addGrass(World world)
	{
		Sprite s1 = new Sprite("Tutorial;Spritesheet.png", new RectangleI(0, 2 * 32, 32, 32));
		Sprite s2 = new Sprite("Tutorial;Spritesheet.png", new RectangleI(32, 2 * 32, 32, 32));
		
		for(int i = 32; i <= 1024 - 64; i += Util.randomInt(80) + 40)
		{
			world.addEntity(new GrassEntity(i, 640 - (16 + 32), 32, 32, s2));
		}
		
		for(int i = 32; i <= 1024 - 64; i += Util.randomInt(32) + 16)
		{
			world.addEntity(new GrassEntity(i, 640 - (16 + 32), 32, 32, s1));
		}
	}

	
	@Override
	public ArrayList<String> getPacks()
	{
		ArrayList<String> out = new ArrayList<String>();
		out.add("factory");
		return out;
	}
}
