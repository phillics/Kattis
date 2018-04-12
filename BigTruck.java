//BigTruck

import java.util.*;

class Location{
	public int score;
	public int distance;
	public Location(int s, int d){
		this.score = s;
		this.distance = d;
	}
	public String toString(){
		return "[score: "+this.score+", dist: "+this.distance+"]\n";
	}
}

public class BigTruck{

	public BigTruck(){
		Scanner sc = new Scanner(System.in);
		int num_locations = sc.nextInt();
		Location[] locations = new Location[num_locations+2];
		int[] scores = new int[num_locations+2];

		for(int i = 1; i <= num_locations; i++){
			int s = sc.nextInt();
			locations[i] = new Location(s, Integer.MAX_VALUE);
			scores[i] = s;
		}

		//build adjMatrix
		int[][] adjMatrix = new int[num_locations+2][2+num_locations];
		int num_connections = sc.nextInt();
		for(int i = 0; i < num_connections; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int d = sc.nextInt();
			adjMatrix[a][b] = d;
			adjMatrix[b][a] = d;
		}


		//initialize start of graph
		locations[1].distance = 0;

		//start at 1 and work your way to end
		//for each connection at each node,
		//	determine new distance, and new score
		//  update that node if necessary,
		//  add that node to the next list
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		queue.add(1);

		int maxIterations = num_locations*num_locations;
		while(queue.isEmpty() == false && maxIterations-- > 0){
			//printAllLocations(locations);

			int cur = queue.poll();
			//add elements from adjMatrix to queue
			for(int j = 1; j <= num_locations; j++){
				if(j == cur || adjMatrix[cur][j] == 0)
					continue;

				//if we can improve j distance or score,
				//	update j and add it to the queue
				int newDist = locations[cur].distance + adjMatrix[cur][j];
				int newScore = locations[cur].score + scores[j];
				//if better distance or same distance and better score, update
				if(newDist < locations[j].distance || (newDist == locations[j].distance && newScore > locations[j].score)){
					locations[j].distance = newDist;
					locations[j].score = newScore;
					queue.add(j);
				}
			}//end for each element

		}//finished with queue

		if(locations[num_locations].distance == Integer.MAX_VALUE)
			System.out.println("impossible");
		else
			System.out.printf("%d %d\n", locations[num_locations].distance, locations[num_locations].score);

	}
	public void printAllLocations(Location[] l){
		for(int i = 1; i < l.length; i++){
			System.out.printf("%s", l[i]);
		}
		System.out.println();
	}

	public static void main(String[] args){
		BigTruck bt = new BigTruck();
	}
}
