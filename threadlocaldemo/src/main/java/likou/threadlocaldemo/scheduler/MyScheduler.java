package likou.threadlocaldemo.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        int count=0;
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).usingJobData("jobDetail1", "这个Job用来测试的")
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例,每隔1s执行一次

       /* Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger")
                .startNow()//立即生效              //任务调度的方式去执行
                .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 2018")).build();*///一直执行*/

        //System.out.println("_______________________________________________________________");
         //任务启动5秒后执行
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 5000);
        //任务执行5秒后停止
        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 5000);

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger")
                .startNow()//立即生效
                //.startAt(startDate)
                //.endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行
       // System.out.println("_______________________________________________________________");
      /*  Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行*/

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        JobDetail jobDetail1 = JobBuilder.newJob(PrintWordsJob.class).usingJobData("jobDetail1", "这个Job2用来测试的")
                .withIdentity("job2", "group1").build();
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup1").usingJobData("trigger1","这是jobDetail2的trigger")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行*/
        //scheduler.addJob(jobDetail1,false);
        scheduler.scheduleJob(jobDetail1, trigger1);
        TimeUnit.SECONDS.sleep(2);
       // scheduler.deleteJob(jobDetail.getKey());//删除job后不能再恢复
        System.out.println(scheduler.isStarted());


        TimeUnit.SECONDS.sleep(2);
        scheduler.resumeJob(jobDetail.getKey());

        //睡眠
        TimeUnit.SECONDS.sleep(2);
        scheduler.shutdown();
        System.out.println(scheduler.isStarted());
        System.out.println("--------scheduler shutdown ! ------------");
    }
}
