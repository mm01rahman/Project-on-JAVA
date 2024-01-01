import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField idField;
    private JTextField emailField;
    private JTextField phoneField;
    private JComboBox<CourseType> courseTypeComboBox;

    public MainGUI() {

        frame = new JFrame();
        frame.setBounds(900, 100, 100, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack(); 
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);

        Font font = new Font("Arial", Font.BOLD,14);


        ImageIcon icon = new ImageIcon("logo.png");
        JLabel img = new JLabel(icon);
        img.setBounds(50, 2, 280, 80);
        frame.getContentPane().add(img);

        

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(29, 100, 120, 14);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.BLACK);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 97, 200, 28);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(29, 140, 120, 14);
        idLabel.setFont(font);
        idLabel.setForeground(Color.BLACK);
        frame.getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 137, 200, 28);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(29, 180, 120, 14);
        emailLabel.setFont(font);
        emailLabel.setForeground(Color.BLACK);
        frame.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 177, 200, 28);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(29, 220, 120, 14);
        phoneLabel.setFont(font);
        phoneLabel.setForeground(Color.BLACK);
        frame.getContentPane().add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 217, 200, 28);
        frame.getContentPane().add(phoneField);
        phoneField.setColumns(10);

        JLabel courseLabel = new JLabel("Course Type:");
        courseLabel.setBounds(29, 260, 120, 14);
        courseLabel.setFont(font);
        courseLabel.setForeground(Color.BLACK);
        frame.getContentPane().add(courseLabel);

        courseTypeComboBox = new JComboBox<>(CourseType.values());
        courseTypeComboBox.setBounds(150, 257, 200, 28);
        frame.getContentPane().add(courseTypeComboBox);

        

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(29, 300, 150, 25);
        btnSave.setBackground(Color.BLUE);
        btnSave.setForeground(Color.WHITE);
        frame.getContentPane().add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        JButton btnShow = new JButton("Show");
        btnShow.setBounds(200, 300, 150, 25);
        btnShow.setBackground(Color.DARK_GRAY);
        btnShow.setForeground(Color.WHITE);
        frame.getContentPane().add(btnShow);
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveFromFile();
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(110, 330, 150, 23);
        btnClear.setBackground(Color.RED);
        btnClear.setForeground(Color.WHITE);
        frame.getContentPane().add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearStudentDetails() ;
            }
        });
    }


    private void saveToFile() 
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.ser"))) 
        {
            Student student = new Student(nameField.getText(), idField.getText(), (CourseType) courseTypeComboBox.getSelectedItem(), emailField.getText(), phoneField.getText());
            out.writeObject(student);
            JOptionPane.showMessageDialog(frame, "Student data saved to file successfully.");
        } 
        catch (IOException | NumberFormatException ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error saving data to file." + ex.getMessage());
        }
    }

    private void retrieveFromFile() 
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.ser"))) 
        {
            Student savedStudent = (Student) in.readObject();
            displayStudentDetails(savedStudent);
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error retrieving data from file." + ex.getMessage());
        }
    }

    private void displayStudentDetails(Student student) 
    {
        nameField.setText(student.getStudentName());
        idField.setText(student.getStudentId());
        emailField.setText(student.getStudentEmail());
        phoneField.setText(student.getphoneNumber());
        courseTypeComboBox.setSelectedItem(student.getCourseType());
        
    }

    private void clearStudentDetails() 
    {
        nameField.setText("");
        idField.setText("");
        emailField.setText("");
        phoneField.setText("");
        courseTypeComboBox.setSelectedItem("MAJOR");
        
    }

    public static void main(String[] args) 
    {
        MainGUI window = new MainGUI();
        window.frame.setVisible(true);
    }
}
