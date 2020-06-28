package l.page_object;

public class JavaInit {
    private String vb; // not initialized
    private Integer i; // String (or any object) has default value null

    public boolean getStr() {
        return vb.contains("class");
    }

    public void getInt() {
        i.intValue();
    }

    public static void main(String[] args) {
        var ji = new JavaInit();
        System.out.println(ji.i);
        ji.getInt();
        System.out.println("OK");

    }
}
