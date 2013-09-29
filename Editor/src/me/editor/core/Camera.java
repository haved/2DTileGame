package me.editor.core;

public class Camera
{
	public int scrollX = 0;
	public int scrollY = 0;
	public float scale = 1.0f;
	
	public void dragPan(int xMove, int yMove)
	{
		pan((int)(xMove * (1/scale)), (int)(yMove * (1/scale)));
	}
	
	public void pan(int xMove, int yMove)
	{
		scrollX += xMove;
		scrollY += yMove;
	}
}
