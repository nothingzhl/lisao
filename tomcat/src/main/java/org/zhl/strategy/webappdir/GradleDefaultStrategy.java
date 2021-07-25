package org.zhl.strategy.webappdir;

import org.apache.commons.lang3.StringUtils;
import org.zhl.strategy.userdir.DefaultUserDirStrategy;

import java.io.File;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-24 14:46
 **/
public class GradleDefaultStrategy implements IWebAppDirStrategy {

    @Override
    public String parser(String wepappDir, String modelName) {
        return StringUtils.isBlank(wepappDir) ? getDefaultWebAppDir(modelName) : wepappDir;
    }

    @Override
    public String getUserDir(String moudleName) {
        return new DefaultUserDirStrategy().parser(moudleName);
    }

    @Override
    public String targetName() {
        return "build";
    }

    private String getDefaultWebAppDir(String modelName) {
        return getUserDir(modelName) + File.separatorChar + targetName() + File.separatorChar + "classes";
    }
}
