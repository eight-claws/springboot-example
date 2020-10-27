
# ABOUT

# features
- spring-boot-starter-test 单元测试相关
- spring-boot-starter-actuator 健康检查
- ConfigurationProperties 加载配置值

 
## spring-boot-starter-test 单元测试

提供了两种测试controller接口的方法

```java
@SpringBootTest
    @AutoConfigureMockMvc
    public class HelloControllerTest {
        @Autowired
        private MockMvc mvc;
    
        @Test
        public void getHello() throws Exception {
            mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
        }
    }
```

```java
 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class HelloControllerIT {
    
        @LocalServerPort
        private int port;
    
        private URL base;
    
        /**
         * 就是为了方便测试使用，在使用@SpringBootTest时，会自动生成可注入。
         * 默认忽略cookie和重定向
         */
        @Autowired
        private TestRestTemplate template;
    
        @BeforeEach
        public void setUp() throws Exception {
            this.base = new URL("http://localhost:" + port + "/");
        }
    
        @Test
        public void getHello() throws Exception {
            ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
            assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
        }
    }
```
## spring-boot-starter-actuator 健康检查
添加spring-boot-starter-actuator的依赖后，默认暴露以下断点：
- actuator/health
> {
     "status":"UP",
     "components":{
         "diskSpace":{
             "status":"UP",
             "details":{
                 "total":1000202039296,
                 "free":991307812864,
                 "threshold":10485760,
                 "exists":true
             }
         },
         "ping":{
             "status":"UP"
         }
     }
 }
- actuator/info
- actuator 列出所有暴露的端点。默认只开启部分，配置文件里开启了*所有
```{
      "_links":{
          "self":{
              "href":"http://localhost:8080/actuator",
              "templated":false
          },
          "beans":{
              "href":"http://localhost:8080/actuator/beans",
              "templated":false
          },
          "caches-cache":{
              "href":"http://localhost:8080/actuator/caches/{cache}",
              "templated":true
          },
          "caches":{
              "href":"http://localhost:8080/actuator/caches",
              "templated":false
          },
          "health":{
              "href":"http://localhost:8080/actuator/health",
              "templated":false
          },
          "health-path":{
              "href":"http://localhost:8080/actuator/health/{*path}",
              "templated":true
          },
          "info":{
              "href":"http://localhost:8080/actuator/info",
              "templated":false
          },
          "conditions":{
              "href":"http://localhost:8080/actuator/conditions",
              "templated":false
          },
          "configprops":{
              "href":"http://localhost:8080/actuator/configprops",
              "templated":false
          },
          "env":{
              "href":"http://localhost:8080/actuator/env",
              "templated":false
          },
          "env-toMatch":{
              "href":"http://localhost:8080/actuator/env/{toMatch}",
              "templated":true
          },
          "loggers":{
              "href":"http://localhost:8080/actuator/loggers",
              "templated":false
          },
          "loggers-name":{
              "href":"http://localhost:8080/actuator/loggers/{name}",
              "templated":true
          },
          "heapdump":{
              "href":"http://localhost:8080/actuator/heapdump",
              "templated":false
          },
          "threaddump":{
              "href":"http://localhost:8080/actuator/threaddump",
              "templated":false
          },
          "metrics":{
              "href":"http://localhost:8080/actuator/metrics",
              "templated":false
          },
          "metrics-requiredMetricName":{
              "href":"http://localhost:8080/actuator/metrics/{requiredMetricName}",
              "templated":true
          },
          "scheduledtasks":{
              "href":"http://localhost:8080/actuator/scheduledtasks",
              "templated":false
          },
          "mappings":{
              "href":"http://localhost:8080/actuator/mappings",
              "templated":false
          }
      }
  }
```

/actuator/shutdown默认禁用，可以management.endpoints.web.exposure.include=health,info,shutdown开启
