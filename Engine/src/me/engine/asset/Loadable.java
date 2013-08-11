package me.engine.asset;

public interface Loadable
{
	public boolean hasWork();
	
	public void loadElement();
	
	public boolean tryWork();
	
	public int getScaledProgress(int max);
}
