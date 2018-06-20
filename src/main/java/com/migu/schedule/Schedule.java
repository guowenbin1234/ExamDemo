package com.migu.schedule;


import com.migu.schedule.constants.DataManagement;
import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule {


    public int init() {
    	DataManagement.NodeMap.clear();
    	DataManagement.TaskMap.clear();
    	return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
       int code = ReturnCodeKeys.E000;
       if(nodeId <= 0){
    	   code = ReturnCodeKeys.E004;
       }
       else if (DataManagement.NodeMap.containsKey(nodeId)) {
    	   code = ReturnCodeKeys.E005;
	}
       else {
    	   DataManagement.NodeMap.put(nodeId, nodeId+"");
    	   code = ReturnCodeKeys.E003;
	}
       return code;
    }

    public int unregisterNode(int nodeId) {
       int code = ReturnCodeKeys.E000;
       if(nodeId <= 0){
    	   code = ReturnCodeKeys.E004;
       }
       else if (!DataManagement.NodeMap.containsKey(nodeId)) {
    	   code = ReturnCodeKeys.E007;
	}
       else {
    	   DataManagement.NodeMap.remove(nodeId);
    	   code = ReturnCodeKeys.E006;
	}
       return code;
    }


    public int addTask(int taskId, int consumption) {
    	int code = ReturnCodeKeys.E000;
    	if (taskId <= 0) {
    		code = ReturnCodeKeys.E009;
		}
    	else if (DataManagement.TaskMap.containsKey(taskId)) {
    		code = ReturnCodeKeys.E010;
		}
    	else {
    		DataManagement.TaskMap.put(taskId, taskId+"");
    		code = ReturnCodeKeys.E008;
		}
        return code;
    }


    public int deleteTask(int taskId) {
    	int code = ReturnCodeKeys.E000;
    	if (taskId <= 0) {
    		code = ReturnCodeKeys.E009;
		}
    	else if (!DataManagement.TaskMap.containsKey(taskId)) {
    		code = ReturnCodeKeys.E012;
		}
    	else {
    		DataManagement.TaskMap.remove(taskId);
    		code = ReturnCodeKeys.E011;
		}
        return code;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
    	int code = ReturnCodeKeys.E000;
    	if (tasks == null) {
			code = ReturnCodeKeys.E016;
		}
        return code;
    }

}
