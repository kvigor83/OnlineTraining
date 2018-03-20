package by.ihi.onlinetraining.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private long id;
    private String fio;
    private String login;
    private String password;
    private String email;
    private String role;
    private String status;

}
