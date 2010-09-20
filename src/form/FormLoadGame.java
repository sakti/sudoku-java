package form;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import sudoku.Player;

import database.*;

public class FormLoadGame extends JDialog {
	public DatabaseFile database;
	public int pilihan;
	private JPanel panel;
	
	public FormLoadGame(Frame owner,boolean modal){
		super(owner,modal);
		this.setSize(400, 250);
		this.setTitle("Load saved game");
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
		JScrollPane scroll=new JScrollPane();
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scroll.setViewportView(panel);
		add(scroll);
		
	}
	public void updateListGame(){
		int i=0;
		panel.removeAll();
		ArrayList<Player> temps=database.getListGame();
		for(Player temp:temps){
			JButton btnPilih=new JButton("    Pilih    ");
			btnPilih.setName(""+i);
			btnPilih.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton temp=(JButton)e.getSource();
					System.out.println("Pilih ini => "+temp.getName());
					pilihan=Integer.parseInt(temp.getName());
					FormLoadGame.this.setVisible(false);
				}
				
			});

			JButton btnHapus=new JButton("  Hapus  ");
			btnHapus.setName(""+i);
			btnHapus.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton temp=(JButton)e.getSource();
					System.out.println("hapus ini => "+temp.getName());
					if(JOptionPane.showConfirmDialog(FormLoadGame.this, "Anda yakin menghapus data game ini?","Konfirmasi",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
						System.out.println("Yaaa");
						database.removeGame(Integer.parseInt(temp.getName()));
						database.saveGames();
						//panel.removeAll();
						FormLoadGame.this.updateListGame();
					}else{
						System.out.println("tidak");
					}
				}
				
			});		
			
			JPanel pnlGame=new JPanel(new GridBagLayout());
			GridBagConstraints aturan=new GridBagConstraints();
			JLabel text=new JLabel();
			text.setText("<html><h2>"+temp.getName()+"</h2><p>Level : "+temp.getPuzzle().getLevel()+"<br/>Score sementara : "+temp.getScore()+"<br/>Durasi : "+temp.getPuzzle().getDuration()+"<br/>Tgl main: "+temp.getPuzzle().getStartTime()+"</p><html>");
			aturan.gridx=0;
			aturan.gridy=0;
			aturan.gridheight=2;
			aturan.gridwidth=2;
			aturan.insets=new Insets(10,10,10,10);			
			pnlGame.add(text,aturan);
			
			aturan=new GridBagConstraints();
			aturan.gridx=2;
			aturan.gridy=0;
			aturan.gridwidth=2;
			aturan.anchor=GridBagConstraints.NORTH;
			pnlGame.add(btnPilih,aturan);
			
			aturan=new GridBagConstraints();
			aturan.gridx=2;
			aturan.gridy=1;
			aturan.gridwidth=2;
			aturan.anchor=GridBagConstraints.NORTH;
			pnlGame.add(btnHapus,aturan);
			
			pnlGame.setBorder(BorderFactory.createEtchedBorder());
			panel.add(pnlGame);
			i++;
		}	
		if(i==0){
			panel.add(new JLabel("Data game tersimpan tidak ada"));
		}
		this.repaint();
		this.validate();
	}
	
}
