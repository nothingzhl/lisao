package org.zhl.strategy.basedir;

/**
 * @author mac
 */
@FunctionalInterface
public interface IBaseDirStrategy {

    /**
     * 解析baseDir
     *
     * @param baseDir
     * @return
     */
    String parser(String baseDir);

}
