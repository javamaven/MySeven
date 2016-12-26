package com.seven.chen.weixin.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.seven.chen.weixin.quartz.RefressAccessTokenTask;

public class WeiXinQuartzJob extends QuartzJobBean{
    private RefressAccessTokenTask refressAccessTokenTask;
  
    public RefressAccessTokenTask getRefressAccessTokenTask() {
        return refressAccessTokenTask;
    }

    public void setRefressAccessTokenTask(RefressAccessTokenTask refressAccessTokenTask) {
        this.refressAccessTokenTask = refressAccessTokenTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
   
        refressAccessTokenTask.refressToken();
        
    }

}
