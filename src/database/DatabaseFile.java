package database;

import java.util.ArrayList;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


import sudoku.Player;
public class DatabaseFile {
	private String fileHighScore;
	private String fileSavedGame;
	private FileOutputStream fos;
	private FileInputStream fis;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private HighScoreData[] hsdata;
	private ArrayList<Player> pldata;
	private int countHsdata;
	public static void main(String... args){
		DatabaseFile a=new DatabaseFile();
		a.loadSavedGames();
		
//		Player player=new Player("h1zbullah");
//		player.createPuzzle(13);
//		player.getPuzzle().getTampilPuzzle();
//		a.addGame(player);
		
		for(Player temp:a.getListGame()){
			System.out.println("nama = "+temp.getName());
			System.out.print("Puzzle = ");
			for(int tmp: temp.getPuzzle().getAnswer()){
				System.out.print(tmp+",");
			}
			System.out.println();
		}
		a.saveGames();
		
		 // testing untuk highscore
//		a.loadHighScore();
//		a.addHighScore(new HighScoreData("Rakhmat Hidayat",193,185,4,33,new Date()));
//		a.addHighScore(new HighScoreData("Sakti dwi cahyono",900,333285,13,123213,new Date()));
		
//		System.out.println("jumlah data ="+a.countHsdata);
//		for(int i=0;i<a.countHsdata;i++){
//			System.out.println("nama = "+a.hsdata[i].name);
//			System.out.println("score = "+a.hsdata[i].score);
//			System.out.println("duration = "+a.hsdata[i].duration);
//			System.out.println("Level = "+a.hsdata[i].level);
//			System.out.println("===========================");
//		}
//		a.saveHighScore();
		
	}
	public DatabaseFile(){		
		fileHighScore=System.getProperty("user.home")+System.getProperty("file.separator")+"high.dat";
		fileSavedGame=System.getProperty("user.home")+System.getProperty("file.separator")+"game.dat";
		hsdata=new HighScoreData[10];
		countHsdata=0;
		pldata=new ArrayList<Player>();
	}
	public int inHighScore(int score){
		//mengembalikan nilai -1 jika tidak termasuk ke HS
		
		for(int i=0;i<countHsdata;i++){
			if(hsdata[i].score<score) return i;
		}
		if(countHsdata<hsdata.length) return countHsdata;
		return -1;
	}
	public void addHighScore(HighScoreData temp){
		int pos=inHighScore(temp.score);
		if(pos!=-1){
			for(int i=hsdata.length-1;i>pos;i--){
				System.out.println("I="+i);
				hsdata[i]=hsdata[i-1];
			}
			hsdata[pos]=temp;
		}else{
			return;
		}	
		if(countHsdata<10) countHsdata++;
	}	
	public boolean addHighScore(Player player){
		boolean masuk=false;
		HighScoreData temp=new HighScoreData(player.getName(),player.getScore(),player.getPuzzle().getDuration(),player.getPuzzle().getLevel(),player.getPuzzle().getCounter(),player.getPuzzle().getEndTime());
		int pos=inHighScore(temp.score);
		if(pos!=-1){
			for(int i=hsdata.length-1;i>pos;i--){
				System.out.println("I="+i);
				hsdata[i]=hsdata[i-1];
			}
			hsdata[pos]=temp;
			masuk=true;
		}else{
			return false;
		}	
		if(countHsdata<10) countHsdata++;
		return masuk;
	}
	public void saveHighScore(){
		try {
			fos=new FileOutputStream(fileHighScore);
			oos=new ObjectOutputStream(fos);
			for(int i=0;i<hsdata.length;i++){
				oos.writeObject(hsdata[i]);
			}
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveGames(){ 
		try {
			fos=new FileOutputStream(new File(fileSavedGame));
			oos=new ObjectOutputStream(fos);
			System.out.println("tulis "+pldata.size()+" data player");
			for(Player temp: pldata){
				System.out.println("tulis-tulis "+temp.getName());
				oos.writeObject(temp);
			}
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void loadSavedGames(){
		Player inputTemp;
		pldata.clear();
		try {
			fis=new FileInputStream(new File(fileSavedGame));
			ois=new ObjectInputStream(fis);
			while((inputTemp=(Player) ois.readObject()) != null){
				pldata.add(inputTemp);
			}
			ois.close();
		}catch(FileNotFoundException e){
			System.out.println("file terhapus / tidak bisa dibuka");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO exception ");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassCastException e){
			System.out.println("lanjut aja file rusak, set ke kosong");
		}		
	}
	public void loadHighScore(){
		HighScoreData inputTemp;
		countHsdata=0;
		try {
			fis=new FileInputStream(fileHighScore);
			ois=new ObjectInputStream(fis);
			while((inputTemp=(HighScoreData) ois.readObject()) != null){
				hsdata[countHsdata]=inputTemp;
				countHsdata++;
				if(countHsdata==10) break;
			}
			ois.close();
		}catch(FileNotFoundException e){
			System.out.println("file terhapus / tidak bisa dibuka");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO exception");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassCastException e){
			System.out.println("lanjut aja file rusak, set ke kosong");
		}
	}
	public HighScoreData[] getListHighScore(){
		return hsdata;
	}
	public Player loadGame(int index){
		return pldata.get(index);
	}
	public void addGame(Player game){
		pldata.add(game);
	}
	public void removeGame(int index){
		pldata.remove(index);
	}
	public ArrayList<Player> getListGame(){
		return pldata;
	}
}
