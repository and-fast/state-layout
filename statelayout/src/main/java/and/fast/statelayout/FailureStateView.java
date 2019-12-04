package and.fast.statelayout;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.TextView;

public class FailureStateView extends BaseStateView {

    private final int layoutResID;

    private OnAnewRequestNetworkListener onAnewRequestNetworkListener;

    public FailureStateView(int layoutResID){
        this.layoutResID = layoutResID;
    }

    @Override
    public Integer getStateCode() {
        return StateLayout.FAILURE_STATE;
    }

    @Override
    public int getLayoutId() {
        return layoutResID;
    }

    @Override
    public void bindParentView(StateLayout parentView) {
        super.bindParentView(parentView);
        int duration = parentView.getResources().getInteger(R.integer.anew_request_delay_millis);

        getStateView()
                .findViewById(R.id.layout_state_container)
                .setOnClickListener(view -> {
                     if (onAnewRequestNetworkListener != null){
                         parentView.showStateView(StateLayout.LOADING_STATE);
                         new Handler(Looper.myLooper()).postDelayed(() ->
                                         onAnewRequestNetworkListener.onAnewRequestNetwork(),
                                 duration);
                     }
                });
    }

    @Override
    public void display(CharSequence message, boolean visibility) {
        if(!TextUtils.isEmpty(message)){
            TextView textView = getStateView().findViewById(R.id.tv_failure_message);
            textView.setText(message);
        }
    }

    @Override
    public void setOnAnewRequestNetworkListener(OnAnewRequestNetworkListener onAnewRequestNetworkListener){
        this.onAnewRequestNetworkListener = onAnewRequestNetworkListener;
    }
}
