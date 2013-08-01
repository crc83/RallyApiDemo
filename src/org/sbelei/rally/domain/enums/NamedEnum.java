package org.sbelei.rally.domain.enums;

public enum NamedEnum {

    NOP("");

    private String name;

    NamedEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
