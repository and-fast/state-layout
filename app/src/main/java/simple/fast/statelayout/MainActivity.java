package simple.fast.statelayout;

import android.os.Bundle;
import android.view.View;

import and.fast.statelayout.StateLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private StateLayout mStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStateLayout = findViewById(R.id.state_layout);
    }

    public void onLoading(View view) {
        mStateLayout.showStateView(StateLayout.LOADING_STATE);
    }

    public void onFailure(View view) {
        mStateLayout.showStateView(StateLayout.FAILURE_STATE, "错误了");
    }

    public void onOffline(View view) {
        mStateLayout.showStateView(StateLayout.OFFLINE_STATE);
    }

    public void onEmpty(View view) {
        mStateLayout.showStateView(StateLayout.EMPTY_STATE, "无数据");
    }

    public void onSuccess(View view) {
        mStateLayout.showContentView();
    }
}
