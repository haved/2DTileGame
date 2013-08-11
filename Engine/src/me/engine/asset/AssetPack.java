package me.engine.asset;

import java.io.File;
import java.util.ArrayList;

public class AssetPack implements Loadable
{
	private String name;
	
	private ArrayList<File> files = new ArrayList<File>();
	private ArrayList<AssetInfo> loaded = new ArrayList<AssetInfo>();
	private int totalFiles;
	
	public AssetPack(String name)
	{
		setName(name);
	}
	
	public AssetPack(String name, File f)
	{
		System.out.println(f.getAbsolutePath());
		setName(name);
		
		if(f.isDirectory())
		{
			addFilesInDirectory(f);
		}
		else if(f.isFile())
		{
			addFile(f);
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public int getTotalAssets()
	{
		return totalFiles;
	}
	
	public void addFilesInDirectory(File dir)
	{
		if(dir != null)
		{
			addFiles(dir.listFiles());
		}
	}
	
	public void addFiles(File[] files)
	{
		for(File file:files)
		{
			addFile(file);
		}
	}
	
	public void addFile(File file)
	{
		totalFiles++;
		files.add(file);
	}
	
	public void unload()
	{
		for(AssetInfo info:loaded)
		{
			if(info != null)
			{
				info.fileType.unload(this, info.fileName);
			}
		}
	}
	
	@Override
	public int getScaledProgress(int max)
	{
		return max * loaded.size() / totalFiles;
	}

	@Override
	public boolean hasWork()
	{
		return files.size() > 0;
	}

	@Override
	public void loadElement()
	{
		File f = files.get(0);
		if(f == null) return;
		
		AssetInfo i = AssetLoader.readAsset(this, f);
		
		if(files.size() > 0)
		{
			loaded.add(i);
			files.remove(0);
		}
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
