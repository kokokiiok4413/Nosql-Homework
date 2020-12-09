package com.bjtu.redis;

import com.bjtu.redis.Action.action;
import com.bjtu.redis.Action.actionjson;

import com.bjtu.redis.tool.tool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class RedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
        int num=1;
        while(true) {

            System.out.println(num+ "Choose :");
            System.out.println("A. 增加 B. 读出   C.freq  D.退出");
            System.out.print("：");
            Scanner ms = new Scanner(System.in);
            String ch = ms.nextLine();


            if(!ch.equals("A")&&!ch.equals("B")&&!ch.equals("C")&&!ch.equals("D"))
            {
                System.out.println("\t 错误");
            }else{
                switch (ch)
                {
                    case "A":
                        System.out.println(" plus count");
                        tool r1 = new tool(new actionjson(new action("pluscount")));
                        r1.solver();
                        break;
                    case "B":
                        System.out.println("read count");
                        tool r2 = new tool( new actionjson(new action("readcount")));
                        r2.solver();
                        break;
                    case "C":
                        System.out.println("read freq");
                        tool r3 = new tool(new actionjson(new action("readfreq")));
                        r3.solver();
                        break;
                    case "D":
                        System.out.println("exit");
                        return;
                    default:
                        break;
                }

            }
            num++;
        }

    }
}


