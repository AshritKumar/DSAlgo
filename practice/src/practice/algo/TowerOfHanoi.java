package practice.algo;

public class TowerOfHanoi {
	int steps = 0;
	public void solve(int NoT, char from, char to, char inter){
		if(NoT == 1) {
			System.out.println("Move Disc 1 from "+from+" to "+to);
			++steps;
		} else {
			solve(NoT-1,from,inter,to);
			System.out.println("Move Disc "+NoT+" from "+from+" to "+to);
			solve(NoT-1,inter,to,from);
			++steps;
		}
	}
	
	public static void main(String[] args) {
		TowerOfHanoi toh = new TowerOfHanoi();
		toh.solve(3, 'A', 'C', 'B');
		System.out.println(toh.steps);
	}

}
