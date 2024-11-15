package Classes;

import java.util.List;

public abstract class Evenement implements Cloneable {

    private long date;

    public Evenement(long date){
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public abstract void execute();

    @Override
    public Evenement clone() throws CloneNotSupportedException {
        return (Evenement) super.clone();
    }

    public Evenement cloneWith(List<Robot> newRobots) {
        return this;
    }
}
