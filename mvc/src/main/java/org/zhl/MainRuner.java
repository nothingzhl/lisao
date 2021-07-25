package org.zhl;

import lombok.extern.slf4j.Slf4j;
import org.zhl.tomcat.TomcatEmbRun;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-24 15:24
 **/
@Slf4j
public class MainRuner {

    public static void main(String[] args) throws Exception {
        final TomcatEmbRun mvc = new TomcatEmbRun("mvc");
        //        Tomcat.addServlet(mvc.getContext(), "uh", TestS.class.getName());
        //        mvc.getContext().addServletMappingDecoded("/*", "uh");
        mvc.run();
    }

}
