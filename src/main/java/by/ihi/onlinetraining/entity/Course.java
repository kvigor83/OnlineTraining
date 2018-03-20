package by.kastsiuchenka.onlinetraining.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Course implements Serializable {
    private long id;
    private String courseName;
    private int hours;
    private String description;
    private int cost;
}
