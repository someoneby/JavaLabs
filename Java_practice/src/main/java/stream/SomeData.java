package stream;

import java.time.LocalDateTime;

public class SomeData implements Comparable<SomeData> {
    private long id;
    private String name;
    private LocalDateTime date;

    public SomeData(long id, String name, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public void print(){
        System.out.println("\tid: "+id+", name: "+name+", date: "+date.getYear()+"-"+date.getMonthValue()
                +"-"+date.getDayOfMonth()+"\t "+date.getHour()+":"+date.getMinute());
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(SomeData another) {
        if(date.isBefore(another.date) || id>another.id || name.compareTo(another.name)==-1)
            return -1;
        return 0;
    }

}
