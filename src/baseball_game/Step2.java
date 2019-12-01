package baseball_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Step2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	ArrayList<TeamData> teamInfo = new ArrayList<>();
	
	public static void main(String[] args) {
		Step2 s2 = new Step2();
		while(true){ 
			s2.doGame();
		}
	}
	
	public void doGame() {
		System.out.print("\n신나는 야구시합 \n1. 데이터 입력 \n2. 데이터 출력\n\n메뉴선택 (1 - 2) ");
		try {
			String selectNum = reader.readLine();
			if(selectNum.equals("1")) {
				inputTeamInfo();
			}else if(selectNum.equals("2")){
				printTeamInfo();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inputTeamInfo() throws IOException {
		String name = "";
		for(int i=1; i<3; i++) {
			ArrayList<String> tmpList = new ArrayList<>();
			System.out.print(i+"팀의 이름을 입력하세요> ");
			name = reader.readLine();
			for(int j=1; j<10; j++) {
				System.out.print(j+"번 타자 정보 입력 > ");
				tmpList.add(reader.readLine());
			}			
			teamInfo.add(new TeamData(name, tmpList));
			System.out.println("");
		}
		System.out.println("\n팀 데이터 입력이 완료되었습니다. ");
	}
	
	public void printTeamInfo() {
		if(teamInfo.size() == 0) {
			System.out.println("입력된 데이터가 없습니다 ");
		}else {
			for(int i=0; i<teamInfo.size(); i++) {
				System.out.println(teamInfo.get(i).teamName+" 팀 정보 ");
				for(int j=0; j<9; j++) {
				System.out.println(teamInfo.get(i).memberInfo.get(j));
				}
			}
		}
	}
	
	
	
	
	
	
	
}
