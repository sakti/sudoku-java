package sudoku;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Sudoku {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}
				
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				final SudokuFrame frame = new SudokuFrame();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.pack();
				frame.addWindowListener(new
						WindowAdapter(){
					public void windowClosing(WindowEvent e){
						if(JOptionPane.showConfirmDialog(frame, "Anda yakin ingin keluar?","Konfirmasi",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
							System.out.println("Yaaa");
							System.out.println("keluar");
							System.exit(0);
						}						
					}
				});
				frame.setVisible(true);
				
				try {
					frame.setIconImage(ImageIO.read(getClass().getResource("/icon/icon.jpg")));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("HOME DIRECTORY "+System.getProperty("user.home"));
				//JOptionPane.showMessageDialog(null, "HOME DIRECTORY "+System.getProperty("user.home")+System.getProperty("file.separator")+"high.dat");
			}
		});
	}

}
