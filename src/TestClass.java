import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

enum LOAD_TYPE {
	HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
	CONSOLA, FISIER, GUI
}

public class TestClass {
	public static Student readStudentForm(String line) throws Exception {
		// Ana, are, mere - String
		// String[] substringuri = line.split(","); // -> ["ana", "are", "mere"];
		try( Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			ArrayList<String> properties = new ArrayList<String>();
			while (rowScanner.hasNext()) {
				properties.add(rowScanner.next());
			}
			return new Student(properties);
		}
	}

	public static void main(String[] args) {
		
	}
}
