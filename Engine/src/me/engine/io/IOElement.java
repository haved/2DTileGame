package me.engine.io;

public interface IOElement
{
	public String getID();
	
	public void onEvent(String event);
}
