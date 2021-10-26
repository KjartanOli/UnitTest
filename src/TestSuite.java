/**
 * A container for a suite of related {@link UnitTest}, allowing those tests to be
 * grouped together, and run all at once.
 */
public class TestSuite {
	private final String label;
	private final UnitTest[] tests;

	/**
	 * Create a new TestSuite to manage a set of tests, and group them together
	 * under a label.
	 *
	 * @param label The label to group the tests under.
	 * @param tests The unit tests for this test suite
	 */
	public TestSuite(String label, UnitTest[] tests) {
		this.label = label;
		this.tests = tests;
	}

	/**
	 * Run all tests in this TestSuite
	 *
	 * @return true if all tests in the suite pass, false otherwise
	 */
	public boolean run() {
		int passed = 0;
		int test_num = 0;
		System.out.printf("Running test suite: %s%n", this.label);
		for (UnitTest test : this.tests) {
			boolean pass = test.run();
			System.out.printf("  Test %d: %s%n", ++test_num, pass ? "Passed" : "Failed");
			if (pass) {
				++passed;
			}
		}

		System.out.printf("Test suite: %s complete. Passed: %d, Failed: %d, Total: %d%n", this.label, passed,
				this.tests.length - passed, this.tests.length);

		return passed == this.tests.length;
	}
}
