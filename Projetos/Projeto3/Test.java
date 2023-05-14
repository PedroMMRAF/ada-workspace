import java.io.*;

/**
 * @author Miguel Valido 60477
 * @author Pedro Fernandes 60694
 */
public class Test {
    private static class TestCase {
        private String name;
        private String input;
        private String output;

        public TestCase(String name, String input, String output) {
            this.name = name;
            this.input = input;
            this.output = output;
        }

        public String getName() {
            return name;
        }

        public InputStream getInputStream() {
            return TestCase.class.getResourceAsStream(input);
        }

        public InputStream getOutputStream() {
            return TestCase.class.getResourceAsStream(output);
        }
    }

    private static TestCase[] testCases = generateCases(2, "tests/input%d.txt", "tests/output%d.txt");

    public static void main(String[] args) throws IOException {
        PrintStream stdout = System.out;

        for (TestCase test : testCases) {
            String actual, expected;
            boolean passed = false;

            try {
                expected = new String(test.getOutputStream().readAllBytes());
            } catch (Exception e) {
                System.out.printf("Test '%s' failed\n", test.getName());
                System.out.println(e);
                continue;
            }

            try {
                System.setIn(test.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                System.setOut(new PrintStream(baos));
                Main.main(args);

                actual = baos.toString();
                passed = actual.equals(expected);
            } catch (Exception e) {
                actual = e.toString();
            }

            System.setOut(stdout);

            if (passed) {
                System.out.printf("Test '%s' passed\n", test.getName());
            } else {
                System.out.printf("Test '%s' failed\n", test.getName());
                System.out.println("Expected:");
                System.out.println(expected);
                System.out.println();
                System.out.println("Actual:");
                System.out.println(actual);
            }
        }
    }

    private static TestCase[] generateCases(int cases, String inFormat, String outFormat) {
        TestCase[] testCases = new TestCase[cases];

        for (int i = 1; i <= cases; i++) {
            testCases[i - 1] = new TestCase("test" + i, String.format(inFormat, i), String.format(outFormat, i));
        }

        return testCases;
    }
}