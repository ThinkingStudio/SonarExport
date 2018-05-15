package com.pixolut.sonar;

import static act.controller.Controller.Util.badRequestIf;
import static act.controller.Controller.Util.render;

import act.Act;
import act.app.conf.AutoConfig;
import act.inject.param.NoBind;
import com.alibaba.fastjson.JSON;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.Const;
import org.osgl.util.S;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@AutoConfig("sonar")
public class Report {

    public static final Const<String> HOST = $.constant();
    public static final Const<String> LOGIN_TOKEN = $.constant();
    public static final Const<String> PROJECT_KEY = $.constant();
    public static final Const<Integer> PAGE_SIZE = $.constant(500);

    @NoBind
    private OkHttpClient http = http();

    @GetAction
    public void home() {
        String host = HOST.get();
        String loginToken = LOGIN_TOKEN.get();
        String projectKey = PROJECT_KEY.get();
        render(host, loginToken, projectKey);
    }

    @GetAction("report")
    public List<Issue> report(
            String host,
            String loginToken,
            String projectKey
    ) throws Exception {
        if (S.blank(host)) {
            host = HOST.get();
        }
        if (S.blank(loginToken)) {
            loginToken = LOGIN_TOKEN.get();
        }
        if (S.blank(projectKey)) {
            projectKey = PROJECT_KEY.get();
        }
        badRequestIf(S.anyBlank(host, loginToken, projectKey), "host, loginToken and projectKey must be configured or specified");
        List<Issue> issues = new ArrayList<>();
        Issue.Resp respObject = fetchIssues(host, projectKey, loginToken, 1);
        issues.addAll(respObject.issues);
        int total = respObject.total;
        if (total > PAGE_SIZE.get()) {
            int max = total / PAGE_SIZE.get() + 2;
            for (int i = 2; i < max; ++i) {
                respObject = fetchIssues(host, projectKey, loginToken, i);
                issues.addAll(respObject.issues);
            }
        }
        return issues;
    }

    private Issue.Resp fetchIssues(String host, String projectKey, String loginToken, int page) throws Exception {
        Response resp = http.newCall(req(host, projectKey, loginToken, 1)).execute();
        String body = resp.body().string();
        return JSON.parseObject(body, Issue.Resp.class);
    }

    private Request req(String host, String projectKey, String loginToken, int page) {
        return new Request.Builder().url(S.concat(host, "/api/issues/search?projects=", projectKey, "&statuses=OPEN&pageSize=-1&pageIndex=", page)).get().header("Authorization", Credentials.basic(loginToken, "")).build();
    }

    private OkHttpClient http() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
