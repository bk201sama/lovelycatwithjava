package org.eu.bk201sama.constant;

public enum LovelyCatResponseEventEnum {
    SendTextMsg("SendTextMsg");
    private String name;

    LovelyCatResponseEventEnum(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }

}
