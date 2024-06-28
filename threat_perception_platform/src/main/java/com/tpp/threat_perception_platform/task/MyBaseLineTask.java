package com.tpp.threat_perception_platform.task;

import com.alibaba.fastjson.JSON;
import com.tpp.threat_perception_platform.param.BaseLineTaskParam;
import com.tpp.threat_perception_platform.pojo.BaseLineTask;
import com.tpp.threat_perception_platform.service.BaseLineTaskService;
import com.tpp.threat_perception_platform.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyBaseLineTask {

    @Autowired
    private BaseLineTaskService baseLineTaskService;

    @Autowired
    private RabbitmqService rabbitmqService;

    /**
     * 定时任务，每隔5秒执行一次
     * 基线探测任务，每5秒执行一次，查看是否存在未执行的任务并且到达执行任务的时间，如果是，将指令发送到客户端
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void baseLineTask() {
        // 1. 获取为状态为0（未执行）的任务列表
        List<BaseLineTask> taskList = baseLineTaskService.selectBaseLineTaskByStatus(0);

        // 2. 遍历任务列表，发送命令
        for (BaseLineTask task : taskList) {
            // 3. 计算当前时间与任务时间的差值，小于7秒（留了一点容错时间），向客户端发送指令，进行基线检测
            long currentTimeMillis = System.currentTimeMillis();
            long taskTime = task.getTaskTime().getTime();
            if (currentTimeMillis - taskTime < 7000 && currentTimeMillis - taskTime > 0) {
                // 4. 向客户端发送指令，进行基线检测
                // 4.1. 获取baseLineTaskParam对象,并设置参数
                // 获取mac地址的列表
                String[] macList = task.getTaskHosts().split(",");
                // 向每台主机的消息接收队列发送消息
                for (String mac : macList) {
                    // 创建BaseLineTaskParam对象承载数据
                    BaseLineTaskParam baseLineTaskParam = new BaseLineTaskParam();
                    // 设置MAC地址
                    baseLineTaskParam.setMac(mac);

                    // 4.2. 将ThreatParam对象转换为JSON字符串
                    String data = JSON.toJSONString(baseLineTaskParam);

                    // 4.3. 发送消息到rabbitmq中（消息队列为agent_mac地址_queue）
                    String exchangeName = "agent_exchange";
                    String routingKey = baseLineTaskParam.getMac().replace(":","");
                    rabbitmqService.sendMessage(exchangeName, routingKey, data);
                }

                // 5. 更新任务状态为1（已执行）
                // 创建一个新对象承载数据（老对象数据多，减少内存占用）
                BaseLineTask baseLineTask = new BaseLineTask();
                baseLineTask.setId(task.getId());
                baseLineTask.setTaskStatus(1);
                baseLineTaskService.updateBaseLineTaskByIdSelective(baseLineTask);
            }
        }

    }

}
