package by.kastsiuchenka.onlinetraining.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Subscription {
    private long id;
    private String dateStop;
    private double avgMark;
    private String numberCompleted;
    private String linkedCourse;
    private String linkedStudent;
    private String Status;
    private String dateStart;
}







