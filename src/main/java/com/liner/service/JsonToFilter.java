package com.liner.service;

import com.liner.service.com.liner.model.JsonFilter;
import com.liner.service.com.liner.model.JsonRule;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liner on 18/6/22.
 */
public class JsonToFilter {
    public static void main(String[] args) {
        JsonToFilter con = new JsonToFilter();
        String filters = "{\"groupOp\": \"OR\",\"rules\": [{\"field\": \"realname\",\"op\": \"eq\"," +
                "\"data\": \"1234\"},{\"field\": \"cityCode\",\"op\": \"ne\",\"data\": \"5678\"}]}";
        JsonFilter jsonFilter = con.jsonToFilters(filters);
        System.out.println("groupOp=" + jsonFilter.getGroupOp() + " rules.size=" + jsonFilter.getRules().size());
    }

    public JsonFilter jsonToFilters(String filters) {
        JsonFilter jsonFilter = new JsonFilter();

        JSONObject jsonObject = JSONObject.fromObject(filters); //将json格式的字符串转换成JSONObject 对象
        JSONArray jsonArray = jsonObject.getJSONArray("rules"); //如果json格式的字符串里含有数组格式的属性，将其转换成JSONArray，以方便后面转换成对应的实体
        List<JsonRule> rules = new ArrayList<JsonRule>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i); //将array中的数据进行逐条转换
            JsonRule rule = (JsonRule) JSONObject.toBean(object, JsonRule.class);//通过JSONObject.toBean()方法进行对象间的转换
            rules.add(rule);
        }
        String groupOp = jsonObject.getString("groupOp"); //简单的直接获取值
        jsonFilter.setGroupOp(groupOp);
        jsonFilter.setRules(rules);

        return jsonFilter;
    }
}
