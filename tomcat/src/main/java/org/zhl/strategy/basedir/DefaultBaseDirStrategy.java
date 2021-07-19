package org.zhl.strategy.basedir;

import com.google.common.base.Preconditions;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 23:06
 **/
public class DefaultBaseDirStrategy implements IBaseDirStrategy {

    @Override
    public String parser(String baseDir) {
        Preconditions.checkNotNull(baseDir, "userDir不能为空");
        return baseDir;
    }
}
