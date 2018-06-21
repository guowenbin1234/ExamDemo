package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.NodeInfo;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *类名和方法不能修改
 */
public class Schedule {
    /**
     * 启用的任务队列(任务id,任务信息)
     */
    public static ConcurrentHashMap<Integer,TaskInfo> taskExecQueue
            = new ConcurrentHashMap<Integer, TaskInfo>();

    /**
     * 挂起的任务队列(任务id,任务信息)
     */
    public static ConcurrentHashMap<Integer,TaskInfo> taskSuspendQueue
            = new ConcurrentHashMap<Integer, TaskInfo>();

    /**
     * 节点信息(节点id,任务id集合)
     */
    public static ConcurrentHashMap<Integer,NodeInfo> nodeMap =
            new ConcurrentHashMap<Integer, NodeInfo>();

    public int init() {
        taskExecQueue.clear();
        taskSuspendQueue.clear();
        nodeMap.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if(nodeId <= 0){ //nodeId编号非法
            return ReturnCodeKeys.E004;
        }
        if(nodeMap.containsKey(nodeId)){  //nodeId已注册
            return ReturnCodeKeys.E005;
        }
        nodeMap.put(nodeId,new NodeInfo(nodeId));
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
  
        return ReturnCodeKeys.E000;
    }


    public int addTask(int taskId, int consumption) {
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        if(taskSuspendQueue.containsKey(taskId)){ //任务已添加
            return ReturnCodeKeys.E010;
        }
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(taskId);
        taskInfo.setConsumption(consumption);
        taskSuspendQueue.put(taskId,taskInfo);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        TaskInfo taskInfo;
        if (taskExecQueue.containsKey(taskId)) {
            taskInfo = taskExecQueue.remove(taskId);
            if (taskInfo != null){
                int consumption = taskInfo.getConsumption();
                NodeInfo nodeInfo = nodeMap.get(taskInfo.getNodeId());
                //删除node中已绑定的该任务
                nodeInfo.getTaskIds().remove(taskId);
                //减少总消耗
                int nodeConsumption = nodeInfo.getConsumption() - consumption;
                nodeInfo.setConsumption(nodeConsumption);
            }
        } else if (taskSuspendQueue.containsKey(taskId)){
            taskSuspendQueue.remove(taskId);
        } else {
            return ReturnCodeKeys.E012;
        }

        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
       
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
       
        return ReturnCodeKeys.E000;
    }

}
