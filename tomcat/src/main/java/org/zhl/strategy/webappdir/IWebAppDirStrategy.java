package org.zhl.strategy.webappdir;

/**
 * @author mac
 */
public interface IWebAppDirStrategy {

    /**
     * 解析wepdir
     *
     * @param wepappDir
     * @return
     */
    String parser(String wepappDir, String modelName);

    String getUserDir(String moudleName);

    String targetName();
}
