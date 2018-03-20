package by.ihi.onlinetraining.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Task implements Serializable {
    private long id;
    private String title;
    private String body;
    private String startTime;
    private String endTime;
    private String answer;
    private int mark;
    private String review;
    private String status;
}
