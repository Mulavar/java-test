package ttp.nacos;

import java.util.Properties;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

public class NacosDemo {
    public static void main(String[] args) throws NacosException, InterruptedException {

        Properties properties = new Properties();
        properties.setProperty("serverAddr", "12.0.0.1:8848");
//        properties.setProperty("namespace", "public");

        NamingService naming = NamingFactory.createNamingService(properties);

        naming.registerInstance("providers:org.apache.dubbo.UserProvider.Test2:myInterfaceVersion:myInterfaceGroup", "11.11.11.11", 8888, "TEST1");
        naming.subscribe("providers:org.apache.dubbo.UserProvider.Test2:myInterfaceVersion:myInterfaceGroup", event -> {
            System.out.println(((NamingEvent)event).getServiceName());
            System.out.println(((NamingEvent)event).getInstances());
        });

        naming.registerInstance("providers:org.apache.dubbo.UserProvider.Test2:myInterfaceVersion:myInterfaceGroup", "2.2.2.2", 9999, "DEFAULT");

        Thread.sleep(600000);
        System.out.println(naming.getAllInstances("providers:org.apache.dubbo.UserProvider.Test2:myInterfaceVersion:myInterfaceGroup"));

        naming.deregisterInstance("providers:org.apache.dubbo.UserProvider.Test2:myInterfaceVersion:myInterfaceGroup", "2.2.2.2", 9999, "DEFAULT");

        System.out.println(naming.getAllInstances("providers:org.apache.dubbo.UserProvider.Test2:myInterfaceVersion:myInterfaceGroup"));


    }
}
