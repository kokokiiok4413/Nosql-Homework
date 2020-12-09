package com.bjtu.redis.Action;



import java.util.ArrayList;

public class action {
    public String actionName;
    public ArrayList<String> readCounters;
    public ArrayList<String> writeCounters;

    public action(){ }

    public action(String actionName)
    {

        this.actionName=actionName;
    }

    public void setActionName(String actionName)
    {
        this.actionName=actionName;
    }

    public void setReadCounters(ArrayList<String> readCounters)
    {
        this.readCounters = readCounters;
    }
    public void setWriteCounters(ArrayList<String> writeCounters)
    {
        this.writeCounters = writeCounters;
    }

}

