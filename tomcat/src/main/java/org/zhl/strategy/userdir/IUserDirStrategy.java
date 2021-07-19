package org.zhl.strategy.userdir;

/**
 * @author mac
 */
@FunctionalInterface
public interface IUserDirStrategy {

    /**
     * 解析userDir
     *
     * @param userDir
     * @return
     */
    String parser(String userDir);

}
