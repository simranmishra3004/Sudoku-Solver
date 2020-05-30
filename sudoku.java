package Java;

public class sudoku {

	public static final int size = 9;
	public static int[][] matrix = { { 6, 5, 0, 8, 7, 3, 0, 9, 0 }, { 0, 0, 3, 2, 5, 0, 0, 0, 8 },
			{ 9, 8, 0, 1, 0, 4, 3, 5, 7 }, { 1, 0, 5, 0, 0, 0, 0, 0, 0 }, { 4, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 5, 0, 3 }, { 5, 7, 8, 3, 0, 1, 0, 2, 6 }, { 2, 0, 0, 0, 4, 8, 9, 0, 0 },
			{ 0, 9, 0, 6, 2, 5, 0, 8, 1 } };

	// function to print SuDoKu
	public static void printSudoku() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println("");
		}
	}

	// Check the cells which do not have a value assigned
	public static int[] number_unassigned(int row, int col) {
		int x = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j] == 0) {
					row = i;
					col = j;
					x = 1;
					int[] a = { x, row, col };
					return a;
				}
			}

		}

		int[] a = { x, -1, -1 };
		return a;
	}

	// Check if the value can be inserted in cell '
	// by checking if the same vale is present in
	// row/column/sub matrix
	public static boolean is_safe(int n, int r, int c) {
		// check in row
		for (int i = 0; i < size; i++) {
			if (matrix[r][i] == n)
				return false;
		}
		// check in col
		for (int i = 0; i < size; i++) {
			if (matrix[i][c] == n)
				return false;
		}

		// Check in sub matrix
		int rownum = (r / 3) * 3;
		int colnum = (c / 3) * 3;

		for (int i = rownum; i < (rownum + 3); i++) {
			for (int j = colnum; j < (colnum + 3); j++) {
				if (matrix[i][j] == n)
					return false;
			}
		}
		return true;
	}

	// Solve SuDoKu
	public static boolean solveSudoKu() {

		int row = 0;
		int col = 0;

		int[] a = number_unassigned(row, col);

		if (a[0] == 0) {
			return true;
		}

		row = a[1];
		col = a[2];

		for (int i = 1; i <= size; i++) {
			if (is_safe(i, row, col)) {
				matrix[row][col] = i;

				if (solveSudoKu()) {
					return true;
				}

				matrix[row][col] = 0;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		if (solveSudoKu()) {
			System.out.println("");
			printSudoku();
		} else
			System.out.println("No Solution ");
	}
}
