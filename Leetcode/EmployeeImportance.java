package Leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EmployeeImportance {
	//time: find n * each employee
	public int getImportance(List<Employee> employees, int id) {
		if (employees == null || employees.size() == 0) return 0;
		
		int totalImp = 0;
		Queue<Employee> queue = new LinkedList<>();
		if (find(employees, id) != null) queue.offer(find(employees, id));
		while (!queue.isEmpty()) {
			Employee cur = queue.poll();
			totalImp += cur.importance;
			for (int i = 0; i < cur.subordinates.size(); i++) {
				Employee subo = find(employees, cur.subordinates.get(i));
				if (subo != null) queue.offer(subo);
			}
		}
		return totalImp;
	}
	
	private Employee find(List<Employee> employees, int id) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).id == id) return employees.get(i);
		}
		return null;
	}
	
	
	
	
	
	class Employee {
		public int id;
		public int importance;
		public List<Integer> subordinates;
		
		public Employee(int id, int imp) {
			this.id = id;
			this.importance = imp;
			subordinates = new LinkedList<Integer>();
		}
	}
}
