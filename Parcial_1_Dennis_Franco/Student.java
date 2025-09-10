package Parcial_1_Dennis_Franco;

// Holds the data of one student
public class Student {
    private int age;
    private int semester;
    private int socioEconomicStratum;
    private int coursesCompleted;
    private int coursesPending;
    private String program;
    private int programId;

    // Basic constructor: set all fields
    public Student(int age, int semester, int socioEconomicStratum,
                   int coursesCompleted, int coursesPending,
                   String program, int programId) {
        this.age = age;
        this.semester = semester;
        this.socioEconomicStratum = socioEconomicStratum;
        this.coursesCompleted = coursesCompleted;
        this.coursesPending = coursesPending;
        this.program = program;
        this.programId = programId;
    }

    // Getters (read-only access)
    public int getAge() { return age; }
    public int getSemester() { return semester; }
    public int getSocioEconomicStratum() { return socioEconomicStratum; }
    public int getCoursesCompleted() { return coursesCompleted; }
    public int getCoursesPending() { return coursesPending; }
    public String getProgram() { return program; }
    public int getProgramId() { return programId; }

    // Text format for easy printing
    @Override
    public String toString() {
        return String.format(
            "Student{age=%d, semester=%d, stratum=%d, completed=%d, pending=%d, program='%s', programId=%d}",
            age, semester, socioEconomicStratum, coursesCompleted, coursesPending, program, programId
        );
    }

    // Quick print helper
    public void print() {
        System.out.println(this.toString());
    }
}
