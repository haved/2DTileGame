package me.engine.render;

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;

import me.engine.math.RectangleI;
import me.engine.math.Vector3f;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public final class RenderEngine
{
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static int textureSize;
	
	public static void init()
	{
		//In case you ever want to init something for the RenderEngine, this method is called on startup.
	}
	
	public static void pushMatrix()
	{
		glPushMatrix();
	}
	
	public static void popMatrix()
	{
		glPopMatrix();
	}
	
	public static boolean bind(String name)
	{
		if(textures.get(name) != null)
		{
			return bind(textures.get(name));
		}
		return false;
	}
	
	public static boolean bind(Texture t)
	{
		if(t == null) return false;
		
		try
		{
			textureSize = t.getTextureWidth();
			t.bind();
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static void addTexture(Texture t, String name)
	{
		textures.put(name, t);
	}
	
	public static void releaseTexture(String name)
	{
		Texture t = textures.get(name);
		
		if(t != null)
		{
			textures.remove(name);
			t.release();
		}
	}
	
	public static void setColorF(float red, float green, float blue, float alpha)
	{
		glColor4f(red, green, blue, alpha);
	}
	
	public static void setColor(Color color)
	{
		setColorF(color.r, color.g, color.b, color.a);
	}
	
	public static void resetColor()
	{
		setColorF(1, 1, 1, 1);
	}
	
	public static void rotateF(float angle, float x, float y, float z)
	{
		glRotatef(angle, x, y, z);
	}
	
	public static void translateF(float x, float y, float z)
	{
		glTranslatef(x, y, z);
	}
	
	public static void scaleF(float x, float y, float z)
	{
		glScalef(x, y, z);
	}
	
	public static void rotateF(float angle, Vector3f vector)
	{
		glRotatef(angle, vector.getX(), vector.getY(), vector.getZ());
	}
	
	public static void translateF(Vector3f vector)
	{
		glTranslatef(vector.getX(), vector.getY(), vector.getZ());
	}
	
	public static void scaleF(Vector3f vector)
	{
		glScalef(vector.getX(), vector.getY(), vector.getZ());
	}
	
	public static void drawRectangle(int x, int y, int width, int height)
	{
		pushMatrix();
		
		translateF(x, y, 0);
		
		glBegin(GL_QUADS);
		{
			glVertex2f(0,     0);
			glVertex2f(width, 0);
			glVertex2f(width, height);
			glVertex2f(0,     height);
		}
		glEnd();
		
		popMatrix();
	}
	
	public static void drawRectangle(RectangleI rect)
	{
		drawRectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}
	
	public static void drawTransparentRectangle(int x, int y, int width, int height)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_COLOR, GL_ONE_MINUS_SRC_COLOR);
		drawRectangle(x, y, width, height);
		glDisable(GL_BLEND);
	}
	
	public static void drawTransparentRectangle(RectangleI rect)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		drawRectangle(rect);
		glDisable(GL_BLEND);
	}
	
	public static void drawTexture(RectangleI rect, RectangleI texture)
	{
		drawTexture(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(),
				texture.getX(), texture.getY(), texture.getX2(), texture.getY2());
	}
	
	public static void drawTexture(int x, int y, int width, int height, int tX, int tY, int tX2, int tY2)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		
		pushMatrix();
		
		translateF(x, y, 0);
		
		float x1 = (tX + 0.4f) / (float)textureSize;
		float y1 = (tY + 0.4f) / (float)textureSize;
		float x2 = (tX2 - 0.8f) / (float)textureSize;
		float y2 = (tY2 - 0.8f) / (float)textureSize;
		
		
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(x1, y1);
			glVertex2f(0,     0);
			glTexCoord2f(x2, y1);
			glVertex2f(width, 0);
			glTexCoord2f(x2, y2);
			glVertex2f(width, height);
			glTexCoord2f(x1, y2);
			glVertex2f(0,     height);
		}
		glEnd();
		
		popMatrix();
		
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}
}
