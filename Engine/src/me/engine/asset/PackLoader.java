package me.engine.asset;

import java.util.ArrayList;

public class PackLoader implements Loadable
{
	private ArrayList<AssetPack> packs;
	private ArrayList<AssetPack> donePacks;
	private int totalAssets;
	private int loadedAssets;
	
	public PackLoader()
	{
		packs = new ArrayList<AssetPack>();
		donePacks = new ArrayList<AssetPack>();
	}
	
	public void addAssetPack(AssetPack pack)
	{
		packs.add(pack);
		totalAssets += pack.getTotalAssets();
	}
	
	public void unload()
	{
		for(AssetPack pack:donePacks)
		{
			pack.unload();
		}
	}
	
	@Override
	public int getScaledProgress(int max)
	{
		if(totalAssets == 0) return 0;
		return max * loadedAssets / totalAssets;
	}

	@Override
	public boolean hasWork()
	{
		return packs.size() > 0;
	}

	@Override
	public void loadElement()
	{
		AssetPack pack = packs.get(0);
		
		if(pack != null)
		{
			if(pack.hasWork())
			{
				pack.loadElement();
			}
			
			if(pack.hasWork() == false)
			{
				packs.remove(0);
				donePacks.add(pack);
			}
		}
		
		loadedAssets++;
	}

	@Override
	public boolean tryWork()
	{
		if(hasWork())
		{
			loadElement();
		}
		
		return hasWork();
	}
}
