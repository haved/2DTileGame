package me.game.core;

import me.engine.core.GameType;
import me.engine.world.entity.PlayerEntity;
import me.game.world.entity.MyPlayerEntity;

public class MyGameType extends GameType
{
	@Override
	public Class<? extends PlayerEntity> getPlayerEntity()
	{
		return MyPlayerEntity.class;
	}
}
