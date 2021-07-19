package org.zhl.strategy.userdir;

import com.google.common.base.Preconditions;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 23:06
 **/
public class DefaultUserDirStrategy implements IUserDirStrategy {
    @Override
    public String parser(String userDir) {
        Preconditions.checkNotNull(userDir, "userDir不能为空");
        return userDir;
    }
}
