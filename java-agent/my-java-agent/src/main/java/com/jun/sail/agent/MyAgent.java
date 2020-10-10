package com.jun.sail.agent;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("Hello Main Method, I'm premain Method from agent, agentOps:" + agentOps);
    }

    public static void agentmain(String agentOps, Instrumentation inst) {
        System.out.println("agent->agentmain is run" + agentOps);
    }

}
