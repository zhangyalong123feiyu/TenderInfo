package com.zyl_android.tenderinfo.project.builder;

import rx.functions.Action1;

/**
 * Created by bibinet on 2017-11-10.
 */

public class OnLoadSucess  {
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        // onError()
        @Override
        public void call(Throwable throwable) {
            // Error handling
        }
    };
}
