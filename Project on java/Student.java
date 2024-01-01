import java.io.Serializable;

public class Student implements Serializable {

    private String studentName;
    private String studentId;
    private String studentEmail;
    private String phoneNumber;
    private CourseType courseType;
    

    public Student(String studentName, String studentId, CourseType courseType, String studentEmail, String phoneNumber) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentEmail = studentEmail;
        this.phoneNumber = phoneNumber;
        this.courseType = courseType;
    }


    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentEmail(){
        return studentEmail;
    }

    public String getphoneNumber(){
        return phoneNumber;
    }

    public CourseType getCourseType() {
        return courseType;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                ", studentEmail" + studentEmail +
                ", courseType=" + courseType +
                '}';
    }
}