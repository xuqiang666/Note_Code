package com.xq.notes.designpattern.taskstate;

// 任务进行状态
class TaskOngoing implements State {
    private final ActivityService activityService = new ActivityService();
    private final TaskManager taskManager = new TaskManager();

    @Override
    public void update(Task task, ActionType actionType) {
        if (actionType == ActionType.ACHIEVE) {
            task.setState(new TaskFinished());
            // 通知
            Long taskId = task.getTaskId();
            activityService.notifyFinished(taskId);
            taskManager.release(taskId);
        } else if (actionType == ActionType.STOP) {
            task.setState(new TaskPaused());
        } else if (actionType == ActionType.EXPIRE) {
            task.setState(new TaskExpired());
        }
    }
}
