package form;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;

import javax.swing.*;

public class FormAbout extends JDialog {
	public FormAbout(Frame owner,boolean modal){
		super(owner,modal);
		this.setSize(500, 400);
		this.setTitle("Tentang");
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);			
		JLabel gambar=new JLabel();
		gambar.setIcon(new ImageIcon(getClass().getResource("/icon/icon.jpg")));
		add(gambar,BorderLayout.NORTH);
		add(new JLabel("<html><center>" +
				"<h1>Sudoku 1.0</h1> Created by :<br/>" +
				"Sofian Wiranandi <br/>(613080003)<br/>" +
				"Sakti Dwi Cahyono <br/>(613081029)<br/>" +
				"Arif Setyo Wibowo <br/>(613081032)<br/>" +
				"<hr/>Copyright © 2010<br/>Published Under GPL License" +
				"</center></html>"));
		JButton exit=new JButton("Keluar");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FormAbout.this.setVisible(false);
			}
		});
		add(exit,BorderLayout.SOUTH);
		this.pack();
	}
}
