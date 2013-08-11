package me.engine.lib;

import static javax.swing.JOptionPane.*;

public final class DialogHelper
{
	public static void showException(Exception e, String problem)
	{
		showErrorMessage(problem + "\n" + e.getMessage(), e.getClass().getName());
	}
	
	public static void showErrorMessage(String text, String title)
	{
		showMessageDialog(null, text, title, ERROR_MESSAGE);
	}
	
	public static void showErrorMessage(String text)
	{
		showErrorMessage(text, "Error");
	}
	
	public static void showWarningMessage(String text, String title)
	{
		showMessageDialog(null, text, title, WARNING_MESSAGE);
	}
	
	public static void showWarningMessage(String text)
	{
		showWarningMessage(text, "Error");
	}
}
