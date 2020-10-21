# ABOUT

从几个spring提供的关键的PostProcessor处理器，来帮助理解spring创建bean的过程：

下面是按方法执行的前后顺序排列
  - **BeanDefinitionRegistryPostProcessor**
    > 继承BeanFactoryPostProcessor，允许更多的bean definitions注册，运行在BeanFactoryPostProcessor之前
      特别的，这个类可以注册更多的bean definitions，甚至包括BeanFactoryPostProcessor的实例
  - **BeanFactoryPostProcessor.postProcessBeanFactory()**
    > 用来在所有的bean definitions加载后，但是还没有实例化bean之前调用本方法，所以本方法非常超前执行
    可以修改bean的定义，如是否是单例，是否lazy init，DependsOn，FactoryBeanName等等等等，一般用来修改属性值，一个典型的实现是PropertyResourceConfigurer，用来从配置文件里加载属性放进bean里，或者更换${...}placeHolder
  - **bean的构造方法**
  - **BeanPostProcessor.postProcessBeforeInitialization()**
    >在bean进行初始化方法（如InitializingBean.afterPropertiesSet或者自定义的init方法@PostConstruct）的回调之前调用
    调用此方法时，bean的属性值已经设置好
    可以返回一个包装类
  - **@PostConstruct**
    >用来标注在bean的方法上，在**依赖注入后**，放入spring容器前执行一些init逻辑（init方法中可以使用依赖属性）。一个bean中只能有一个@PostConstruct
    然后注意，这是java规定的注解！！
    如果在拦截器中,（没见过这个用法，不太懂）
        必须有InvocationContext方法参数，可以有Object返回值
    如果不是在拦截器类中：
         不能有返回值和方法参数
        标注的方法可以是public, protected,package private or private
        可以是final的
        不能抛出运行时异常，会导致容器启动失败
  - **InitializingBean.afterPropertiesSet()**
    >用来BeanFactory将bean的所有属性都设置后，执行一些init逻辑或者只是check属性是否正确和缺失。
  - **BeanPostProcessor.postProcessAfterInitialization()**
    >在bean进行初始化方法（如InitializingBean.afterPropertiesSet或者自定义的init方法@PostConstruct）的回调之后调用
    调用此方法时，bean的属性值已经设置好
    可以返回一个包装类
    可能被多次调用
  - **SmartInitializingSingleton**
    >这个接口可以被一个单例的bean实现，在BeanFactory实例化所有单例bean之后执行。
    一般用来执行一些init逻辑，在想要急切获取其他bean时可以用来替换InitializingBean.afterPropertiesSet()的方案
  - **SmartLifecycle.start()**
    >Lifecycle接口的扩展，用来实现想在容器启动刷新，或者shutdown时执行一些逻辑。
     isAutoStartup()用来表示在容器刷新时这个bean是否启动，否则只有容器start时重新创建
     stop(Runnable)用于需要异步的关闭逻辑，必须要调用callback.run()
     Phased用于控制多个bean的启动顺序，value较小的会先启动，shutdown时会后关闭。
     如ComponentB依赖componentA先启动，则componentA.phase()应该返回一个较小的值，关闭时B会先关闭。
     如果明确指定depends-on，以depends-on为准。
     任何没有实现SmartLifecycle的bean的phase会是0，这也就意味着如果SmartLifecycle Bean的phase返回负数，将优先于所有的容器bean启动，正数反之。
     SmartLifecycle的DEFAULT_PHASE = Integer.MAX_VALUE;
