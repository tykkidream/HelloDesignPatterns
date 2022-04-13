package hello.designpatterns.batch.task;

import java.util.Iterator;
import java.util.List;

public class TaskBatchExecutionIterator implements Iterable<TaskBatchExecutionRecord>, Iterator<TaskBatchExecutionRecord> {

    private List<TaskBatchExecutionRecord> records = null;

    private int index = 0;

    private int size = 0;

    private TaskBatchExecutionRecord hasNext = null;

    public TaskBatchExecutionIterator(List<TaskBatchExecutionRecord> records) {
        this.records = records;
        this.size = records.size();
    }

    @Override
    public Iterator<TaskBatchExecutionRecord> iterator() {
        index = 0;
        return this;
    }

    @Override
    public boolean hasNext() {
        if (hasNext != null) {
            return true;
        }

        do {
            if (index >= size) {
                return false;
            }

            TaskBatchExecutionRecord record = records.get(index);

            index++;

            if (record == null) {
                continue;
            }

            TaskSyncState state = record.getState();

            if (state == null) {
                hasNext = record;
                break;
            } else if (state.isDispatchFailure()) {
                continue;
            } else if (state.isExecuteFailure()) {
                continue;
            } else if (state.isExecuteSuccess()) {
                continue;
            } else {
                hasNext = record;
                break;
            }
        } while (true);

        return hasNext != null;
    }

    @Override
    public TaskBatchExecutionRecord next() {
        if (hasNext()) {
            TaskBatchExecutionRecord record = hasNext;

            hasNext = null;

            return record;
        }

        return null;
    }
}
