package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

import database.*;

import form.*;

public class SudokuFrame extends JFrame {
	public SudokuFrame(){
		setTitle("Permainan Sudoku");
		setSize(500,500);
		kotakKecil=new JPanel[9];
		tombol=new JButton[81];
		papanPermainan=new JPanel();
		new JPanel();
		
		mouseListener=new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e) {
				x=e.getX();
				y=e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {}
		};
		
		klikTombol=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton temp=(JButton)e.getSource();
				tombolDipilih=temp;
				int posKotakKecil=Integer.parseInt(temp.getName());
				//poskotakkecil/27 baris
				//poskotakkecil%9/3 kolom
				//posisi baris*3 + kolom
				posKotakKecil=(posKotakKecil%9)/3+(posKotakKecil/27)*3;
				int xkotak=kotakKecil[posKotakKecil].getX();
				int ykotak=kotakKecil[posKotakKecil].getY();
				//System.out.println("sumber :"+temp.getName());
				
				popup.show(papanPermainan,temp.getX()+ x+xkotak, temp.getY()+y+ykotak);
			}
		};
		
		klikPopup=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				player.getPuzzle().addCounter();
				System.out.println("nama -->"+e.getActionCommand());
				System.out.println("Tombol -->"+tombolDipilih.getName());
				tombolDipilih.setText(e.getActionCommand());
				tombolDipilih.setForeground(Color.BLUE);
				int index=Integer.parseInt(tombolDipilih.getName());
				int value=Integer.parseInt(e.getActionCommand());
				boolean berbeda=player.getPuzzle().setAnswer(index, value);
				if(player.getPuzzle().getConflict().size()==0){
					System.out.println("Benar");
					if(berbeda) player.calculateScore();
				}
				lblScore.setText(""+player.getScore());
				if(player.getPuzzle().cekAnswer()){
					timer.stop();
					JOptionPane.showMessageDialog(SudokuFrame.this, "selamat\nanda telah mencoba "+player.getPuzzle().getCounter()+" kali");
					tombolPause.setEnabled(false);
					tombolSimpan.setEnabled(false);
					database.loadHighScore();
					if(database.addHighScore(player)){
						JOptionPane.showMessageDialog(SudokuFrame.this, "selamat\nanda masuk ke highScore");
					}
					database.saveHighScore();
					hidePuzzle();
				}
				updateError(player.getPuzzle().getConflict());
			}
		};
		
		buatPapan();
		buatMenu();
		
		popup=new JPopupMenu();
		for(int i=1;i<=9;i++){
			JMenuItem popupItem=new JMenuItem(""+i);
			popupItem.addActionListener(klikPopup);
			popup.add(popupItem);
		}
		
		lblDurasi=new JLabel();
		lblNama=new JLabel();
		lblScore=new JLabel();
		lblLevel=new JLabel();
		lblStatus=new JLabel();
		GridBagConstraints aturan=new GridBagConstraints();
		
		papanStatus=new JPanel();
		papanStatus.setLayout(new GridBagLayout());
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=0;
		aturan.insets=new Insets(5,5,5,5);
		aturan.anchor=GridBagConstraints.EAST;
		papanStatus.add(new JLabel("Nama :"),aturan);
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=0;
		aturan.insets=new Insets(5,5,5,5);
		aturan.ipadx=60;
		aturan.anchor=GridBagConstraints.WEST;	
		papanStatus.add(lblNama,aturan);
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=1;
		aturan.insets=new Insets(5,5,5,5);
		aturan.anchor=GridBagConstraints.EAST;		
		papanStatus.add(new JLabel("Durasi :"),aturan);		
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=1;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.insets=new Insets(5,5,5,5);		
		papanStatus.add(lblDurasi,aturan);	
		
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=2;
		aturan.insets=new Insets(5,5,5,5);
		aturan.anchor=GridBagConstraints.EAST;		
		papanStatus.add(new JLabel("Score :"),aturan);	
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=2;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.insets=new Insets(5,5,5,5);		
		papanStatus.add(lblScore,aturan);
		
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=3;
		aturan.insets=new Insets(5,5,5,5);
		aturan.anchor=GridBagConstraints.EAST;		
		papanStatus.add(new JLabel("Level :"),aturan);	
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=3;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.insets=new Insets(5,5,5,5);		
		papanStatus.add(lblLevel,aturan);

		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=4;
		aturan.insets=new Insets(5,5,5,5);
		aturan.anchor=GridBagConstraints.EAST;		
		papanStatus.add(new JLabel("Status :"),aturan);	
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=4;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.insets=new Insets(5,5,5,5);		
		papanStatus.add(lblStatus,aturan);

		
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=5;
		aturan.gridwidth=2;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.ipadx=150;
		aturan.insets=new Insets(5,5,5,5);		
		papanStatus.add(new JLabel("                     "),aturan);
		
		papanTombol=new JPanel();
		papanTombol.setLayout(new GridBagLayout());
		tombolPause=new JToggleButton("Pause");
		tombolSimpan=new JButton("Simpan");
		
		tombolPause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tombolPause.isSelected()){
					timer.stop();
					tombolPause.setText("Lanjutkan");
					tombolSimpan.setEnabled(false);
					hidePuzzle();
				}else{
					timer.start();
					tombolPause.setText("Pause");
					tombolSimpan.setEnabled(true);
					showPuzzle();
				}
			}
		});
		
		tombolSimpan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lblStatus.setText("<html>terakhir disimpan <br/> "+ DateFormat.getTimeInstance().format(new Date())+"</html>");
				database.loadSavedGames();
				database.addGame(player);
				database.saveGames();
			}
		});
		
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=2;
		aturan.gridwidth=2;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.ipadx=150;
		aturan.insets=new Insets(5,5,5,5);		
		papanTombol.add(new JLabel("                     "),aturan);
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=0;
		aturan.gridwidth=2;
		aturan.anchor=GridBagConstraints.WEST;	
		aturan.insets=new Insets(5,5,5,5);		
		papanTombol.add(tombolPause,aturan);	
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=1;
		aturan.gridwidth=2;
		aturan.anchor=GridBagConstraints.WEST;
		aturan.insets=new Insets(5,5,5,5);		
		papanTombol.add(tombolSimpan,aturan);	
		
		
		setLayout(new GridBagLayout());
		aturan=new GridBagConstraints();
		aturan.gridx=0;
		aturan.gridy=0;
		aturan.gridwidth=1;
		aturan.gridheight=9;
		aturan.ipadx=200;
		aturan.ipady=200;
		add(papanPermainan,aturan);
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=0;
		aturan.insets=new Insets(5,5,5,5);			
		add(papanStatus,aturan);
		Border etched=BorderFactory.createEtchedBorder();
		Border borderStatus=BorderFactory.createTitledBorder(etched,"Informasi");
		papanStatus.setBorder(borderStatus);
		aturan=new GridBagConstraints();
		aturan.gridx=1;
		aturan.gridy=1;
		aturan.insets=new Insets(5,5,5,5);			
		aturan.anchor=GridBagConstraints.WEST;			
		add(papanTombol,aturan);	
		Border borderTombol=BorderFactory.createTitledBorder(etched,"Aksi");
		papanTombol.setBorder(borderTombol);
		
		database=new DatabaseFile();
		hidePuzzle();
		tombolSimpan.setEnabled(false);
		tombolPause.setEnabled(false);
	    timer=new Timer(1000,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EventQueue.invokeLater(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						player.getPuzzle().addDuration();
						updateStatus(true);
					}
					
				});				
			}
		});
	}
	
	private void updateStatus(boolean waktu){
		//jika true hanya waktu saja yang di update
		if(!waktu){
			lblScore.setText(""+player.getScore());
			lblNama.setText(player.getName());
			lblLevel.setText(""+player.getPuzzle().getLevel());	
		}
		lblDurasi.setText((player.getPuzzle().getDuration()/60)+":"+(player.getPuzzle().getDuration()%60));
	}
	
	private void hidePuzzle(){
		for(int i=0;i<81;i++){
			tombol[i].setEnabled(false);
			tombol[i].setText("  ");
		}
	}
	
	private void showPuzzle(){
		tampilPuzzle(player.getPuzzle().getAnswer());
	}
	
	private void tampilPuzzle(int data[]){
		String tmp;
		for(int i=0;i<81;i++){
			if(data[i]==0){
				tmp="  ";
				tombol[i].setEnabled(true);
			}else{
				tmp=""+data[i];
				if(player.getPuzzle().isEdit(i)){
					tombol[i].setEnabled(true);
				}else{
					tombol[i].setEnabled(false);
					tombol[i].setFont(new Font("SansSerif",Font.BOLD,14));
				}
			}
			tombol[i].setText(tmp);
		}
	}
	
	private void buatPapan(){
		papanPermainan.setLayout(new GridLayout(3,3));
		for(int index=0;index<81;index++){
			tombol[index]=new JButton("  ");
			tombol[index].setName(""+index);
			tombol[index].addMouseMotionListener(mouseListener);
			tombol[index].addActionListener(klikTombol);			
		}
		for(int i=0;i<9;i++){
			kotakKecil[i]=new JPanel(new GridLayout(3,3));
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					int index=(((int)Math.floor(i/3))*3+j)*9+(i%3)*3+k;
					System.out.println(index);
					kotakKecil[i].add(tombol[index]);
				}
			}			
			kotakKecil[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			papanPermainan.add(kotakKecil[i]);
		}
	}
	
	private void updateError(ArrayList<Integer> data){
		for(int i=0;i<81;i++){
			tombol[i].setForeground(Color.GRAY);
		}
		for(Integer i:data){
			tombol[i].setForeground(Color.RED);
		}
	}
	
	private void mulaiGameBaru(String name, int level){
		player=new Player(name);
		player.createPuzzle(level);
		tampilPuzzle(player.getPuzzle().getTampilPuzzle());
		updateStatus(false);
		timer.start();
		tombolSimpan.setEnabled(true);
		tombolPause.setEnabled(true);
		tombolPause.setSelected(false);
		tombolPause.setText("Pause");
		lblStatus.setText("belum disimpan");
		updateError(player.getPuzzle().getConflict());
	}
	
	private void buatMenu(){
		JMenu gameMenu=new JMenu("Permainan");
		JMenu bantuanMenu=new JMenu("Bantuan");
		
		gameMenu.setMnemonic('P');
		bantuanMenu.setMnemonic('B');
		JMenu newGame=new JMenu("New Game");
		newGame.setMnemonic('N');
		newGame.setIcon(new ImageIcon(getClass().getResource("/icon/play.gif")));
		ActionListener klikNewLevel=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String inputName="";
				while(inputName.trim().equals("")){
					String temp;
					temp= JOptionPane.showInputDialog("Masukkan nama anda !");
					if(temp!=null){
						inputName=temp;
					}else{
						return;
					}
				}
				if(inputName.length()>20) inputName=inputName.substring(0, 20);
				mulaiGameBaru(inputName,Integer.parseInt(e.getActionCommand()));
				
				System.out.println("Memulai level ke "+e.getActionCommand()+" "+inputName);
			}
			
		};
		for(int i=1;i<=13;i++){
			JMenuItem itemLevel=new JMenuItem(""+i);
			itemLevel.addActionListener(klikNewLevel);
			newGame.add(itemLevel);
		}		

		gameMenu.add(newGame);
		JMenuItem load=new JMenuItem("Load");
		load.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
		load.setMnemonic('L');
		load.setIcon(new ImageIcon(getClass().getResource("/icon/load.gif")));
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(dlgLoadGame==null)
					dlgLoadGame=new FormLoadGame(SudokuFrame.this,true);
				database.loadSavedGames();
				((FormLoadGame) dlgLoadGame).database=database;
				((FormLoadGame) dlgLoadGame).updateListGame();
				((FormLoadGame) dlgLoadGame).pilihan=-1;
				dlgLoadGame.setVisible(true);
				if(((FormLoadGame) dlgLoadGame).pilihan!=-1){
					System.out.println("terpilih");
					player=database.loadGame(((FormLoadGame) dlgLoadGame).pilihan);
					showPuzzle();
					timer.start();
					tombolPause.setEnabled(true);
					tombolSimpan.setEnabled(true);
					updateStatus(false);
					updateError(player.getPuzzle().getConflict());
					lblStatus.setText("memainkan game tersimpan");
					tombolPause.setSelected(false);
					tombolPause.setText("Pause");
				}
			}
		});
		gameMenu.add(load);
		JMenuItem highScore=new JMenuItem("HighScore");
		highScore.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
		highScore.setMnemonic('H');
		highScore.setIcon(new ImageIcon(getClass().getResource("/icon/high.gif")));
		highScore.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(dlgHighScore==null)
					dlgHighScore=new FormHighScore(SudokuFrame.this,true);
				database.loadHighScore();
				((FormHighScore)dlgHighScore).database=database;
				((FormHighScore)dlgHighScore).updateHighScore();
				dlgHighScore.setVisible(true);
			}
		});
		gameMenu.add(highScore);
		
		JMenuItem faq=new JMenuItem("FAQ");
		faq.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		faq.setMnemonic('F');
		faq.setIcon(new ImageIcon(getClass().getResource("/icon/faq.gif")));
		faq.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(dlgFAQ==null)
					dlgFAQ=new FormFAQ(SudokuFrame.this,true);
				
				dlgFAQ.setVisible(true);
			}
		});
		bantuanMenu.add(faq);
		
		JMenuItem tentang=new JMenuItem("Tentang");
		tentang.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		tentang.setMnemonic('T');
		tentang.setIcon(new ImageIcon(getClass().getResource("/icon/info.gif")));
		tentang.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(dlgTentang==null)
					dlgTentang=new FormAbout(SudokuFrame.this,true);
				
				dlgTentang.setVisible(true);
			}
		});
		bantuanMenu.add(tentang);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(gameMenu);		
		menuBar.add(bantuanMenu);
	}
	
	private ActionListener klikTombol,klikPopup;
	private JPanel papanPermainan,kotakKecil[],papanStatus,papanTombol;
	private JButton tombol[],tombolDipilih,tombolSimpan;
	private JToggleButton tombolPause;
	private JLabel lblDurasi,lblNama,lblScore,lblLevel,lblStatus;
    private JPopupMenu popup;
    private MouseMotionListener mouseListener;
    private int x,y;
    private Player player;
    public Timer timer;
	private JDialog dlgTentang,dlgFAQ,dlgHighScore,dlgLoadGame;
	private DatabaseFile database;
}