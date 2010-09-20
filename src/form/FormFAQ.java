package form;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
public class FormFAQ extends JDialog {
	public FormFAQ(Frame owner,boolean modal){
		super(owner,modal);
		this.setSize(300, 200);
		this.setTitle("FAQ");
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);			
		add(new JLabel("<html>" +
				"<center><h1>FAQ</h1></center><hr/>" +
				"<ol>" +
				"<li>Bagaimana cara memainkan Game Sudoku ini?</li>" +
				"<p>" +
				"Pertama kita masuk ke permainan “Game Sudoku”, lalu kita akan diberikan sebuah tampilan untuk <br/>" +
				"memilih level yang akan kita mainkan. Setelah itu kita harus memasukkan nama untuk bisa masuk <br/>" +
				"ke permainan." +
				"</p><br/>" +
				"<li>Bagaimana cara mem-pause permainan?</li>" +
				"<p>" +
				"Saat kita memainkan Game Sudoku ini, kita bisa mem-pause (menghentikan) sementara, dengan <br/>" +
				"mengklik tombol pause  pada menu disebelah kanan. Saat kita mengklik tombol pause maka secara<br/>" +
				"otomatis waktu juga  akan berhenti. Untuk memulai  permainan kembali maka kita bisa mengklik tombol<br/>" +
				"lanjutkan." +
				"</p><br/>" +
				"<li>Bagaimana cara menyimpan permainan?</li>" +
				"<p>" +
				"Kita bisa menyimpan permainan yang sedang kita mainkan dengan cara mengklik tombol simpan pada <br/>" +
				"menu disebalah kanan." +
				"</p><br/>" +
				"<li>Bagaimana cara me-load (memainkan kembali) permainan yang telah kita simpan?</li>" +
				"<p>" +
				"Kita bisa memainkan kembali permainan yang sebelumnya telah kita simpan dengan cara pilih menu permainan,<br/>" +
				"lalu klik load. Maka kita akan bisa memainkan kembali permainan yang telah kita simpan dengan mengklik tombol<br/>" +
				"pilih. Namun kita juga bisa menghapusnya dengan mengklik  tombol  hapus." +
				"</p><br/>" +
				"<li>Bagaimana tingkat kesulitan dari Game Sudoku ini?</li>" +
				"<p>" +
				"Tingkat kesulitan dari permainan Sudoku ini tergantung dari level yang akan kita mainkan. <br/>" +
				"Semakin tinggi level permainan maka semakin tinggi juga tingkat kesulitannya. Level permainan<br/>" +
				"dimulai dari level 1 hingga level 13." +
				"</p><br/>" +
				"<li>Bagaimana cara menghitung score (nilai) dalam permainan ini?</li>" +
				"<p>" +
				"Penghitungan score dalam permainan ini berdasarkan tingkat level yang kita mainkan.<br/>" +
				"Dimana tingkat level permainan *20 dikurangi durasi(lama waktu untuk menyelesaikan permainan)<br/>" +
				"dibagi 15(dalam detik) ditambah tingkat level permainan*5 dibagi jumlah percobaan." +
				"<br/><i>(level * 20) – (durasi/15) + (level*5) /  (jumlah percobaan)</i>" +
				"</p><br/>" +
				"<li>Apa maksud warna merah yang muncul pada angka yang kita masukkan? </li>" +
				"<p>" +
				"Saat memainkan Game Sudoku ini, kita mungkin menjumpai angka yang kita masukkan akan berwarna merah.<br/>" +
				"Hal ini disebabkan karena dalam satu baris atau dalam satu kotak terdapat angka yang sama, yang seharusnya<br/>" +
				"hal ini tidak diperbolehkan." +
				"</p><br/>" +
				"</ol>" +
				"</html>"));
		JButton exit=new JButton("Keluar");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FormFAQ.this.setVisible(false);
			}
		});
		add(exit,BorderLayout.SOUTH);
		
		this.pack();
	}
}
