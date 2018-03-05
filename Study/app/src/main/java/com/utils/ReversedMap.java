package com.utils;

import java.util.HashMap;

public class ReversedMap<K,V> {
    private HashMap<K, V> map;
    private HashMap<V, K> rmap;

    public ReversedMap(){
        map = new HashMap<>();
        rmap = new HashMap<>();
    }

    public void put(K key, V val){
        map.put(key, val);
        rmap.put(val, key);
    }

    public V getByKey(K key){
        return map.get(key);
    }

    public K getByVal(V val){
        return rmap.get(val);
    }
}
