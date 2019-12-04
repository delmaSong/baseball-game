package baseball_game;

import java.util.Random;

public class Step1 {
	Random ran = new Random();

	int strikeCnt = 0;
	int ballCnt = 0;
	int outCnt = 0;
	int antaCnt = 0;
	
	public static void main(String[] args) {
		Step1 s1 = new Step1();
		s1.playGame();
	}

	public void playGame() {
		System.out.println("신나는 야구 게임! \n첫 번째 타자가 타석에 입장했습니다.");
		while(outCnt != 3) {
			drawRandom();
			cntPoint();
		}
			finishGame();
	}

	public void drawRandom() {
		int randomResult = (ran.nextInt(4)+0);
		if(randomResult == 0) {
			System.out.print("\n스트라이크! ");
			strikeCnt += 1;
		}else if(randomResult == 1) {
			System.out.print("\n볼! ");
			ballCnt += 1;
		}else if(randomResult == 2) {
			System.out.print("\n아웃! ");
			outCnt += 1 ;
			comeNextHitter();
		}else {
			System.out.print("\n안타! ");
			antaCnt += 1;
			comeNextHitter();
		}
	}
	
	public void cntPoint() {
		if ( strikeCnt == 3 ) {
			strikeCnt = 0;
			outCnt += 1;
		}
		if (ballCnt == 4) {
			ballCnt = 0;
			antaCnt += 1;
		}
		System.out.println("\n"+ strikeCnt+"S "+ballCnt+"B " + outCnt+"O ");
	}
	
	public void comeNextHitter() {
		System.out.print("다음 타자가 타석에 입장했습니다.");
		strikeCnt = 0;
		ballCnt = 0;
	}
	
	public void finishGame() {
		System.out.println("\n최종 안타수: " + antaCnt);
		System.out.println("GAME OVER");
	}
	
}
