package org.zhl.search;

public interface Search<T extends Comparable> {

    int search(T t[], T targetValue);

}
