package stream;

public class SimpleDataStream3 {
    private long id;
    private String name;
    private boolean logged;

    public SimpleDataStream3(long id, String name, boolean logged) {
        this.id = id;
        this.name = name;
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    public String getName() {
        return name;
    }

    public void Print(){
        System.out.println("  id: "+id+", name: "+name+", logged: "+logged);
    }
}
