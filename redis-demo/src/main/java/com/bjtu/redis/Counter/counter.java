package com.bjtu.redis.Counter;


public class counter {
    public String counterName;
    public String type;
    public String key;
    public int valueField;

    public counter(){
    }

    public counter(String counterName){
        this.counterName=counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValueField(int valueField) {
        this.valueField = valueField;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counterName='" + counterName + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", valueField=" + valueField +
                '}';
    }
}
