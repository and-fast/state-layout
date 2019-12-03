package and.fast.statelayout;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

public class EmptyStateView extends BaseStateView {

    private final int layoutResID;

    public EmptyStateView(@LayoutRes int layoutResID){
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
    public void showView(CharSequence charSequence) {
        super.showView(charSequence);
        if(!TextUtils.isEmpty(charSequence)){
            TextView textView = getStateView().findViewById(R.id.tv_empty_title);
            textView.setText(charSequence);
        }
    }

    @Override
    public Integer getStateCode() {
        return StateLayout.EMPTY_STATE;
    }
}
