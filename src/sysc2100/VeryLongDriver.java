package sysc2100;
import java.io.*;

import java.util.*;

public class VeryLongDriver {
	protected BufferedReader fileReader;

	protected PrintWriter fileWriter;

	public PrintWriter openFiles() {
		final String IN_FILE_PROMPT = "\n\nPlease enter the path for the input file: ";

		final String OUT_FILE_PROMPT = "\n\nPlease enter the path for the output file: ";

		BufferedReader keyboardReader = new BufferedReader(
				new InputStreamReader(System.in));

		String inFilePath, outFilePath;

		boolean pathsOK = false;

		while (!pathsOK) {
			try {
				System.out.print(IN_FILE_PROMPT);
				inFilePath = keyboardReader.readLine();
				fileReader = new BufferedReader(new FileReader("/Users/Ryan/Dropbox/Eclipse/workspace/VeryLongInt/src/sysc2100/in.txt"));
				System.out.print(OUT_FILE_PROMPT);
				outFilePath = keyboardReader.readLine();
				fileWriter = new PrintWriter(new FileWriter("/Users/Ryan/Dropbox/Eclipse/workspace/VeryLongInt/src/sysc2100/out.txt"));
				pathsOK = true;
			} // try
			catch (IOException e) {
				System.out.println(e);
			} // catch I/O exception
		} // while
		return fileWriter;
	} // method openFiles

	public void testVeryLongIntMethods() {
		final String LINE_MESSAGE = "The line is: ";

		final String STRING_CONSTRUCTOR = "VeryLongInt";

		final String TO_STRING = "toString";

		final String ADD = "add";

		final String MULTIPLY = "multiply";

		final String FIBONACCI = "fibonacci";

		final String FACTORIAL = "factorial";

		final String VERY_LONG_MESSAGE = "Here is the VeryLongInt: ";

		final String FIBONACCI_MESSAGE = "Here is the Fibonacci: ";

		final String FACTORIAL_MESSAGE = "Here is the Factorial: ";

		final String BAD_METHOD = "The line entered does not represent a legal method.";

		StringTokenizer tokens;

		String line, method, argument;

		VeryLongInt veryLong = null, otherVeryLong;

		while (true) {
			try {
				line = fileReader.readLine();
				if (line == null)
					break;
				fileWriter.println();
				fileWriter.println(LINE_MESSAGE + line);
				tokens = new StringTokenizer(line);
				method = tokens.nextToken();
				if (method.equals(STRING_CONSTRUCTOR)) {
					if (tokens.hasMoreTokens()) {
						argument = tokens.nextToken();
						veryLong = new VeryLongInt(argument);
					} // if more tokens
					else
						veryLong = new VeryLongInt(null);
				} // string-parameter constructor
				else if (method.equals(TO_STRING)) {
				} // toString method
				else if (method.equals(ADD)) {
					if (tokens.hasMoreTokens()) {
						argument = tokens.nextToken();
						otherVeryLong = new VeryLongInt(argument);
						veryLong.add(otherVeryLong);
					} // if more tokens
					else
						veryLong = new VeryLongInt(null);
				} // add method
				else if (method.equals(MULTIPLY)) {
					if (tokens.hasMoreTokens()) {
						argument = tokens.nextToken();
						otherVeryLong = new VeryLongInt(argument);
						veryLong.multiply(otherVeryLong);
					} // if more tokens
					else
						veryLong = new VeryLongInt(null);
				} // multiply method
				else if (method.equals(FIBONACCI)) {
					argument = tokens.nextToken();
					veryLong.fibonacci(Integer.parseInt(argument));
					fileWriter.println(FIBONACCI_MESSAGE + veryLong.digits);
				} // Fibonacci
				else if (method.equals(FACTORIAL)) {
					argument = tokens.nextToken();
					veryLong.factorial(Integer.parseInt(argument));
					fileWriter.println(FACTORIAL_MESSAGE + veryLong.digits);
				} // Fibonacci
				else
					fileWriter.println(BAD_METHOD);
				fileWriter.println(VERY_LONG_MESSAGE + veryLong);
			} // try
			catch (IOException e) {
				fileWriter.println(e);
			} // catch IOException
			catch (NullPointerException e) {
				fileWriter.println(e);
			} // catch NoSuchElementException
		} // while
	} // method testVeryLongIntMethods

} // class VeryLongDriver
