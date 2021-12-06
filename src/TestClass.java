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
		Settings.initApplication();

		System.out.println(Settings.loadType);
		IDataLoader dataLoader = Settings.loadType == LOAD_TYPE.HARDCODAT ? new HardcodatDataManager() : new FileDataManager();
		Profesor[] profesors = dataLoader.createProfesorData();
		for (Profesor p :
				profesors) {
			System.out.println(p);
		}



		/* Scanner scanner = new Scanner(System.in);
		System.out.println("a =");
		String a = scanner.next();
		try {
			int aCaNrIntreg = Integer.parseInt(a);
			System.out.print("Am citit " + aCaNrIntreg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} */
		/* String propertyName = LOAD_TYPE.FILE.name();
		System.out.print(propertyName);
		ArrayList<Student> students = new ArrayList<>();
		try( Scanner scanner = new Scanner(new File("studenti.csv"))) {
			while(scanner.hasNextLine()) {
				try {
					Student s = readStudentForm(scanner.nextLine());
					students.add(s);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for( Student s: students) {
			System.out.println(s);
		}
		*/
	}
}
