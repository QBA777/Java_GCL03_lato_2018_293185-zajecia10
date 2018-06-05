package model;

public class SSA {
    public SSA(){

    }

    public SSA(String id, String name, String resolution, String size, String created) {
        this.id = id;
        this.name = name;
        this.resolution = resolution;
        this.size = size;
        this.created = created;
    }

    private String id;
    private String name;

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    private String resolution;
    private String size;
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}



