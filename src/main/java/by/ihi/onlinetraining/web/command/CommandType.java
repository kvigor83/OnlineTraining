package by.ihi.onlinetraining.web.command;

import by.ihi.onlinetraining.web.command.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CommandType {
    HOME("home.jsp", "home", new DefaultCommand()),
    ABOUT("about.jsp", "about", new DefaultCommand()),
    LOGIN("login/login.jsp", "login",new LoginCommand()),
    REGISTRATION("registration/registration.jsp", "registration",  new RegistrationCommand()),
    SUBSCRIPTION_ADD("subscription/view.jsp", "addSubscription",  new SubscriptionAddCommand()),
    SUBSCRIPTION("subscription/view.jsp", "subscription",  new SubscriptionCommand()),
    SUBSCRIPTION_TUTOR("subscription/view-tutor.jsp", "subscriptionTutor",  new SubscriptionTutorCommand()),
    LOGOUT("login/login.jsp", "logout", new LogoutCommand()),
    TASK("task/edit.jsp", "task", new TaskCommand()),
    TASKS("task/view.jsp", "tasks", new TasksCommand()),
    TASK_EDIT("task/tasks.jsp", "editTask", new TaskEditCommand()),
    TASKS_EDIT_USER("task/tasks.jsp", "editTaskUser", new TaskEditUserCommand()),
    TASK_ADD("task/view.jsp", "addTask", new TaskAddCommand()),
    COURSE("course/edit.jsp", "course", new CourseCommand()),
    COURSES("course/view.jsp", "courses", new CoursesCommand()),
    COURSES_TUTOR("course/view-tutor.jsp", "courseTutor", new CourseTutorCommand()),
    COURSE_ADD("course/view-tutor.jsp", "addCourse", new CourseAddCommand()),
    COURSE_DELETE("course/view-tutor.jsp", "deleteCourse", new CourseDeleteCommand()),
    COURSE_EDIT("course/view-tutor.jsp", "editCourse", new CourseEditCommand()),
    USERS("profile/view-all.jsp", "users",  new UserCommand()),
    USERS_DELETE("profile/view-all.jsp", "userDelete",  new UserDeleteCommand()),
    USERS_SAVE("profile/users.jsp", "editUser",  new UserEditCommand()),
    PROFILE("profile/view.jsp", "profile",  new ProfileCommand()),
    ERROR("error/error.jsp", "error",  new DefaultCommand());

    private String pagePath;
    private String pageName;
    private Command command;

    public static CommandType getByCommandName(String page) {
        for (CommandType type : CommandType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return HOME;   // default
    }

}