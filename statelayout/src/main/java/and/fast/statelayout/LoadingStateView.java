package and.fast.statelayout;

public class LoadingStateView extends BaseStateView {

    private final int layoutResID;

    public LoadingStateView(int layoutResID){
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
