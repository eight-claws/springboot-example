# Getting Started

 
  ============== [ SmartInitializingSingleton ]  -afterSingletonsInstantiated()
  ============== [ SmartLifecycle ]
  Tomcat started on port(s): 8080 (http) with context path ''
  Started AppStartApplication in 1.358 seconds (JVM running for 2.637)
  ============== [ ApplicationRunner ] 
  ============== [ listener ApplicationReadyEvent ]
  
  ApplicationRunner和CommandLineRunner对应着容器启动后的回调callRunner
  ApplicationReadyEvent是在容器启动后context.publishEvent(new ApplicationReadyEvent)
  

