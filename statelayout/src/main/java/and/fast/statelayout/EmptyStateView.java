package and.fast.statelayout;

import android.text.TextUtils;
import android.widget.TextView;

public class EmptyStateView extends BaseStateView {

    private final int layoutResID;

    public EmptyStateView(int layoutResID){
        this.layoutResID = layoutResID;
    }

    @Override
    public int getLayoutId() {
        if (layoutResID == 0){
            return R.layout.view_state_layout_empty;
        }

        return layoutResID;
    }

    @Override
    public Integer getStateCode() {
        return StateLayout.EMPTY_STATE;
    }

    @Override
    public void display(CharSequence message, boolean visibility) {
        if(!TextUtils.isEmpty(message)){
            TextView textView = getStateView().findViewById(R.id.tv_empty_title);
            textView.setText(message);
        }
    }
}
