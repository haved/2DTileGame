package me.engine.asset;

import java.io.FileInputStream;

public interface AssetReader
{
	public Object read(AssetPack pack, AssetInfo info, FileInputStream in);

	public void unload(AssetPack pack, AssetInfo info);
}
