package me.editor.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import me.editor.core.MainFrame;
import me.editor.world.layer.LayerTypes;

public class NewLayerDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = -3120022584831452212L;
	
	private MainFrame frame;
	
	private JComboBox<String> layers;
	private JButton ok;
	private JButton cancel;
	
	public NewLayerDialog(MainFrame frame)
	{
		super(frame);
		this.frame = frame;
		
		makeComponents();
		pack();
		setResizable(false);
		
		setLocationRelativeTo(frame);
	}
	
	private void makeComponents()
	{
		setLayout(new BorderLayout());
		
		layers = new JComboBox<String>(getLayerNames());
		layers.setBorder(new TitledBorder("Layer Type"));
		
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		add(layers, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(cancel, BorderLayout.WEST);
		buttonPanel.add(ok, BorderLayout.EAST);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private String[] getLayerNames()
	{
		String[] out = new String[LayerTypes.values().length];
		
		for(int i = 0; i < out.length; i++)
		{
			out[i] = LayerTypes.values()[i].getName();
		}
		
		return out;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == ok && frame.hasWorld())
		{
			frame.getWorld().layers.add(LayerTypes.values()[layers.getSelectedIndex()].newInstance());
			frame.updateGUI();
			setVisible(false);
		}
		else if(e.getSource() == cancel)
		{
			setVisible(false);
		}
	}
}
