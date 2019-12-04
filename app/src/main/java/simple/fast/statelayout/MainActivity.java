package simple.fast.statelayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.Objects;

import and.fast.statelayout.OnAnewRequestNetworkListener;
import and.fast.statelayout.StateLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnAnewRequestNetworkListener {

    private StateLayout mStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStateLayout = findViewById(R.id.state_layout);
        mStateLayout.setOnAnewRequestNetworkListener(this);
    }

    public void onLoading(View view) {
        mStateLayout.showStateView(StateLayout.LOADING_STATE);
    }

    public void onFailure(View view) {
        mStateLayout.showStateView(StateLayout.FAILURE_STATE, "JSON解析失败");
    }

    public void onOffline(View view) {
        mStateLayout.showStateView(StateLayout.OFFLINE_STATE);
    }

    public void onEmpty(View view) {
        mStateLayout.showStateView(StateLayout.EMPTY_STATE, "没有收藏数据");
    }

    public void onSuccess(View view) {
        mStateLayout.showContentView();
    }

    @Override
    public void onAnewRequestNetwork() {
        new Handler(Objects.requireNonNull(Looper.myLooper()))
                .postDelayed(() -> mStateLayout.showContentView(), 1000);
    }
}
