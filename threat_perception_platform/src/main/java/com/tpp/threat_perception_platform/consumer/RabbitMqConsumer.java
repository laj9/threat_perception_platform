package com.tpp.threat_perception_platform.consumer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.tpp.threat_perception_platform.pojo.*;
import com.tpp.threat_perception_platform.pojo.Process;
import com.tpp.threat_perception_platform.service.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class RabbitMqConsumer {
    @Autowired
    private HostService hostService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private HotfixService hotfixService;

    @Autowired
    private UserVulnerabilityService userVulnerabilityService;

    @Autowired
    private WeakPwdService weakPwdService;

    @Autowired
    private UserAppVulnerabilityService userAppVulnerabilityService;

    @RabbitListener(queues = {"sysinfo_queue"})
    public void receive_message(String message, @Headers Map<String,Object> headers,
                                Channel channel) throws IOException {
        // 接收消息
        System.out.println("receive_message:" + message);

        // 将json字符串类型的消息转化为host对象
        Host host = JSON.parseObject(message, Host.class);

        // 如果操作系统的类型为Microsoft Windows 10 家庭中文版，将其改为Windows,正常情况不可以这样搞，这里是我的数据库里面自编的数据类型没编好，真实的数据只有一条，所以做了一下调整
        if(Objects.equals(host.getOsType(), "Microsoft Windows 10 家庭中文版")){
            host.setOsType("Windows");
        }

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = hostService.pushHost(host);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }


    }

    @RabbitListener(queues = {"status_queue"})
    public void receive_status_message(String message, @Headers Map<String,Object> headers,
                                       Channel channel) throws IOException {
        // 接收消息
        System.out.println("receive_message:" + message);

        // 将json字符串类型的消息转化为host对象
        Host host = JSON.parseObject(message, Host.class);

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = hostService.heartbeat(host);

        // 手动 ACK, 先获取 deliveryTag
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        // ACK
        channel.basicAck(deliveryTag,false);

    }

    @RabbitListener(queues = {"account_queue"})

    public void account(String message, @Headers Map<String,Object> headers,
                        Channel channel) throws IOException {
        // 将json字符串类型的消息转化为Account对象
        List<Account> accounts = JSON.parseArray(message, Account.class);

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = accountService.addAccount(accounts);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }


    }

    @RabbitListener(queues = {"service_queue"})

    public void service(String message, @Headers Map<String,Object> headers,
                        Channel channel) throws IOException {
        // 将json字符串类型的消息转化为Service对象
        List<Service> services = JSON.parseArray(message, Service.class);

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = serviceService.addService(services);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }


    }

    @RabbitListener(queues = {"process_queue"})

    public void process(String message, @Headers Map<String,Object> headers,
                        Channel channel) throws IOException {
        // 将json字符串类型的消息转化为Process对象
        List<Process> processes = JSON.parseArray(message, Process.class);

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = processService.addProcess(processes);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }


    }

    @RabbitListener(queues = {"application_queue"})

    public void application(String message, @Headers Map<String,Object> headers,
                            Channel channel) throws IOException {
        // 将json字符串类型的消息转化为Application对象
        List<Application> applications = JSON.parseArray(message, Application.class);

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = applicationService.addApplication(applications);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }


    }

    @RabbitListener(queues = {"hotfix_queue"})

    public void hotfix(String message, @Headers Map<String,Object> headers,
                       Channel channel) throws IOException {
        // 将json字符串类型的消息转化为Hotfix对象
        List<Hotfix> hotfixes = JSON.parseArray(message, Hotfix.class);

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = hotfixService.addHotfix(hotfixes);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }
    }

    @RabbitListener(queues = {"vul_queue"})

    public void userVulnerability(String message, @Headers Map<String,Object> headers,
                                  Channel channel) throws IOException {
        // 将json字符串类型的消息转化为Vulnerability对象
        List<UserVulnerability> userVulnerabilities = JSON.parseArray(message, UserVulnerability.class);

        // 获取当前时间并设置到vulnerabilities中
        for (UserVulnerability userVulnerability : userVulnerabilities) {
            userVulnerability.setTime(new Date());
            userVulnerability.setTaskSender("sys");
            System.out.println(userVulnerability);
        }

        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = userVulnerabilityService.addUserVulnerability(userVulnerabilities);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }
    }

    @RabbitListener(queues = {"weak_pwd_queue"})

    public void weakPwd(String message, @Headers Map<String,Object> headers,
                        Channel channel) throws IOException {
        // 将json字符串类型的消息转化为WeakPwd对象
        List<WeakPwd> weakPwds = JSON.parseArray(message, WeakPwd.class);

        // 添加扫描时间和任务发起者
        for (WeakPwd weakPwd : weakPwds) {
            weakPwd.setTime(new Date());
            weakPwd.setTaskSender("admin");
        }
        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = weakPwdService.addWeakPwd(weakPwds);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }
    }

//    @RabbitListener(queues = {"app_vul_queue"})

    public void appVulnerability(String message, @Headers Map<String,Object> headers,
                                 Channel channel) throws IOException {
        // 将json字符串类型的消息转化为UserAppVulnerability对象
        List<UserAppVulnerability> userAppVulnerabilities = JSON.parseArray(message, UserAppVulnerability.class);

        // 添加扫描时间和任务发起者
        for (UserAppVulnerability userAppVulnerability : userAppVulnerabilities) {
            userAppVulnerability.setTime(new Date());
            userAppVulnerability.setTaskSender("admin");
        }
        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = userAppVulnerabilityService.addUserAppVulnerability(userAppVulnerabilities);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }
    }
    @Autowired
    private LogService logService;

    @Autowired
    private BaseLineResultService baseLineResultService;


    @RabbitListener(queues = {"my_log"})

    public void log(String message, @Headers Map<String,Object> headers,
                    Channel channel) throws IOException {

        System.out.println(message);
        // 将json字符串类型的消息转化为UserAppVulnerability对象
        List<Log> logList = JSON.parseArray(message, Log.class);

        // 添加扫描时间和转换时间戳格式
//        for (Log log : logList) {
//            // 添加扫描时间
//            log.setSubmitTime(new Date());
//
//            // 将时间戳格式转换为Date
//            String timestampStr = log.getTimestampStr();
//            try {
//                Date timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestampStr);
//                log.setTimestamp(timestamp);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }
        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
//        int res = logService.addLogList(logList);
        int res = 0;
        for (Log log : logList) {
            System.out.println(log);
            res = logService.addLog(log);
        }

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }
    }

    @RabbitListener(queues = {"base_line_queue"})

    public void baseLineResult(String message, @Headers Map<String,Object> headers,
                               Channel channel) throws IOException {
        // 将json字符串类型的消息转化为UserAppVulnerability对象
        List<BaseLineResult> baseLineResultList = JSON.parseArray(message, BaseLineResult.class);

        // 添加扫描时间
//        for (BaseLineResult result : baseLineResultList) {
//            // 添加扫描时间
//            result.setTime(new Date());
//        }
        // 消息入库,res用于接收返回的信息，返回的信息为影响的行数
        int res = baseLineResultService.addBaseLineResult(baseLineResultList);

        if (res > 0){
            // res>0，说明影响行数不为0，入库成功
            // 手动 ACK, 先获取 deliveryTag
            Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
            // ACK
            channel.basicAck(deliveryTag,false);
        }
    }

}