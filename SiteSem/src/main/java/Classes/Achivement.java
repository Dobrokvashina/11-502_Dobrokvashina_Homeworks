package Classes;


public class Achivement {
    private University university;
    private Subject subject;
    private String type;
    private int id;
    private int points;

    public Achivement(Subject subject, String type, int id) {
        this.subject = subject;
        this.type = type;
        this.id = id;
    }

    public Achivement(University university, Subject subject, String type, int id, int points) {
        this.university = university;
        this.subject = subject;
        this.type = type;
        this.id = id;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public University getUniversity() {

        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
