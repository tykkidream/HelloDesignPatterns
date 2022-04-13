package hello.designpatterns.batch.task;

public enum TaskSyncState {
    /**
     * 未知
     */
    None(0) {
        @Override
        public boolean isNone() {
            return true;
        }
    },

    /**
     * 待调度
     */
    ToBeDispatched(10) {
        @Override
        public boolean isToBeDispatched() {
            return true;
        }
    },

    /**
     * 重复任务调度忽略（一段时间内重复任务的忽略）
     */
    DispatchRepeatIgnore(12) {
        @Override
        public boolean isDispatchRepeatIgnore() {
            return true;
        }
    },

    /**
     * 过时任务调度忽略（一段时间内乱序任务的忽略）
     */
    DispatchOutdatedIgnore(13) {
        @Override
        public boolean isDispatchOutdatedIgnore() {
            return true;
        }
    },

    /**
     * 调度失败
     *
     * 已过期。DispatchFailure 是枚举设计初期定义的，由于考虑到没什么实际用处，所以暂时不删除只标记为过期。
     */
    @Deprecated
    DispatchFailure(17) {
        @Override
        public boolean isDispatchFailure() {
            return true;
        }
    },

    /**
     * 调度异常
     *
     * 已过期。DispatchException 是枚举设计初期定义的，由于考虑到没什么实际用处，所以暂时不删除只标记为过期。
     */
    @Deprecated
    DispatchException(18) {
        @Override
        public boolean isDispatchException() {
            return true;
        }
    },

    /**
     * 调度成功
     */
    DispatchSuccess(19) {
        @Override
        public boolean isDispatchSuccess() {
            return true;
        }
    },

    /**
     * 准备就绪执行
     */
    ExecuteReady(20) {
        @Override
        public boolean isExecuteReady() {
            return true;
        }
    },

    /**
     * 执行忽略
     */
    ExecuteIgnore(22) {
        public boolean isExecuteIgnore() {
            return true;
        }
    },

    /**
     * 执行延后
     */
    ExecuteDelay(26) {
        @Override
        public boolean isExecuteDelay() {
            return true;
        }
    },

    /**
     * 同步失败
     */
    ExecuteFailure(27) {
        @Override
        public boolean isExecuteFailure() {
            return true;
        }
    },

    /**
     * 同步异常
     */
    ExecuteException(28) {
        @Override
        public boolean isExecuteException() {
            return true;
        }
    },

    /**
     * 同步成功
     */
    ExecuteSuccess(29) {
        @Override
        public boolean isExecuteSuccess() {
            return true;
        }
    },




    ;

    private int value;

    TaskSyncState(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static TaskSyncState valueOf(int value) {
        switch (value) {
            case 0:
                return None;
            case 10:
                return ToBeDispatched;
            case 12:
                return DispatchRepeatIgnore;
            case 13:
                return DispatchOutdatedIgnore;
            case 17:
                return DispatchFailure;
            case 18:
                return DispatchException;
            case 19:
                return DispatchSuccess;
            case 20:
                return ExecuteReady;
            case 26:
                return ExecuteDelay;
            case 27:
                return ExecuteFailure;
            case 28:
                return ExecuteException;
            case 29:
                return ExecuteSuccess;
        }

        return None;
    }

    public boolean isNone() {
        return false;
    }

    public boolean isToBeDispatched() {
        return false;
    }

    public boolean isDispatchRepeatIgnore() {
        return false;
    }

    public boolean isDispatchOutdatedIgnore() {
        return false;
    }

    public boolean isExecuteReady() {
        return false;
    }

    public boolean isExecuteIgnore() {
        return false;
    }

    public boolean isExecuteDelay() {
        return false;
    }

    public boolean isDispatchFailure() {
        return false;
    }

    public boolean isDispatchException() {
        return false;
    }

    public boolean isDispatchSuccess() {
        return false;
    }

    public boolean isExecuteFailure() {
        return false;
    }

    public boolean isExecuteException() {
        return false;
    }

    public boolean isExecuteSuccess() {
        return false;
    }
}
