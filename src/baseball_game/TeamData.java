package baseball_game;

import java.util.ArrayList;

public class TeamData {

	String teamName;
	ArrayList<String> memberInfo = new ArrayList<>();
	
	public TeamData(String teamName, ArrayList<String> memberInfo) {
		this.teamName = teamName;
		this.memberInfo = memberInfo;
	}
}
