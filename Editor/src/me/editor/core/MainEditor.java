package me.editor.core;

public class MainEditor
{
	public static MainEditor instance;
	
	public MainFrame frame;
	
	public MainEditor()
	{
		frame = new MainFrame("MyEngine Editor");
	}
	
	public void show()
	{
		frame.showFrame();
	}
	
	public static void main(String[] args)
	{
		instance = new MainEditor();
		instance.show();
	}
}