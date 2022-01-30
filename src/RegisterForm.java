import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {

    public JPanel getMainPanel() {
        return rmainPanel;
    }

    public RegisterForm() {

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (username.getText().equals("") || new String(userpassword.getPassword()).equals("") || uNume.getText().equals("") || uPrenume.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Campurile nu sunt completate!");
                    } else if (!studentRadioButton.isSelected() && !profesorRadioButton.isSelected())
                        JOptionPane.showMessageDialog(null, "Statutul userului nu a fost selectat");
                    else {
                        Application.getInstance().register(uNume.getText(), uPrenume.getText(), username.getText(), new String(userpassword.getPassword()), acctype);
                        JOptionPane.showMessageDialog(null, "Ai reusit sa te inregistrezi, " + username.getText() + " !");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        profesorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (profesorRadioButton.isSelected()) {
                    acctype = UserAccountType.TEACHER;
                }
            }
        });
        studentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentRadioButton.isSelected()) {
                    acctype = UserAccountType.STUDENT;
                }
            }
        });
    }

    private UserAccountType acctype;
    private JTextField username;
    private JButton registerButton;
    private JPanel rmainPanel;
    private JRadioButton studentRadioButton;
    private JRadioButton profesorRadioButton;
    private JPasswordField userpassword;
    private JTextField uNume;
    private JTextField uPrenume;
}
