import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Application {
    private static Application single_instance = null;
    private List<User> userList = new ArrayList<>();
    private boolean RegistryToken;
    public User currentUser = null;

    static Application getInstance() {
        if ( single_instance == null) {
            single_instance = new Application();
        }
        return  single_instance;
    }

    private Application() {this.initUsers();}

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.userList = (ArrayList<User>)decoder.readObject();
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getRegistryToken(){ return RegistryToken; }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }

    public void register(String nume,String prenume,String username, String userpassword,UserAccountType accountType ) throws Exception{
        FileDataManager fdm=new FileDataManager();
        RegistryToken=false;
        Student[] students;
        Profesor[] profesori;
        try{
            if(accountType.equals(UserAccountType.STUDENT)){
                students=fdm.createStudentsData();
                for(Student stud:students){
                    if(RegistryToken)
                        break;
                    if(stud.nume.equals(nume) && stud.prenume.equals(prenume)){
                        try (FileOutputStream fos = new FileOutputStream("users.xml")) {
                            XMLEncoder encoder = new XMLEncoder(fos);
                            User newUser=new User(username,userpassword,new StudentStrategy(new Student(nume,prenume, stud.grupa)));
                            if(!userList.contains(newUser)){
                                encoder.writeObject(newUser);
                                RegistryToken=true;
                            }else throw new Exception("Utilizatorul deja exista");
                            encoder.close();
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
            else if(accountType.equals(UserAccountType.TEACHER)) {
                profesori = fdm.createProfesorData();
                for(Profesor prof:profesori){
                    if(RegistryToken)
                        break;
                    if(prof.nume.equals(nume) && prof.prenume.equals(prenume)){
                        try (FileOutputStream fos = new FileOutputStream("users.xml")) {
                            XMLEncoder encoder = new XMLEncoder(fos);
                            User newUser=new User(username,userpassword,new TeacherStrategy(new Profesor(nume,prenume)));
                            if(!userList.contains(newUser)){
                                encoder.writeObject(newUser);
                                RegistryToken=true;
                            }else throw new Exception("Utilizatorul deja exista");
                            encoder.close();
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
