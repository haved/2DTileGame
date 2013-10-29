package me.editor.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public final class Util
{
	public static Graphics2D cloneGraphics(Graphics g)
	{
		return (Graphics2D) g.create();
	}
	
	public static void resetTransform(Graphics2D g)
	{
		g.setTransform(new AffineTransform(1, 0, 0, 1, 0, 0));
	}
}
