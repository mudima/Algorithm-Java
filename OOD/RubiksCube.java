package OOD;


enum Color {
	Red, Yellow, Blue, White, Black, Orange
}
enum Direction {
	Up, Down, Left, Right
}

class Unit {
	Color color;
	public Unit(Color color) {
		this.color = color;
	}
}

class Face {
	private static final int SIZE = 3;
	int id;
	Unit[][] matrix;
	
	public Face(int id, Color color) {
		this.id = id;
		matrix = new Unit[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = new Unit(color);
			}
		}
	}
	
	public Unit[] getEntry(int index, Direction direction) {
		Unit[] result = new Unit[SIZE];
		if (direction == Direction.Left || direction == Direction.Right) {
			result = matrix[index];
		} else {
			for (int i = 0; i < SIZE; i++) {
				result[i] = matrix[i][index];
			}
		}
		return result;
	}

	
	public void changeEntry(int index, Direction direction, Unit[] replace) {
		if (direction == Direction.Left || direction == Direction.Right) {
			matrix[index] = replace;
		} else {
			for (int i = 0; i < SIZE; i++) {
				matrix[i][index] = replace[i];
			}
		}
	}
} 

public class RubiksCube {
	private static final int SIZE = 6;
	Face[] array = new Face[SIZE]; 
	
	public RubiksCube() {
		int i = 0;
		for (Color c : Color.values()) {
			array[i] = new Face(i, c);
			i++;
		}
	}
	public void rotation(int face, int index, Direction direction) {
		if (direction == Direction.Up) {
			Unit[] temp = array[0].getEntry(index, direction);
			Unit[] replace = array[4].getEntry(index, direction);
			array[0].changeEntry(index, direction, replace);
			replace = array[2].getEntry(index, direction);
			array[4].changeEntry(index, direction, replace);
			replace = array[5].getEntry(index, direction);
			array[2].changeEntry(index, direction, replace);
			array[5].changeEntry(index, direction, temp);
		} else if (direction == Direction.Down) {
			Unit[] temp = array[0].getEntry(index, direction);
			Unit[] replace = array[2].getEntry(index, direction);
			array[5].changeEntry(index, direction, replace);
			replace = array[4].getEntry(index, direction);
			array[2].changeEntry(index, direction, replace);
			replace = array[5].getEntry(index, direction);
			array[4].changeEntry(index, direction, replace);
			array[0].changeEntry(index, direction, temp);
		} else if (direction == Direction.Left) {
			Unit[] temp = array[2].getEntry(index, direction);
			Unit[] replace = array[3].getEntry(index, direction);
			array[2].changeEntry(index, direction, replace);
			replace = array[0].getEntry(index, direction);
			array[3].changeEntry(index, direction, replace);
			replace = array[1].getEntry(index, direction);
			array[0].changeEntry(index, direction, replace);
			array[1].changeEntry(index, direction, temp);
		} else {
			Unit[] temp = array[0].getEntry(index, direction);
			Unit[] replace = array[3].getEntry(index, direction);
			array[0].changeEntry(index, direction, replace);
			replace = array[2].getEntry(index, direction);
			array[3].changeEntry(index, direction, replace);
			replace = array[1].getEntry(index, direction);
			array[2].changeEntry(index, direction, replace);
			array[1].changeEntry(index, direction, temp);
		}
	}
	
}
