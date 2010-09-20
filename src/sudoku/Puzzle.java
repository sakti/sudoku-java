package sudoku;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Puzzle implements Serializable {
	private int templatePuzzle[][];
	private int currentPuzzle[];
	private int answerPuzzle[];
	private int level;
	private int duration;
	private int counter;
	private Date startTime;
	private Date endTime;
	private ArrayList<Integer> conflict;
	private boolean boolElm[]=new boolean[81];
	private boolean boolEdit[]=new boolean[81];
	private static final int CNTTEMPLATE=3;
	
	public Puzzle(){
		conflict=new ArrayList<Integer>();
		int tmpPuzzle[][]={
				 {4,2,9,3,1,6,5,7,8
				 ,8,6,7,5,2,4,1,9,3
				 ,5,1,3,8,9,7,2,4,6
				 ,9,3,1,7,8,5,6,2,4
				 ,6,8,2,9,4,1,7,3,5
				 ,7,4,5,2,6,3,9,8,1
				 ,3,5,4,6,7,2,8,1,9
				 ,1,7,8,4,5,9,3,6,2
				 ,2,9,6,1,3,8,4,5,7},
				 {9,6,5,4,1,8,7,3,2
				 ,1,4,3,2,6,7,9,5,8
				 ,8,2,7,9,5,3,6,1,4
				 ,5,7,9,3,8,4,1,2,6
				 ,4,1,2,6,9,5,3,8,7
				 ,6,3,8,1,7,2,4,9,5
				 ,3,5,4,7,2,1,8,6,9
				 ,7,8,6,5,3,9,2,4,1
				 ,2,9,1,8,4,6,5,7,3},     
				 {1,2,5,8,9,7,6,3,4
				 ,6,7,4,5,1,3,9,2,8
				 ,3,9,8,4,2,6,1,5,7
				 ,4,8,2,6,5,9,7,1,3
				 ,7,6,9,2,3,1,4,8,5
				 ,5,3,1,7,8,4,2,9,6
				 ,2,4,3,9,7,5,8,6,1
				 ,9,5,6,1,4,8,3,7,2
				 ,8,1,7,3,6,2,5,4,9}};		
		templatePuzzle=tmpPuzzle;
		currentPuzzle=new int[81];
		answerPuzzle=new int[81];
		level=1;
		duration=0;
		counter=0;
		startTime=new Date();
		endTime=new Date();
		for(int i=0;i<81;i++){
			boolEdit[i]=true;
			boolElm[i]=false;
			currentPuzzle[i]=0;
		}
	}
	public Date getEndTime(){
		return endTime;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getLevel(){
		return level;
	}
	public static void main(String... args){
		/*
		Puzzle a=new Puzzle();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++)
				System.out.print(a.currentPuzzle[j+i*9]);
			System.out.println();
		}
		System.out.println("==============");
		ArrayList<Integer> c=a.generateArrayRandom(10);
		for(Integer b: c){
			System.out.println(b);
		}
		System.out.println("Tes ="+(54/27)*27);
		System.out.print("test lagi =");
		int index=8;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				int tmpi=(index%3)*3+(27*(index/3))+j+9*i;
				System.out.print(tmpi+",");
			}
		}		
		*/
		Puzzle a=new Puzzle();
		a.setLevel(10);
		a.getTampilPuzzle();
		for(int i=0; i<81;i++){
			System.out.println("boolean ke "+i+" = "+a.boolElm[i]);
		}
		if(a.fillAll()){
			System.out.println("sudah terisi semua");
		}else{
			System.out.println("belum terisi semua");
		}
	}
	public void addCounter(){
		counter++;
	}
	public void addDuration(){
		duration++;
	}
	public int getCounter(){
		return counter;
	}
	public int getDuration(){
		return duration;
	}
	
	public ArrayList<Integer> generateArrayRandom(int n){
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		int i=0,tmpBil;
		while(i<n){
			tmpBil=(int)(Math.random()*81);
			if(!tmp.contains(tmpBil)){
				tmp.add(tmpBil);
				i++;
			}
		}
		return tmp;
	}
	
	public int[] getTampilPuzzle(){
		int tmp[]=new int[81];
		ArrayList<Integer> randomArray;
		
		randomArray=generateArrayRandom(level*5);
		for(int i=0;i<81;i++){
			if(randomArray.contains(i)){
				tmp[i]=0;
			}else{
				tmp[i]=currentPuzzle[i];
				boolEdit[i]=false;
				boolElm[i]=true;
				answerPuzzle[i]=currentPuzzle[i];
			}
		}
		return tmp;
	}
	
	public boolean isEdit(int i){
		return boolEdit[i];
	}
	
	public int[] getAnswer(){
		return answerPuzzle;
	}
	
	public void transformPuzzle(int tipe){
		int tmp,div9,mod9,tmpindex;
		//div => baris, mod => kolom
		//operasi div di java => / bukan 'div'
		
		switch(tipe){
		case 0:
			//transformasi vertikal
			for(int i=0;i<81;i++){
				if(i%9<4){
					tmp=currentPuzzle[i];
					div9=i/9;
					tmpindex=(9*div9+8)-(i-(9*div9));
					currentPuzzle[i]=currentPuzzle[tmpindex];
					currentPuzzle[tmpindex]=tmp;
				}
			}
			break;
		case 1:
			//transformasi diagonal kanan
			for(int i=0;i<81;i++){
				if((i/9)+(i%9)<8){
					tmp=currentPuzzle[i];
					div9=i/9;
					mod9=i%9;
					tmpindex=(8-mod9)*9+8-div9;
					currentPuzzle[i]=currentPuzzle[tmpindex];
					currentPuzzle[tmpindex]=tmp;
				}
			}			
			break;
		case 2:
			//transformasi diagonal kiri
			for(int i=0;i<81;i++){
				if(i/9>i%9){
					tmp=currentPuzzle[i];
					div9=i/9;
					mod9=i%9;
					tmpindex=div9+mod9*9;
					currentPuzzle[i]=currentPuzzle[tmpindex];
					currentPuzzle[tmpindex]=tmp;
				}
			}					
			break;
		case 3:
			//transformasi horizontal
			for(int i=0;i<81;i++){
				if(i/9<4){
					tmp=currentPuzzle[i];
					div9=i/9;
					mod9=i%9;
					tmpindex=mod9+(8-div9)*9;
					currentPuzzle[i]=currentPuzzle[tmpindex];
					currentPuzzle[tmpindex]=tmp;
				}
			}					
			break;
		}
	}
	
	public boolean fillAll(){
		boolean temp=true;
		for(int i=0; i<81;i++){
			if(boolElm[i]==false){
				temp=false;
				break;
			}
		}		
		return temp;
	}
	public boolean cekAnswer(){
		boolean benar=true;
		for(int i=0;i<81;i++){
			if(answerPuzzle[i]!=currentPuzzle[i]){
				benar=false;
				break;
			}
		}
		if(fillAll() && conflict.size()==0) benar=true;
		System.out.println("CONFLICT = "+conflict.size());
		System.out.println("Sudah terisi semua ="+fillAll());
		if(benar) endTime=new Date();
		return benar;
	}
	
	public void checkConflict(int pos,int count){
		//jangan dihapus terlebih dahulu
		//conflict.clear();
		//JOptionPane.showMessageDialog(null,"pemanggilan fungsi dengan parameter count="+count+";pos="+pos);
		if(count==0) return;
		boolean error=false;
		int row=pos/9;
		int col=pos%9;
		
		//cek kolom
		//JOptionPane.showMessageDialog(null,"cek kolom dengan parameter count="+count+";pos="+pos);
		for(int i=0;i<9;i++){
			int tmpi=i*9+col;
			if(tmpi!=pos){
				//JOptionPane.showMessageDialog(null,answerPuzzle[tmpi]+"=="+answerPuzzle[pos] );
				if(answerPuzzle[tmpi]==answerPuzzle[pos]){
					//JOptionPane.showMessageDialog(null,"benar");
					error=true;
					if(!conflict.contains(tmpi)) conflict.add(tmpi);				
				}else{
					//JOptionPane.showMessageDialog(null,"salah");
					if(conflict.contains(tmpi)) {
						//JOptionPane.showMessageDialog(null,"KOLOM :cek index ini dunk "+tmpi+" count:"+count);
						if(count!=1) {
							conflict.remove((Integer)tmpi);
							//JOptionPane.showMessageDialog(null,"rekursif terjadi dari kolom");
							checkConflict(tmpi,1);
						}
					}		
				}
			}
			/*
			if(tmpi!=pos&&answerPuzzle[tmpi]==answerPuzzle[pos]){
				error=true;
				if(!conflict.contains(tmpi)){
					conflict.add(tmpi);
				}
			}else{
				if(conflict.contains(tmpi)){
					conflict.remove((Integer)tmpi);
					if(tmpi!=pos&&count!=1){
						System.out.println("Terjadi rekursif kolom di "+tmpi);
						checkConflict(tmpi,1);
					}
				}
			}
			*/
		}
		
		//cek baris
		//JOptionPane.showMessageDialog(null,"cek baris dengan parameter count="+count+";pos="+pos);
		for(int i=0;i<9;i++){
			int tmpi=9*row+i;
			if(tmpi!=pos){
				if(answerPuzzle[tmpi]==answerPuzzle[pos]){
					error=true;
					if(!conflict.contains(tmpi)) conflict.add(tmpi);				
				}else{
					if(conflict.contains(tmpi)) {
						//JOptionPane.showMessageDialog(null,"BARIS :cek index ini dunk "+tmpi+" count:"+count);
						if(count!=1) {
							conflict.remove((Integer)tmpi);
							//JOptionPane.showMessageDialog(null,"rekursif terjadi dari kolom");
							checkConflict(tmpi,1);
						}
					}		
				}
			}			
			/*
			if(tmpi!=pos&&answerPuzzle[tmpi]==answerPuzzle[pos]){
				error=true;
				if(!conflict.contains(tmpi)){
					conflict.add(tmpi);
				}
			}else{
				if(conflict.contains(tmpi)){
					conflict.remove((Integer)tmpi);
					if(tmpi!=pos&&count!=1){
						System.out.println("Terjadi rekursif baris di "+tmpi);
						checkConflict(tmpi,1);
					}
				}				
			}
			*/
		}	
		
		//cek kotak
		//JOptionPane.showMessageDialog(null,"cek kotak dengan parameter count="+count+";pos="+pos);
		int index=(pos/27)*3+(pos%9)/3;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				int tmpi=(index%3)*3+(27*(index/3))+j+9*i;
				if(tmpi!=pos){
					if(answerPuzzle[tmpi]==answerPuzzle[pos]){
						error=true;
						if(!conflict.contains(tmpi)) conflict.add(tmpi);				
					}else{
						if(conflict.contains(tmpi)){
							//JOptionPane.showMessageDialog(null,"KOTAK :cek index ini dunk "+tmpi+" count:"+count);
							if(count!=1) {
								conflict.remove((Integer)tmpi);
								//JOptionPane.showMessageDialog(null,"rekursif terjadi dari kotak");
								checkConflict(tmpi,1);
							}
						}		
					}
				}				
				/*
				if(tmpi!=pos&&answerPuzzle[tmpi]==answerPuzzle[pos]){
					error=true;
					if(!conflict.contains(tmpi)){
						conflict.add(tmpi);
					}
				}else{
					 if(conflict.contains(tmpi)){
						conflict.remove((Integer)tmpi);
						if(tmpi!=pos&&count!=1){
							System.out.println("Terjadi rekursif kotak di "+tmpi);
							checkConflict(tmpi,1);
						}			
					}					
				}
				*/
			}
		}
		
		
		if(error){
			System.out.println("*************\nterjadi error\n****************");
			if(!conflict.contains(pos)){
				conflict.add(pos);
			}
		}else if(conflict.contains(pos)){
			conflict.remove((Integer)pos);
		}
		
	}
	
	public ArrayList<Integer> getConflict(){
		return conflict;
	}
	
	public boolean setAnswer(int index,int value){
		//mengembalikan nilai true jika nilai yang diisikan berbeda dengan nilai sebelumnya
		boolean temp=false;
		boolElm[index]=true;
		if(answerPuzzle[index]!=value)temp=true;
		answerPuzzle[index]=value;
		checkConflict(index,4);
		return temp;
	}
	
	public void choosePuzzle(){
		currentPuzzle=templatePuzzle[(int)(Math.random()*CNTTEMPLATE)];
		for(int i=0;i<5;i++){
			transformPuzzle((int)Math.random()*4);
		}
	}
	public Date getStartTime() {
		return startTime;
	}
}
