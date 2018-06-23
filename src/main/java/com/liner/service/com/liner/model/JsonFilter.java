package com.liner.service.com.liner.model;

import java.util.List;

/**
 * Created by Liner on 18/6/22.
 */
public class JsonFilter {
    private String groupOp;
    private List<JsonRule> rules;

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public List<JsonRule> getRules() {
        return rules;
    }

    public void setRules(List<JsonRule> rules) {
        this.rules = rules;
    }
}
