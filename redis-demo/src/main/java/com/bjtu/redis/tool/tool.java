package com.bjtu.redis.tool;




import com.bjtu.redis.Action.actionjson;
import com.bjtu.redis.Counter.counter;
import com.bjtu.redis.Counter.counterjson;
import com.bjtu.redis.je.Jedisutil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class tool {

    com.bjtu.redis.Action.action action;
    private final ArrayList<String> readCounter;
    private final ArrayList<String> writeCounter;
    private final ArrayList<counter> readCounterObj;
    private final ArrayList<counter> writeCounterObj;

    public tool(actionjson jsh){
        this.action=jsh.getAction();
        readCounter=jsh.getReadCounter();
        writeCounter=jsh.getWriteCounter();
        readCounterObj = new ArrayList<counter>();
        writeCounterObj = new ArrayList<counter>();
        initCounters();
    }

    public void solver(){

        for(counter counter : readCounterObj){
            switch (counter.type){
                case "num":
                    System.out.println("The Value Of "+counter.key+" Is "+Jedisutil.getValueNum(counter.key));
                    break;
                case "hash":
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
                    System.out.print("begin time(yyyy-MM-dd HH)>>");
                    Scanner ms = new Scanner(System.in);
                    String begin = ms.nextLine();
                    System.out.print("end time(yyyy-MM-dd HH)>>");
                    Scanner ms2 = new Scanner(System.in);
                    String end = ms2.nextLine();
                    try {
                        Date from = format.parse(begin);
                        Date to = format.parse(end);
                        Map<String,String> map = Jedisutil.getHashMap(counter.key);
                        Set<String> keys = map.keySet();
                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        int ret = 0;
                        for(String key:keys){
                            Date it = format2.parse(key);
                            if(it.after(from)&&it.before(to)){
                                String value = map.get(key);
                                ret+=Integer.parseInt(value);
                            }
                        }
                        System.out.println("本时间段count一共增加了"+ret);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
        //写所有的write counter
        for(counter counter : writeCounterObj){
            switch (counter.type){
                case "num":
                    Jedisutil.setIncrNum(counter.key,counter.valueField);
                    break;
                case "hash":
                    //时间戳
                    //存储结构：keyField-time-valueField
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    Jedisutil.setHashPush(counter.key,timestamp.toString(),counter.valueField+"");
                    break;
                default:
                    break;
            }
        }
    }

    private void initCounters(){
        for(String readName:readCounter){
            counter counter = new counter(readName);
            new counterjson(counter);
            readCounterObj.add(counter);
        }
        for(String writeName:writeCounter){
            counter counter = new counter(writeName);
            new counterjson(counter);
            writeCounterObj.add(counter);
        }
    }
}

