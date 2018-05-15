package com.pixolut.sonar;

import act.data.annotation.Data;
import act.util.SimpleBean;
import org.osgl.util.S;

import java.util.List;

@SuppressWarnings("unused")
@Data
public class Issue implements SimpleBean {
    public String key;
    public String rule;
    public String severity;
    public String component;
    public int line;
    public String message;
    public String type;

    public String getSource() {
        return S.concat(S.afterLast(component, ":"), ":", line);
    }

    @Data
    public static class Resp implements SimpleBean {
        public int total;
        public List<Issue> issues;
    }
}
