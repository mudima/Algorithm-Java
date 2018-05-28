package Leetcode;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII {
	//S1: BFS
	//arraylist[] pres to store all precourses for index course
	//int[] count to store number of precourse index need to be
	//decrease count[idx] for BFS expands, only current highest course can into queue
	//time: build graph and count n+L + BFS n*L = O(n*L), each course only in&out queue once, when out queue, do L to expand
	//space: graph n*L + count n + queue n = O(n*L)
	public int[] findOrderS1(int numCourses, int[][] prerequisites) {
		int[] result = new int[numCourses];
		int index = 0; //use for assign result array
		if (numCourses <= 0) return new int[0];
		if (prerequisites == null || prerequisites.length == 0) {
			for (int i = 0; i < numCourses; i++) {
				result[i] = i;
			}
			return result;
		}
		
		//graph stores all super courses of course i
		ArrayList[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		//count stores number of prerequisites course i need
		int[] count = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			count[prerequisites[i][0]]++;
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		//add most basic courses (need no pre course) into queue
		for (int i = 0; i < numCourses; i++) {
			if (count[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			ArrayList<Integer> pres = graph[cur];
			for (int pre : pres) {
				count[pre] -= 1;
				if (count[pre] == 0) {
					queue.offer(pre);
				}
			}
			result[index++] = cur;
		}
		
		if (index == numCourses) return result;
		return new int[0];
	}
	
	//S2: DFS with visited
	//graph -> all courses need course i to be pre
	//count -> number of pre courses course i need
	//visited -> is this course been checked during dfs
	//when course i has no pres, add i into result and visited, do dfs(i), for every course in graph[i], count[idx]--, if 0, do dfs(idx)
	//time: graph n + graph/count l + DFS n*n = O(n*n) 
	//space: graph n*n + count n + result n = O(n*n)
	public int[] findOrderS2(int numCourses, int[][] prerequisites) {
		//corner cases
		if (numCourses <= 0) return new int[0];
		int[] result = new int[numCourses + 1]; //last digit keeps current assigned index
		if (prerequisites == null || prerequisites.length == 0) {
			for (int i = 0; i < numCourses; i++) {
				result[i] = i;
			}
			return Arrays.copyOf(result, result.length - 1);
		}
		
		ArrayList[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		int[] count = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			count[prerequisites[i][0]]++;
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}
		
		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (count[i] == 0 && visited[i] == false) {
				visited[i] = true;
				result[result[numCourses]] = i;
				result[numCourses]++;
				dfs(graph, count, i, result, visited);
			}
		}
		
		if (result[numCourses] == numCourses) return Arrays.copyOfRange(result, 0, numCourses);
		return new int[0];
	}
	
	private void dfs(ArrayList[] graph, int[] count, int course, int[] result, boolean[] visited) {
		int numCourses = count.length;
		if (result[numCourses] == numCourses) return;
		
		ArrayList<Integer> pres = graph[course];
		for (int i = 0; i < pres.size(); i++) {
			int p = pres.get(i);
			count[p]--;
			if (count[p] == 0 && visited[p] == false) {
				visited[p] = true;
				result[result[numCourses]] = p;
				result[numCourses]++;
				dfs(graph, count, p, result, visited);
			}
		}
	}
	
}
