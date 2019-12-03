package and.fast.statelayout;

import androidx.annotation.LayoutRes;

public class LoadingStateView extends BaseStateView {

    private final int layoutResID;

    public LoadingStateView(@LayoutRes int layoutResID){
        this.layoutResID = layoutResID;
    }

    @Override
    public Integer getStateCode() {
        return StateLayout.LOADING_STATE;
    }

    @Override
    public void setOnAnewRequestNetworkListener(
            OnAnewRequestNetworkListener onAnewRequestNetworkListener) {
    }

    @Override
    public int getLayoutId() {
        return layoutResID;
    }
}
