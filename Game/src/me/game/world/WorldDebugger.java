package me.game.world;

import me.engine.lib.Util;
import me.engine.math.RectangleI;
import me.engine.math.Vector2i;
import me.engine.physics.TileMap;
import me.engine.render.Sprite;
import me.engine.world.World;
import me.engine.world.WorldInit;
import me.engine.world.entity.PlayerEntity;
import me.engine.world.layer.SimpleBackground;
import me.game.world.entity.BoxEntity;
import me.game.world.entity.BreakableEntity;
import me.game.world.entity.GrassEntity;

public class WorldDebugger implements WorldInit
{
	public void initWorld(PlayerEntity player, World world)
	{
		player.setID("Player");
		
		BoxEntity box;
		
		for(int j = 0; j < 5; j++)
		{
			for(int i = 0; i < 30; i++)
			{
				box = new BoxEntity(700 + (i*32), 300 + (j*32), 32, 32);
				box.setMass(2);
				box.setGravity(Util.randomFloat(2) - 1);
				world.addEntity(box);
			}
		}
		
		world.addEntity(new BoxEntity(250, 32, 128, 128, 100));
		
		world.addEntity(new BreakableEntity(50, 500, 200, 50, 10000));
		
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
		
		map.setValue(true, 26, map.getMapHeight() - 2);
		
		world.addPoint("Start", new Vector2i(200, 200));
		world.addBackground(new SimpleBackground("Tutorial;bigBG.png", 0.1f, 0.1f, 300, 600));
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
}
