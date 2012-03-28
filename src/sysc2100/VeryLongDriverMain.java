package sysc2100;
import java.io.*;

public class VeryLongDriverMain {
	public static void main(String[] args) {
		PrintWriter writer = null;
		try {
			VeryLongDriver driver = new VeryLongDriver();
			writer = driver.openFiles();
			driver.testVeryLongIntMethods();
		} // try
		finally {
			writer.close();
		} // finally
	} // method main
} // class VeryLongDriverMain

