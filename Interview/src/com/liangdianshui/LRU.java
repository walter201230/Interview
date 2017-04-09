package com.liangdianshui;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作系统中可以使用 LRU（Least Recently Used）内存淘汰旧数据的策略，
 * 如果内存需要加载新数据但空间不足，则会按照最近访问时间进行排序，并将最老的数据淘汰。
 * 假设现在内存空间大小为5，原本内存中没有数据，
 * 对内存中数据的访问顺序如下： 1, 2, 5, 3, 4, 6,1, 4, 3, 6, 7, 8, 3, 9
 * 问访问过程中发生缺页的次数是多少次？
 * 
 * A. 缺页次数：4 
 * B. 缺页次数：10 
 * C. 缺页次数：5 
 * D. 缺页次数：9
 * 
 * @author 两点水
 *
 */
public class LRU {

	public static void main(String[] args) {
        LimitQueue<String> list = new LimitQueue<String>(5);
        Integer[] array = new  Integer[]{1, 2, 5, 3, 4, 6,1, 4, 3, 6, 7, 8, 3, 9 };
        List<Integer> page = new ArrayList<Integer>();
        for(Integer i :array){
            if(list.contains(i.toString())){
                list.remove(i);
            }else{
                page.add(i);
            }
            list.offer(i.toString()); 
        }
        System.out.println("缺页数量"+page.size()+",缺页数据"+page.toString());
    }
}
