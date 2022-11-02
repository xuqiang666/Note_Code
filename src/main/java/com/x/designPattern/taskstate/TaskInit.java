package com.x.designpattern.taskstate;

/**
 * 任务初始状态
 */
class TaskInit implements State {
    @Override
    public void update(Task task, ActionType actionType) {
        if (actionType == ActionType.START) {
            task.setState(new TaskOngoing());
        }
    }
}
