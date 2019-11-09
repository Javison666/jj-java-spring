package cn.jiweiqing.base.config.shiro;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 处理map和list数据
 *
 * @author by xin.xue on 2018/9/2.
 * 接收application.yml中的myProps下面的属性
 */

public class ShiroProps {

    private Map<String, String> filterChainDefinitionMap = new HashMap<>();


    public Map<String, String> getFilterChainDefinitionMap() {
        HashMap<String, String> newFilterMap = new LinkedHashMap<>(20);
        Iterator<Map.Entry<String, String>> iterator = filterChainDefinitionMap.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            // 往newMap中放入新的Entry
            newFilterMap.
                    put(entry.getKey().replace("[", "")
                            .replace("]", ""), entry.getValue());
            // 删除老的Entry
            iterator.remove();
        }
        if (!newFilterMap.isEmpty()) {
            newFilterMap.put("/**", "user");
        }
        return newFilterMap;
    }

    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }
}
