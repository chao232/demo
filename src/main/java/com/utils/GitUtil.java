package com.utils;


import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitStats;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GitUtil {

    public static int getTotalLines(String projectName, String branch) {
        int line = 0;
        try {
            String token = "";
            String url = "https://gitlab.com/";
            Date end = new Date();
            Date start = Date.from(LocalDateTime.now().minusMonths(4L).atZone(ZoneId.systemDefault()).toInstant());
            GitLabApi gitLabApi = new GitLabApi(url, token);
            List<Commit> commitsMaster = gitLabApi.getCommitsApi().getCommits(projectName, "master", start, end);
            //不一定是合入master，存在先合入dev2，再一起合入master，eg：pre_master
            commitsMaster=
                    commitsMaster.stream().filter(commit -> commit.getMessage().contains(branch+"' " +
                            "into")
//                            || commit.getMessage().contains(
//                            "into "+branch)
                    ).collect(Collectors.toList());
            List<Commit> finalCommitsMaster = commitsMaster;
            //判断是否合入master
            log.info("commitsMaster.size():{}",commitsMaster.size());
            if(commitsMaster.size()==0){
                List<Commit> commitsBranch = gitLabApi.getCommitsApi().getCommits(projectName, branch, start, end);
                List<Commit> commitsMaster1= gitLabApi.getCommitsApi().getCommits(projectName, "master", start, end);
                finalCommitsMaster=commitsBranch.stream().filter(commit -> {
                    for (Commit commit1 : commitsMaster1) {
                        if(commit1.getId().equals(commit)){
                            return false;
                        }
                    }
                    return true;
                }).collect(Collectors.toList());
            }

            for (Commit commit : finalCommitsMaster) {
                CommitStats stats = gitLabApi.getCommitsApi().getCommit(projectName, commit.getShortId()).getStats();
                if (stats != null) {
                    line = line + stats.getTotal();
                }
            }
                log.info("projectName line-->>>{}", line);
            } catch(Exception e){
                e.printStackTrace();
            }
            return line;
        }
    }
