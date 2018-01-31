//Average
import java.util.*;

public class Average{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int lines = sc.nextInt();
		for(int i = 0; i < lines; i++){
			int students = sc.nextInt();
			int[] s = new int[students];
			int sum = 0;
			for(int j = 0; j < students; j++){
				s[j] = sc.nextInt();
				sum += s[j];
			}

			double average = (double) sum / (double) students;
			int num = 0;
			for(int j = 0; j < students; j++)
				if(s[j] > average)
					num++;

			System.out.printf("%.3f%%\n", (float) num / (float) students * 100.0);

		}
	}
}
