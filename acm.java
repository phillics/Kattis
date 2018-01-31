//acm.java

/*

*/

import java.util.*;


class Node{
	public char problem;
	public int penalty;
	public boolean solved;
	public int minutes;

	public Node(char p, int m, boolean s){
		this.problem = p;
		this.minutes = m;
		this.solved = s;
		this.penalty = 0;
	}
	public void attempt(int m, boolean s){
		if(this.solved)
			return; //problem already solved
		this.penalty += 20;
		this.minutes = m;
		this.solved = s;
	}
	public String toString(){
		return "<"+problem+" "+minutes+" "+solved+">";
	}
}

public class acm{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int minute;
		char problem;
		boolean success;

		LinkedList<Node> myList = new LinkedList<>();

		//collect data
		while(true){
			minute = sc.nextInt();
			if(minute < 0)
				break;
			problem = sc.next().trim().charAt(0);
			success = (sc.next().trim().charAt(0) == 'r') ? true : false;

			//check if this problem has been attempted
			boolean added = false;
			for(Node n : myList)
				if(n.problem == problem){
					n.attempt(minute, success);
					added = true;
					break;
				}

			if(!added){
				//else add new node to list
				Node newNode = new Node(problem, minute, success);
				myList.add(newNode);
			}

			//System.out.println(myList);
		}

		//analyze data
		int problems_solved = 0;
		int score = 0;

		for(Node n : myList)
			if(n.solved){
				problems_solved++;
				score += n.minutes + n.penalty;
			}
		System.out.printf("%d %d\n", problems_solved, score);

	}
}
