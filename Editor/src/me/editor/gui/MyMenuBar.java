package me.editor.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import me.editor.core.IONames;

public class MyMenuBar extends JMenuBar
{
	private static final long serialVersionUID = 8202482736475155984L;
	
	private JMenu[] menues;
	
	public MyMenuBar(ActionListener listener)
	{
		menues = new JMenu[3];
		
		menues[0] = new JMenu("File");
		menues[1] = new JMenu("Edit");
		menues[2] = new JMenu("Windows");
		
		menues[0].add(makeMenuItem("New", IONames.FILE_NEW, listener));
		menues[0].add(makeMenuItem("Open", IONames.FILE_OPEN, listener));
		menues[0].add(makeMenuItem("Save", IONames.FILE_SAVE, listener));
		menues[0].addSeparator();
		menues[0].add(makeMenuItem("Import", IONames.FILE_IMPORT, listener));
		menues[0].add(makeMenuItem("Export", IONames.FILE_EXPORT, listener));
		menues[0].addSeparator();
		menues[0].add(makeMenuItem("Exit", IONames.FILE_EXIT, listener));
		
		add(menues[0]);
		add(menues[1]);
		add(menues[2]);
	}
	
	private static JMenuItem makeMenuItem(String text, String name, ActionListener listener)
	{
		JMenuItem out = new JMenuItem(text);
		out.setName(name);
		out.addActionListener(listener);
		return out;
	}
}
