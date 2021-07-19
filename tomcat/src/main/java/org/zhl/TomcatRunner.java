package org.zhl;

import lombok.extern.java.Log;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 22:35
 **/
@Log
public final class TomcatRunner {

    public static void main(String[] args) throws LifecycleException {
        log.info("启动服务器");
        final Tomcat tomcat = new Tomcat();
        final Connector connector = tomcat.getConnector();
        connector.setPort(8080);
        tomcat.start();
        log.info("启动服务器成功");
        tomcat.getServer().await();
        log.info("关闭服务器");
    }

}
