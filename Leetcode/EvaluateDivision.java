package Leetcode;

import java.util.HashMap;

public class EvaluateDivision {
	private class V {
		public String name;
		public double val;
		public V parent;
		private int size;
		
		public V(String name) {
			this.name = name;
			this.parent = this;
			this.val = 1.0;
			this.size = 1;
		}
	}
	
	private boolean find(V v1, V v2) {
		return root(v1) == root(v2);
	}
	
	private void union(V v1, V v2, double d) {
		V root1 = root(v1), root2 = root(v2);
		if (root1.size < root2.size) {
			root1.parent = root2;
			root2.size += root1.size;
			root1.val = v2.val / (v1.val * d);
		} else {
			root2.parent = root1;
			root1.size += root2.size;
			root2.val = (v1.val * d) / v2.val;
		}
	}
	
	private V root(V v) {
		V cur = v;
		double d = 1.0;
		while (v.parent != v) {
			v.val = v.parent.val * v.val;
			d *= v.val;
			v.parent = v.parent.parent;
			v = v.parent;
		}
		cur.parent = v;
		cur.val = d;
		return v;
	}
	
	private double division(V v1, V v2) {
		return v2.val / v1.val;
	}
	
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		int len = equations.length, lenQ = queries.length;
		double[] res = new double[lenQ];
		HashMap<String, V> map = new HashMap<>();
		
		for (int i = 0; i < len; i++) {
			String[] equa = equations[i];
			String s1 = equa[0], s2 = equa[1];
			double val = values[i];
			
			if (!map.containsKey(s1)) map.put(s1, new V(s1));
			if (!map.containsKey(s2)) map.put(s2, new V(s2));
			
			V v1 = map.get(s1);
			V v2 = map.get(s2);
			if (!find(v1, v2)) {
				union(v1, v2, val);
			}
		}
		
		for (int i = 0; i < lenQ; i++) {
			String[] query = queries[i];
			String s1 = query[0], s2 = query[1];
			
			if (!map.containsKey(s1) || !map.containsKey(s2)) {
				res[i] = -1;
			} else {
				V v1 = map.get(s1), v2 = map.get(s2);
				if (! find(v1, v2)) {
					res[i] = -1;
				} else {
					res[i] = division(v1, v2);
				}
			}
		}
		return res;
	}
}
