public class PatternPrinting {
	public static void main(String[] args) {
		int cnt=1;
		for(int i = 0 ; i < 6 ; i++) {
			for(int j = 1; j < 13 ; j = j+2) {
				System.out.print(j+" ");
				if(j==cnt) {
					cnt=cnt+2;
					break;
				}
			}
			System.out.println();
		}
	}
}
