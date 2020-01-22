package redisson;


import java.io.Externalizable;
import java.io.Serializable;

public class CustomData implements Serializable {

    int id;
    String name;
    CustomDetail detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomDetail getDetail() {
        return detail;
    }

    public void setDetail(CustomDetail detail) {
        this.detail = detail;
    }

    public CustomData(int id, String name, CustomDetail detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }
}
