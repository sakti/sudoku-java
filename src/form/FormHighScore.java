package form;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.text.DateFormat;

import javax.swing.*;

import database.*;
public class FormHighScore extends JDialog {
	public DatabaseFile database;
	private JLabel lblHighScore;
	public FormHighScore(Frame owner,boolean modal){
		super(owner,modal);
		this.setSize(900, 600);
		this.setTitle("HighScore");
		lblHighScore=new JLabel("");
		JPanel panel=new JPanel();
		panel.add(lblHighScore);
		this.add(panel,BorderLayout.CENTER);
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);		
	}
	public void updateHighScore(){
		HighScoreData[] artemp=database.getListHighScore();
		int i=0;
		DateFormat df = DateFormat.getDateInstance();
		DateFormat tf=DateFormat.getTimeInstance(DateFormat.SHORT);
		String txtHS="<html><h1>HighScore</h1></hr><table border=1>" +
				"<tr><th>No</th><th>Nama</th><th>Score</th><th>Level</th><th>Durasi Permainan</th><th>Jumlah percobaan</th><th>Selesai main</th></tr>";
		for(HighScoreData temp:artemp){
			if(temp!=null){
				i++;
				txtHS+="<tr><td>"+i+"</td><td><b>"+temp.name+"</b></td><td>"+temp.score+"</td><td>"+temp.level
				+"</td><td>"+(temp.duration/3600)+":"+(temp.duration/60)+":"+(temp.duration%60)+"</td><td>"+temp.trying+"</td><td>"+df.format(temp.endDate)+" "+tf.format(temp.endDate)+"</td></tr>";
			}
		}
		if(i==0){
			txtHS+="<tr><td colspan=7><b>tidak ada data highscore</b></td></tr>";
		}else{
			txtHS+="</table></html>";
		}
		System.out.println(txtHS);
		lblHighScore.setText(txtHS);
	}
	
	
}
