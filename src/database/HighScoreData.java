package database;
import java.io.*;
import java.util.Date;

public class HighScoreData implements Serializable {
	public String name;
	public int score;
	public int duration;
	public int level;
	public int trying;
	public Date endDate;
	public HighScoreData(String name,int score,int duration,int level,int trying,Date endDate){
		this.name=name;
		this.score=score;
		this.duration=duration;
		this.level=level;
		this.trying=trying;
		this.endDate=endDate;
	}
}

