package org.zhl.agent;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

/**
 * Agent的类
 */
public class Agent {

    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("Args" + args);
    }

    public static void premain(String args, Instrumentation inst) {
        System.out.println("Pre Args:" + args);
        long count = Arrays.stream(inst.getAllLoadedClasses())
                .count();
        System.out.println("所有的load class 总数为:"+count);
    }

}
