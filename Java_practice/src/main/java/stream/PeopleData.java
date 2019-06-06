package stream;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class PeopleData {
    private String firstName;
    private String lastname;
    private LocalDateTime createdAt;
    private boolean logged;

    public PeopleData(String firstName, String lastname, boolean logged, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.createdAt = createdAt;
        this.logged = logged;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isLogged() {
        return logged;
    }

    public void print(){
        System.out.println(" Firstname: "+firstName+", lastname: "+lastname+", created at: "+createdAt.getMonthValue()
                +"/"+createdAt.getDayOfMonth()+"/"+createdAt.getYear()+" "+createdAt.getHour()+":"+createdAt.getMinute()
                +":"+createdAt.getSecond()+" logged: "+logged);
    }

}

