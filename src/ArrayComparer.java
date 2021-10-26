/**
 * A utility class containing functions to compare {@link java.util.Arrays}.
 */
public class ArrayComparer {
	public static void main(String[] args) {
		TestSuite tests = new TestSuite("compare_arrays", new UnitTest[] {
				new UnitTest<double[][], Boolean>(new double[][] { { 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0 } }, true,
						(a) -> compare(a[0], a[1])),
				new UnitTest<double[][], Boolean>(new double[][] { { 1.0, 2.0, 3.0 }, { 1.0, 2.0 } }, false,
						(a) -> compare(a[0], a[1])),
				new UnitTest<double[][], Boolean>(new double[][] { { 1.0, 2.0, 3.0 }, { 3.0, 2.0, 1.0 } }, false,
						(a) -> compare(a[0], a[1])),
				new UnitTest<int[][], Boolean>(new int[][] { { 1, 2, 3 }, { 1, 2, 3 } }, true, (a) -> compare(a[0], a[1])),
				new UnitTest<int[][], Boolean>(new int[][] { { 1, 2, 3 }, { 1, 2 } }, false, (a) -> compare(a[0], a[1])),
				new UnitTest<int[][], Boolean>(new int[][] { { 1, 2, 3 }, { 3, 2, 1 } }, false, (a) -> compare(a[0], a[1])) });

		System.exit(tests.run() ? 0 : 1);
	}

	/**
	 * @param a the first array
	 * @param b the second array
	 * @return true if the arrays contain the same values, in the same order, false
	 *         otherwise
	 */
	public static boolean compare(double[] a, double[] b) {
		// To avoid code duplication simply call the more general overload with a default value for epsilon
		return compare(a, b, 0.0000001d);
	}

	/**
	 * @param a       the first array
	 * @param b       the second array
	 * @param epsilon the precision to which the floats should be compared
	 * @return true if the arrays contain the same values, in the same order, false
	 *         otherwise
	 */
	public static boolean compare(double[] a, double[] b, double epsilon) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; ++i) {
			if (Math.abs(a[i] - b[i]) > epsilon) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param a the first array
	 * @param b the second array
	 * @return true if the arrays contain the same values, in the same order, false
	 *         otherwise
	 */
	public static boolean compare(int[] a, int[] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; ++i) {
			if (a[i] != b[i]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param a the first array
	 * @param b the second array
	 * @return true if the arrays contain the same values, in the same order, false
	 *         otherwise
	 */
	public static boolean compare(int[][] a, int[][] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; ++i) {
			if (!compare(a[i], b[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param a the first array
	 * @param b the second array
	 * @return true if the arrays contain the same values, in the same order, false
	 *         otherwise
	 */
	public static boolean compare(double[][] a, double[][] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; ++i) {
			System.out.printf("%s%n%s%n%n", java.util.Arrays.toString(a[i]), java.util.Arrays.toString(b[i]));
			if (!compare(a[i], b[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param a       the first array
	 * @param b       the second array
	 * @param epsilon the precision to which the doubles should be compared
	 * @return true if the arrays contain the same values, in the same order, false
	 *         otherwise
	 */
	public static boolean compare(double[][] a, double[][] b, double epsilon) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; ++i) {
			if (!compare(a[i], b[i], epsilon)) {
				return false;
			}
		}

		return true;
	}

	public static <T extends Comparable<? super T>> boolean compare(T[] a, T[] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; ++i) {
			if (a[i] == null || b[i] == null || a[i].compareTo(b[i]) != 0) {
				return false;
			}
		}

		return true;
	}
}
