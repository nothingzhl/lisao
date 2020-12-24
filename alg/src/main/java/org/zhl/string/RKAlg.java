package org.zhl.string;

/**
 * rk 算法
 */
public class RKAlg implements StringFind {

    @Override
    public boolean find(String origin, String match) {

        char[] origins = origin.toCharArray();
        char[] matchs = match.toCharArray();
        int length = matchs.length;
        int matchHash = hash(matchs, 0, length);

        for (int i = 0; i < origins.length-length; i++) {
            int hash = hash(origins, i, length);
            // hash 相同的话再比较子串是否相同
            if (hash == matchHash) {
                for (int j = 0; j < length; j++) {
                    if (!(origins[i + j] == matchs[j])) {
                        break;
                    }
                    if (j == length-1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int hash(char[] origins, int i, int length) {
        char[] copy = new char[length];
        System.arraycopy(origins, i, copy, 0,
                length);
        String s = new String(copy);
        return s.hashCode();
    }
}
