package org.zhl.func.self;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SelfCollector<T> implements Collector<T, Set<T>, Set<T>> {

    /**
     * 返回的是中间收集器的类型
     *
     * @return
     */
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println(getCurrentMethodName() + "invoked!");
        final List<String> collect =
            Arrays.stream(Thread.currentThread().getStackTrace()).map(StackTraceElement::getMethodName)
                .collect(Collectors.toList());
        return HashSet::new;
    }

    private String getCurrentMethodName() {
        final List<String> collect =
            Arrays.stream(Thread.currentThread().getStackTrace()).map(StackTraceElement::getMethodName)
                .collect(Collectors.toList());
        return collect.get(2) + "\t";
    }

    /**
     * 不断从流中遍历元素，然后不断追加到中间的容器中
     *
     * @return
     */
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println(getCurrentMethodName() + "invoked!");
        // set 是中间容器，element 是流元素
        return (set, element) -> set.add(element);
    }

    /**
     * 并行流的结果合并
     *
     * @return
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println(getCurrentMethodName() + "invoked!");
        return (leftSet, rightSet) -> {
            leftSet.addAll(rightSet);
            return leftSet;
        };
    }

    /**
     * 可选方法：将中间的的结果集转换为最终的结果集
     *
     * @return
     */
    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println(getCurrentMethodName() + "invoked!");
        return Function.identity();
    }

    /**
     * 流操作的特性
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println(getCurrentMethodName() + "invoked!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }
}
