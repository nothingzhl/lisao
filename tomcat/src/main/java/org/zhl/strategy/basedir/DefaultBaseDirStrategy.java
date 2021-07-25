package org.zhl.strategy.basedir;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-07-19 23:06
 **/
@Slf4j
public class DefaultBaseDirStrategy implements IBaseDirStrategy {

    @Setter
    private String prefix = "Tomcat";

    @Override
    public String parser(String baseDir) {

        return StringUtils.isBlank(baseDir) ? getDefaultTemp() : baseDir;
    }

    private String getDefaultTemp() {
        try {
            final Path tempDirectory = Files.createTempDirectory(prefix);
            Files.deleteIfExists(tempDirectory);
            return tempDirectory.toFile().getPath();
        } catch (IOException e) {
            log.error("创建临时文件夹失败", e);
            e.printStackTrace();
            return null;
        }
    }
}
