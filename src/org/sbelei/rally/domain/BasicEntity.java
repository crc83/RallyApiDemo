package org.sbelei.rally.domain;

public class BasicEntity {
	
    public String name;
    public String id;
    public String ref;

    @Override
    public String toString() {
        return "\n entity id=" + id + "\tname=" + name + "";
    }

}
