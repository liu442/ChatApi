package com.steve.utils.systemUtil;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 自定义该list主要是为了重写里面toString方法，原有的ArrayList打印出来会有空格，这导致签名和其他语言组织签名会存在问题
 *
 * List<String> list=new ArrayList<String>();
 * list.add("张三");
 * list.add("李四");
 * list.add("王五");
 * System.out.println(list.toString());
 * 结果输出：
 * [张三, 李四, 王五]
 * 结果中我们不难发现，从第二个数据开始，每个数据的前面多了一个空格。
 * @param <E>
 */
public class DiyArrayList<E> extends ArrayList<E> {
    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',');
        }
    }
}
