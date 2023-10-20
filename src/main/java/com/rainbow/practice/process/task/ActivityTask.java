package com.rainbow.practice.process.task;

import lombok.Data;

/**
 * @Author: yzh
 * @Date: 2023/10/20
 * @Description:
 */
@Data
public class ActivityTask implements Task{

    private String taskId;

    private String taskKey;

    private String taskName;

    private String instanceId;

    private String defineId;

    private String defineKey;

    private String defineName;

    private String assignee;
}
