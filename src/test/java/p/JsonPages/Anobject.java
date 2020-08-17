package p.JsonPages;

import java.util.ArrayList;

public class Anobject {
    private String whoa;
    ArrayList<Object> anarray = new ArrayList<Object>();
    private String more;


    // Getter Methods

    public String getWhoa() {
        return whoa;
    }

    public ArrayList<Object> getAnarray() { return anarray; }

    public String getMore() {
        return more;
    }

    // Setter Methods

    public void setWhoa(String whoa) {
        this.whoa = whoa;
    }

    public void setMore(String more) {
        this.more = more;
    }
}