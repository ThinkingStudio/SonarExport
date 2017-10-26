package com.pixolut.sonar;

import act.data.annotation.Data;
import act.util.SimpleBean;

import java.util.List;

@Data
public class Issue implements SimpleBean {
    public String key;
    public String rule;
    public String severity;
    public String component;
    public int line;
    public String message;
    public String type;

    @Data
    public static class Resp implements SimpleBean {
        public int total;
        public List<Issue> issues;
    }
}
