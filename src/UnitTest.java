import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * A unit test is used to verify the correct operation of a single program
 * component. Usually more than one are required, but each one should verify
 * that given a known input, that component returns the expected output.
 *
 * @param <T1> The type of the input passed to the test
 * @param <T2> The type of the tests output
 */
public class UnitTest<T1, T2> {
	private Function<T1, T2> test;
	private BiPredicate<T2, T2> tester;
	private T1 input;
	private T2 expected;

	/**
	 * Create a new {@link UnitTest}
	 *
	 * @param input    The input that should be passed to the test
	 * @param expected The expected output of the test
	 * @param test     The function to run the test. It should accept a single
	 *                 parameter of type T1, and return a value of type T2. Usually
	 *                 it will be enough for this to be a lambda expression calling
	 *                 the function being tested.
	 * 
	 * @return The new UnitTest
	 */
	public UnitTest(T1 input, T2 expected, Function<T1, T2> test) {
		this.test = test;
		this.tester = (out, exp) -> out.equals(exp);
		this.input = input;
		this.expected = expected;
	}

	/**
	 * Create a new {@link UnitTest}
	 *
	 * @param input      The input that should be passed to the test
	 * @param expected   The expected output of the test
	 * @param test       The function to run the test. It should accept a single
	 *                   parameter of type T1, and return a value of type T2.
	 *                   Usually it will be enough for this to be a lambda
	 *                   expression calling the function being tested.
	 * @param comparator A function specifying how the real, and expected, outputs
	 *                   of test should be compared. Use this when T2 can not be
	 *                   compared using .equals.
	 * @return The new UnitTest
	 */
	public UnitTest(T1 input, T2 expected, Function<T1, T2> test, BiPredicate<T2, T2> comparator) {
		this.test = test;
		this.tester = comparator;
		this.input = input;
		this.expected = expected;
	}

	/**
	 * Create a new {@link UnitTest} for a component that takes no input. Note that
	 * even if the function being tested takes no input, the function passed to the
	 * constructor must take one. This is due to a limitation of the Java generics
	 * system that I have not had the time to work around yet.
	 *
	 * @param expected The expected output of the test
	 * @param test     The function to run the test
	 * @return The new UnitTest
	 */
	public UnitTest(T2 expected, Function<T1, T2> test) {
		this.test = test;
		this.tester = (out, exp) -> out.equals(exp);
		this.input = null;
		this.expected = expected;
	}

	/**
	 * Create a new {@link UnitTest} for a component that takes no input with a
	 * custom output comparator. Note that even if the function being tested takes
	 * no input, the function passed to the constructor must take one. This is due
	 * to a limitation of the Java generics system that I have not had the time to
	 * work around yet.
	 *
	 * @param expected   The expected output of the test
	 * @param test       The function to run the test
	 * @param comparator A function specifying how the real, and expected, outputs
	 *                   of test should be compared. Use this when T2 can not be
	 *                   compared using .equals.
	 * @return The new UnitTest
	 */
	public UnitTest(T2 expected, Function<T1, T2> test, BiPredicate<T2, T2> comparator) {
		this.test = test;
		this.tester = comparator;
		this.input = null;
		this.expected = expected;
	}

	/**
	 * Run the {@link UnitTest}
	 *
	 * @return true if the output of the test matches the expected output, false
	 *         otherwise
	 */
	public boolean run() {
		return this.tester.test(this.test.apply(this.input), this.expected);
	}
}
