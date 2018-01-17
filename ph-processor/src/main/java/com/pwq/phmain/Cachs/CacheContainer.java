package com.pwq.phmain.Cachs;

import com.gargoylesoftware.htmlunit.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CacheConta
 * @author Yu Yangjun
 * @date 2016/8/21
 */
public final class CacheContainer {

    /*存放缓存内容*/
    private Map<String,Page> contentPageMap = new HashMap<>();
    /*存放缓存内容*/
    private Map<String,List<Page>> contentPagesMap = new HashMap<>();
    private Map<String,String> contentStringMap = new HashMap<>();
    private Map<String,List<String>> contentStringsMap = new HashMap<>();

    /**
     * 从缓存中取数据
     * @param
     * @return
     */
    public Map<String,Page> getPageMap() {
        return contentPageMap;
    }

    /**
     * 从缓存中取数据
     * @param
     * @return
     */
    public Map<String,List<Page>> getPagesMap() {
        return contentPagesMap;
    }

    /**
     * 从缓存中取数据
     * @param
     * @return
     */
    public Map<String,String> getStringMap() {
        return contentStringMap;
    }

    /**
     * 从缓存中取数据
     * @param
     * @return
     */
    public Map<String,List<String>> getStringsMap() {
        return contentStringsMap;
    }

    /**
     *向缓存中取数据
     *
     * @param key
     * @param value
     */
    public void putPage(String key, Page value){
    	contentPageMap.put(key,value);
    }
    
    /**
     * 从缓存中取数据
     * @param key
     * @return
     */
    public Page getPage(String key){
        return contentPageMap.get(key);
    }
    
    /**
     *向缓存中取数据
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value){
    	contentStringMap.put(key,value);
    }

    /**
     * 从缓存中取数据
     * @param key
     * @return
     */
    public String getString(String key){
        return contentStringMap.get(key);
    }

    /**
     *向缓存中存数据
     * @param key
     * @param pages
     */
    public void putPages(String key, List<Page> pages){
    	contentPagesMap.put(key,pages);
    }
    
    /**
     * 从缓存中取数据
     * @param key
     * @return
     */
    public List<Page> getPages(String key){
        return contentPagesMap.get(key);
    }
    
    /**
     *向缓存中存数据
     * @param key
     * @param pagesInfo
     */
    public void putStrings(String key, List<String> pagesInfo){
      	contentStringsMap.put(key, pagesInfo);
    }
    
    /**
     * 从缓存中取数据
     * @param key
     * @return
     */
    public List<String> getStrings(String key){
        return contentStringsMap.get(key);
    }
}
