public class ConsoleDisplay implements IDisplayManager {
    @Override
    public void displayStudents(Student[] students) {
        for (Student s :
                students) {
            System.out.println(s);
        }
    }

    @Override
    public void displayTeachers(Profesor[] profesors) {

        for (Profesor s :
                profesors) {
            System.out.println(s);
        }
    }

    @Override
    public void displayCourses(Curs[] cursuri) {

        for (Curs s :
                cursuri) {
            System.out.println(s);
        }
    }
}
