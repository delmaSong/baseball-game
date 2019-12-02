package baseball_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Step2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	ArrayList<TeamData> teamInfo = new ArrayList<>();
	TeamPoint t1 = new TeamPoint();
	TeamPoint t2 = new TeamPoint();
	String inning = "회초";
	String nowTeam = "";
	int t1PlayerNum = 1;
	int t2PlayerNum = 1;
	boolean antaFlag = false;
	int compCnt = 1;
	boolean endFlag = false;
	
	public static void main(String[] args) {
		Step2 s2 = new Step2();
		while(true){ 
			s2.selectMenu();
			if(s2.endFlag)
				break;
		}
	}
	
	public void selectMenu() {
		System.out.print("\n신나는 야구시합 \n1. 데이터 입력 \n2. 데이터 출력 \n3. 시합 시작 \n\n메뉴선택 (1 - 3) ");
		try {
			String selectNum = reader.readLine();
			if(selectNum.equals("1")) {
				inputTeamInfo();
			}else if(selectNum.equals("2")){
				printTeamInfo();
			}else if(selectNum.equals("3")){
				doCompetition();
			}else {
				System.out.println("다시 선택해주세요 ");
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
				System.out.println((j+1)+"번 "+teamInfo.get(i).memberInfo.get(j));
				}
			}
		}
	}
	
	public void doCompetition() {
		System.out.println(teamInfo.get(0).teamName + " VS " + teamInfo.get(1).teamName + "의 시합을 시작합니다. \n");
		System.out.println(compCnt+inning+" "+teamInfo.get(0).teamName+" 공격 ");

		while(compCnt != 7) {
			nowPlayer();
		}
		finishGame();
	}

	public String countHitRate(double h) {
		int anta = 0;
		int strike = 0;
		int ball = 0;
		int out = 0;
		int point = 0;
		String result = "";
		double ranNum = Math.random();
		ranNum = Math.round(ranNum*1000) / 1000.0;
		if(0.0 <= ranNum && ranNum <= 0.1) {
			result = "아웃";
			out += 1;
		}else if(0.1 < ranNum && ranNum <= (1 - h) / 2 - 0.05) {	
			result = "스트라이크";
			strike += 1;
		}else if((1 - h) / 2 - 0.05 <= ranNum && ranNum <= (1 - h) / 2 - 0.05) {
			result = "볼";
			ball += 1;
		}else {
			result = "안타";
			anta += 1;
			if (antaFlag) { point += 1; }
		}
		System.out.println(result+"!");
		countPoint(out, strike, ball, anta, point);
		return result;
	}

	public void countPoint(int out, int strike, int ball, int anta, int point) {
		if(inning.equals("회초")) {
			t1.updatePoint(out, strike, ball, anta, point);
			cntSBO(t1);
		}else {
			t2.updatePoint(out, strike, ball, anta, point);
			cntSBO(t2);
		}	
	}
	
	public void cntSBO(TeamPoint tp) {
		if(tp.strike == 3) {
			tp.strike = 0;
			tp.out += 1;
		}
		if(tp.ball == 4) {
			tp.ball = 0;
			tp.anta += 1;
		}
		if(tp.anta == 4 || !antaFlag) {	
			tp.anta = 0;
			tp.point += 1;
			antaFlag = true;
		}
		if(tp.out == 3) {
			turnSwitch();
		}
		System.out.println(tp.strike+"S "+tp.ball+"B "+tp.out+"O \n");
	}

	public void nowPlayer() {
		String result = "";
		if(inning.equals("회초")) {
			String[] info = teamInfo.get(0).memberInfo.get(t1PlayerNum-1).split(", ");
			System.out.println(t1PlayerNum+"번 "+ info[0]);
			result = countHitRate(Double.parseDouble(info[1]));
		}else {
			String[] info = teamInfo.get(1).memberInfo.get(t2PlayerNum-1).split(", ");
			System.out.println(t2PlayerNum+"번 "+ info[0]);
			result = countHitRate(Double.parseDouble(info[1]));
		}
		if(result.equals("안타") || result.equals("아웃")) {
			nextPlayer();
		}
	}
	
	public void nextPlayer() {
		if(inning.equals("회초")) {
			if(t1PlayerNum == 9) {
				t1PlayerNum = 1;
			}else {
				t1PlayerNum += 1;
			}
		}else {
			if(t2PlayerNum == 9) {
				t2PlayerNum = 1;
			}else {
				t2PlayerNum += 1;
			}
		}
	}
	public void turnSwitch() {	//회말일때 넘어가면 다음 이닝으로 
		if(inning.equals("회초")) {
			inning = "회말";
			nowTeam = teamInfo.get(1).teamName;
		}else {
			inning = "회초";
			nowTeam = teamInfo.get(0).teamName;
			compCnt += 1;
		}
		System.out.println(compCnt+inning+" "+nowTeam+" 공격 ");
	}
	
	public void finishGame() {
		endFlag = true;
		System.out.println(teamInfo.get(0).teamName +" VS "+ teamInfo.get(1).teamName);
		System.out.println(t1.point + " : " + t2.point);
		System.out.println("Thank you! ");
	}
	
	
}
