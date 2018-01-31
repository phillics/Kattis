import java.util.*;

public class Autori{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String[] input = sc.next().split("-");
		for(String s : input)
			System.out.printf("%c", s.charAt(0));
	}
}
