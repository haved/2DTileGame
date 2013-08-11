package me.game.screen;

import me.engine.asset.Loadable;
import me.engine.screen.LoadingScreen;

import static org.lwjgl.opengl.GL11.*;

public class BarLoadingScreen extends LoadingScreen
{
	protected Loadable loader;
	
	public BarLoadingScreen(Loadable loader)
	{
		this.loader = loader;
	}

	@Override
	public void render()
	{		
		int scale = loader.getScaledProgress(600);
		
		glPushMatrix();
		
		glColor3f(1, 1, 1);
		glTranslatef(100, 400, 0);
		
		glBegin(GL_QUADS);
		{
			glVertex2f(0, 0);
			glVertex2f(scale, 0);
			glVertex2f(scale, 50);
			glVertex2f(0, 50);
		}
		glEnd();
		
		glPopMatrix();
	}
}
