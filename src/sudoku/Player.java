package sudoku;
import java.io.Serializable;

public class Player implements Serializable {
	private String name;
	private int score;
	private Puzzle puzzle;
	public Player(){
		
	}
	public Player(String name){
		this.name=name;
		this.score=0;
	}	
	public Player(String name, int score, Puzzle puzzle){
		this.name=name;
		this.score=score;
		this.puzzle=puzzle;
	}
	public void setPuzzle(Puzzle puzzle){
		this.puzzle=puzzle;
	}
	public Puzzle getPuzzle(){
		return puzzle;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setScore(int score){
		this.score=score;
	}
	public int getScore(){
		return score;
	}
	public void calculateScore(){
		score+=puzzle.getLevel()*20-(puzzle.getDuration()/15)+(puzzle.getLevel()*5/puzzle.getCounter());
	}
	public void createPuzzle(int level){
		puzzle=new Puzzle();
		puzzle.setLevel(level);		
		puzzle.choosePuzzle();
	}
}
