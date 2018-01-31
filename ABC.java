//ABC

import java.util.*;

public class ABC{

	public static void main(String[] args){
		int nums[] = new int[3];
		int tmp = 0;

		Scanner sc = new Scanner(System.in);

		nums[0] = sc.nextInt();
		nums[1] = sc.nextInt();

		if(nums[0] > nums[1]){
			tmp = nums[1];
			nums[1] = nums[0];
			nums[0] = tmp;
		}

		nums[2] = sc.nextInt();

		if(nums[1] > nums[2]){
			tmp = nums[2];
			nums[2] = nums[1];
			nums[1] = tmp;
		}

		if(nums[0] > nums[1]){
			tmp = nums[1];
			nums[1] = nums[0];
			nums[0] = tmp;
		}

		String order = sc.next();
		tmp = 0;
		int k = 0;
		while(tmp < 3){
			switch(order.charAt(tmp)){
				case 'A': k = 0;
					break;
				case 'B': k = 1;
					break;
				case 'C': k = 2;
				default: break;
			}
			System.out.printf("%d ", nums[k]);
			tmp++;
		}
		System.out.println();


	}
}
