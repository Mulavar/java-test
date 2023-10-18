package ttp.hystrix;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;

    /**
     * 定义构造函数，参数即被包装方法的输入参数
     *
     * @param name
     */
    public HelloWorldCommand(String name) {
        //定义命令组 和 方法调用超时时间
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000)));
        this.name = name;
    }

    /**
     * 封装业务逻辑的方法体，在这里执行真正的业务逻辑。
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        //例子中，模拟执行超时
        if ("TimeoutCommand".equals(name)) {
            TimeUnit.SECONDS.sleep(2);
        }
        //例子中，模拟出现异常
        if ("ExceptionCommand".equals(name)) {
            throw new Exception("ExceptionCommand");
        }
        //例子中，模拟正常返回
        return "Hello " + name + "!";
    }

    /**
     * 降级方法定义，即，run方法中出现异常后应该执行的方法。包括例子中的超时。
     *
     * @return
     */
    @Override
    protected String getFallback() {
        return "Command failed!";
    }
}