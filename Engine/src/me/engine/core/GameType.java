package me.engine.core;

import me.engine.world.entity.PlayerEntity;

public abstract class GameType
{
	public abstract Class<? extends PlayerEntity> getPlayerEntity();
}
