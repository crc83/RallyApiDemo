package org.sbelei.rally.domain;

import java.util.ArrayList;
import java.util.List;

public class Workspace {
    
    public String name;
    public String id;
    public String ref;
    public String description;
    public String notes;
    public List<Project> projects;
    
    public Workspace() {
        projects = new ArrayList<>();
    }
}
