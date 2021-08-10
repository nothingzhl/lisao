package org.zhl.tomcat;

import lombok.*;
import org.zhl.strategy.basedir.DefaultBaseDirStrategy;
import org.zhl.strategy.basedir.IBaseDirStrategy;
import org.zhl.strategy.userdir.DefaultUserDirStrategy;
import org.zhl.strategy.userdir.IUserDirStrategy;
import org.zhl.strategy.webappdir.GradleDefaultStrategy;
import org.zhl.strategy.webappdir.IWebAppDirStrategy;

import java.util.Optional;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 22:48
 **/
@NoArgsConstructor
@RequiredArgsConstructor
public class TomcatConfig {

    @Setter
    private IUserDirStrategy userDirStrategy;

    @Setter
    private IBaseDirStrategy baseDirStrategy;

    @Setter
    private IWebAppDirStrategy iWebAppDirStrategy;

    /**
     * 端口
     */
    @Setter
    @Getter
    private Integer port;

    /**
     * 项目目录，用于其它目录定位相对路径
     */
    @Setter
    private String userDir;

    /**
     * tomcat 应用存放的目录，这个放哪儿没关系
     */
    @Setter
    private String tomcatBaseDir;

    /**
     * 项目部署目录，我们这里需要设置为 $userDir$/target/classes 目录，因为项目编译的文件都会存到改目录下
     */
    @Setter
    private String webappDir;

    @Setter
    @NonNull
    private String modelName;

    public String getUserDir() {
        return Optional.of(userDirStrategy).orElseGet(DefaultUserDirStrategy::new).parser(TomcatConfig.this.userDir);
    }

    public String getTomcatBaseDir() {
        return Optional.ofNullable(baseDirStrategy).orElseGet(DefaultBaseDirStrategy::new)
            .parser(TomcatConfig.this.tomcatBaseDir);
    }

    public String getWebappDir() {
        return Optional.ofNullable(iWebAppDirStrategy).orElseGet(GradleDefaultStrategy::new)
            .parser(webappDir, modelName);
    }
}
