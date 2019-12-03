package and.fast.statelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Map;

import androidx.collection.ArrayMap;

public class StateLayout extends FrameLayout {

    public static final Integer FAILURE_STATE = 1;
    public static final Integer OFFLINE_STATE = 2;
    public static final Integer LOADING_STATE = 3;
    public static final Integer EMPTY_STATE   = 4;

    private int mDisplayStateLayoutCount; // 已经添加状态布局数量

    private Map<Integer, NetworkState> mStateMap = new ArrayMap<>();

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StateLayout);

        // 无网络
        addStateView(new OfflineStateView(ta.getResourceId(R.styleable.StateLayout_offline_res,
                R.layout.view_state_layout_offline)));

        // 网络加载中
        addStateView(new LoadingStateView(ta.getResourceId(R.styleable.StateLayout_loading_res,
                R.layout.view_state_layout_loading)));

        // 空状态
        addStateView(new EmptyStateView(ta.getResourceId(R.styleable.StateLayout_empty_res,
                R.layout.view_state_layout_empty)));

        // 错误
        addStateView(new FailureStateView(ta.getResourceId(R.styleable.StateLayout_failure_res,
                R.layout.view_state_layout_failure)));

        ta.recycle();
    }

    public StateLayout addStateView(NetworkState networkState) {
        mStateMap.put(networkState.getStateCode(), networkState);
        return this;
    }

    public void showContentView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setVisibility(i < childCount - mDisplayStateLayoutCount ? View.VISIBLE : View.GONE);
        }
    }

    public void showStateView(int stateCode) {
        this.showStateView(stateCode, null);
    }

    public void showStateView(int stateCode, CharSequence charSequence) {
        NetworkState networkState = mStateMap.get(stateCode);

        if (networkState == null) {
            throw new IllegalStateException("没有这个状态布局，code:" + stateCode);
        }

        if (networkState.getStateView() == null) {
            ++mDisplayStateLayoutCount;
            networkState.bindParentView(this);
        }

        networkState.showView(charSequence);
    }

    public void setOnAnewRequestNetworkListener(OnAnewRequestNetworkListener onAnewRequestNetworkListener) {
        for (Map.Entry<Integer, NetworkState> networkStateEntry : mStateMap.entrySet()) {
            networkStateEntry.getValue().setOnAnewRequestNetworkListener(onAnewRequestNetworkListener);
        }
    }


    public interface NetworkState {

        Integer getStateCode();

        View getStateView();

        void bindParentView(StateLayout parentView);

        void showView(CharSequence message);

        void setOnAnewRequestNetworkListener(OnAnewRequestNetworkListener onAnewRequestNetworkListener);
    }
}
