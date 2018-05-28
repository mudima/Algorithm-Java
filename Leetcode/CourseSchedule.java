package Leetcode;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
	//S1: BFS
	//arraylist[] graph -> graph[i] is all prerequisites for course i
	//int[] degree -> degree[i] is how many courses need course i to be a prerequisite
	//				  if degree[i] == 0, means course i is highest degree for current
	//
	//time: O(n)
	//space: graph n*n + degree n = O(n*n)
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0) return true;
		
		ArrayList[] graph = new ArrayList[numCourses];
		int[] degree = new int[numCourses];
		Queue<Integer> queue = new LinkedList<Integer>();
		int count = 0;
		
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][0]].add(prerequisites[i][1]);
			degree[prerequisites[i][1]]++;
		}
		
		for (int i = 0; i < degree.length; i++) {
			if (degree[i] == 0) {
				queue.add(i);
				count++;
			}
			
		}
		//only the highest level courses can go in queue, out of queue means can be token.
		while (!queue.isEmpty()) {
			int course = (int) queue.poll();
			for (int i = 0; i < graph[course].size(); i++) {
				int pre = (int) graph[course].get(i);
				degree[pre]--;
				if (degree[pre] == 0) {
					queue.add(pre);
					count++;
				}
			}
		}
		if (count == numCourses) return true;
		return false;
	}
	
	//S2: DFS
	//build up graph as in BFS
	//boolean[] visited -> back tracing to mark if has circle
	//
	//time: O(n^n), worst case is each course need other n-1 courses as prerequisites, none can be token.
	//space: graph n*n + visited n = O(n * n)
	public boolean canFinishS2(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0) return true;
		
		ArrayList[] graph = new ArrayList[numCourses];
		boolean[] visited = new boolean[numCourses];
		
		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][0]].add(prerequisites[i][1]);
		}
		
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
		if (visited[course]) {
			return false;
		}
		
		visited[course] = true;
		ArrayList<Integer> pres = graph[course];
		for (int i = 0; i < pres.size(); i++) {
			if (!dfs(graph, visited, pres.get(i))) {
				return false;
			}
		}
		//back tracing
		visited[course] = false;
		return true;
	}
}
