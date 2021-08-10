package org.zhl.tomcat;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-24 15:26
 **/
@Log
public class TomcatEmbRun {

    @Setter
    @NonNull
    private String moudle;

    @Getter
    private Tomcat tomcat;

    @Getter
    private Context context;

    public TomcatEmbRun(@NonNull String moudle) {
        this.moudle = moudle;
        init();
    }

    private void init() {
        this.tomcat = new Tomcat();
        TomcatConfig tomcatConfig = new TomcatConfig(moudle);
        log.info("启动服务器");
        final Connector connector = tomcat.getConnector();
        connector.setPort(8080);
        this.context = tomcat.addContext("/", tomcatConfig.getWebappDir());
    }

    public void run() throws Exception {
        tomcat.start();
        log.info("启动服务器成功");
        tomcat.getServer().await();
        log.info("关闭服务器");
    }

    public void addServlet(String url, String name, String servlet) {
        tomcat.addServlet(url, name, servlet);
    }

}
