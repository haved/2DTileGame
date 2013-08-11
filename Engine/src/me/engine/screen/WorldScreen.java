package me.engine.screen;

import me.engine.world.entity.PlayerEntity;

public class WorldScreen extends GuiScreen
{
	private PlayerEntity player;
	
	public WorldScreen(PlayerEntity player)
	{
		this.setPlayer(player);
	}

	public void setPlayer(PlayerEntity player)
	{
		this.player = player;
	}

	public PlayerEntity getPlayer()
	{
		return player;
	}

	@Override
	public void update()
	{
		player.updateWorld();
	}
	
	@Override
	public void render()
	{
		player.renderWorld();
	}
}
