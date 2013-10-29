package me.editor.gui;

public interface ListController
{
	public int getSelectedElement();
	
	public Object[] getElements();
	
	public void onSelectionChange(int newIndex);
}
