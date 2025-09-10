public class Student {
    // --- Fields ---
    private int age;                      // 18–30
    private int semester;                 // 1–10
    private int socioEconomicStratum;     // 1–6
    private int coursesCompleted;         // >= 0
    private int coursesPending;           // >= 0
    private String program;               // program name
    private int programId;                // unique id

    // --- Constructor ---
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

    // --- Getters ---
    public int getAge() { return age; }
    public int getSemester() { return semester; }
    public int getSocioEconomicStratum() { return socioEconomicStratum; }
    public int getCoursesCompleted() { return coursesCompleted; }
    public int getCoursesPending() { return coursesPending; }
    public String getProgram() { return program; }
    public int getProgramId() { return programId; }

    // --- toString for pretty printing ---
    @Override
    public String toString() {
        return "Student{" +
                "programId=" + programId +
                ", program='" + program + '\'' +
                ", age=" + age +
                ", semester=" + semester +
                ", stratum=" + socioEconomicStratum +
                ", completed=" + coursesCompleted +
                ", pending=" + coursesPending +
                '}';
    }
}
