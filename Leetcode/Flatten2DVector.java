package Leetcode;

import java.util.Iterator;
import java.util.List;

public class Flatten2DVector implements Iterator<Integer>{
	//S1: using index of 2d vetor
	 private Iterator<List<Integer>> i;
	 private Iterator<Integer> j;

	 public Flatten2DVector(List<List<Integer>> vec2d) {
		 i = vec2d.iterator();
	 }

	 public Integer next() {
		 hasNext();
		 return j.next();
	 }

	 public boolean hasNext() {
		 while ((j == null || !j.hasNext()) && i.hasNext()){
			 j = i.next().iterator();
		 }
	     return j != null && j.hasNext();
	 }
	 
	 //S2: only use list.iterator(), int.iterator()
	 private int indexList, indexEle;
	 private List<List<Integer>> vec; 
	    
	 public Vector2D(List<List<Integer>> vec2d) {
		 indexList = 0;
		 indexEle = 0;
		 vec = vec2d;
	 }

	 public int next() {
		 return vec.get(indexList).get(indexEle++);
	 }

	 public boolean hasNext() {
		 while(indexList < vec.size()){
			 if(indexEle < vec.get(indexList).size())
				 return true;
		 	 } else {
		 		indexList++;
		 		indexEle = 0;
		 	 }
	     }
	     return false;
	  }
}
