package and.fast.statelayout;

import android.os.Handler;
import android.os.Looper;

public class OfflineStateView extends BaseStateView {

    private final int layoutResID;

    private OnAnewRequestNetworkListener onAnewRequestNetworkListener;

    public OfflineStateView(int layoutResID) {
        this.layoutResID = layoutResID;
    }

    @Override
    public Integer getStateCode() {
        return StateLayout.OFFLINE_STATE;
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
    public void setOnAnewRequestNetworkListener(OnAnewRequestNetworkListener onAnewRequestNetworkListener) {
        this.onAnewRequestNetworkListener = onAnewRequestNetworkListener;
    }

}
