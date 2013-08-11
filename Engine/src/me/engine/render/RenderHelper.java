package me.engine.render;

import static org.lwjgl.opengl.GL11.*;

public final class RenderHelper
{
	public static void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public static void initGL(int width, int height)
	{
		glShadeModel(GL_SMOOTH);        
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);                    

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
		glClearDepth(1);                                        
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glViewport(0,0,width,height);
		glMatrixMode(GL_MODELVIEW);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
}
	
