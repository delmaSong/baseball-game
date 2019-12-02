package baseball_game;

public class TeamPoint {
	int out = 0;
	int strike = 0;
	int ball = 0;
	int anta = 0;
	int point = 0;
	
	public void updatePoint(int o, int s, int b, int a, int p) {
		out += o;
		strike += s;
		ball += b;
		anta += a;
		point += p;
	}
}
