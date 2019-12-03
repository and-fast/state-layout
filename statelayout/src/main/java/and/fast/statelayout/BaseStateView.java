package and.fast.statelayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseStateView implements StateLayout.NetworkState {

    private View      mStateView;
    private ViewGroup mParentView;

    @Override
    public View getStateView() {
        return mStateView;
    }

    @Override
    public void bindParentView(StateLayout parentView) {
        this.mParentView = parentView;
        mStateView = LayoutInflater.from(parentView.getContext()).inflate(getLayoutId(), parentView, false);
        parentView.addView(mStateView);
    }

    @Override
    public void showView(CharSequence message) {
        for (int i = 0; i < mParentView.getChildCount(); i++) {
            mParentView.getChildAt(i).setVisibility(View.GONE);
        }

        mStateView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOnAnewRequestNetworkListener(
            OnAnewRequestNetworkListener onAnewRequestNetworkListener) {

    }

    public abstract int getLayoutId();
}
