package org.zhl.strategy.userdir;

import com.google.common.base.Preconditions;

import java.io.File;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 23:06
 **/
public class DefaultUserDirStrategy implements IUserDirStrategy {
    @Override
    public String parser(String moudleName) {
        Preconditions.checkNotNull(moudleName);
        return System.getProperty("user.dir") + File.separator + moudleName;
    }
}
