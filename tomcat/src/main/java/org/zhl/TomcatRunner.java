package org.zhl;

import lombok.extern.java.Log;
import org.zhl.tomcat.TomcatEmbRun;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 22:35
 **/
@Log
public final class TomcatRunner {

    public static void main(String[] args) throws Exception {
        new TomcatEmbRun("tomcat").run();
    }

}
