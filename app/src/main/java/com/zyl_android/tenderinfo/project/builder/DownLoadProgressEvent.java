package com.zyl_android.tenderinfo.project.builder;

/**
 * Created by bibinet on 2017-12-13.
 */

public class DownLoadProgressEvent {
    private long mTotal;
    private long mProgress;

    public DownLoadProgressEvent(long total, long progress) {
        this.mTotal = total;
        this.mProgress = progress;
    }

    public long getTotal() {
        return this.mTotal;
    }

    public long getProgress() {
        return this.mProgress;
    }

    public boolean isNotDownloadFinished() {
        return this.mTotal != this.mProgress;
    }
}
