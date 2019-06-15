package uiza.test.android.uiza_sdk_player_2_4_0.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;

import uiza.test.android.uiza_sdk_player_2_4_0.R;
import uiza.test.android.uiza_sdk_player_2_4_0.app.LSApplication;
import vn.uiza.core.base.BaseActivity;
import vn.uiza.core.common.Constants;
import vn.uiza.core.utilities.LAnimationUtil;
import vn.uiza.core.utilities.LScreenUtil;
import vn.uiza.core.utilities.LUIUtil;
import vn.uiza.restapi.uiza.model.v2.listallentity.Item;
import vn.uiza.restapi.uiza.model.v3.linkplay.getlinkplay.ResultGetLinkPlay;
import vn.uiza.restapi.uiza.model.v3.metadata.getdetailofmetadata.Data;
import vn.uiza.uzv3.util.UZUtil;
import vn.uiza.uzv3.view.rl.video.UZCallback;
import vn.uiza.uzv3.view.rl.video.UZVideo;
import vn.uiza.views.LToast;


public class CustomSkinXMLActivity extends BaseActivity implements UZCallback {
    private UZVideo uzVideo;

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return "TAG" + getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_uiza_custom_skin_xml;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        UZUtil.setCasty(this);
        UZUtil.setCurrentPlayerId(R.layout.uiza_controller_skin_custom_main);
        super.onCreate(savedInstanceState);
        uzVideo = findViewById(R.id.uiza_video);
        uzVideo.setUZCallback(this);

        final String entityId = LSApplication.entityIdDefaultVOD;
        UZUtil.initEntity(activity, uzVideo, entityId);

        findViewById(R.id.bt_change_skin_custom).setOnClickListener(v -> {
            if (uzVideo != null) {
                uzVideo.changeSkin(R.layout.uiza_controller_skin_custom_main);
            }
        });
        findViewById(R.id.bt_change_skin_0).setOnClickListener(v -> {
            if (uzVideo != null) {
                uzVideo.changeSkin(R.layout.uz_player_skin_0);
            }
        });
        findViewById(R.id.bt_change_skin_1).setOnClickListener(v -> {
            if (uzVideo != null) {
                uzVideo.changeSkin(R.layout.uz_player_skin_1);
            }
        });
        findViewById(R.id.bt_change_skin_2).setOnClickListener(v -> {
            if (uzVideo != null) {
                uzVideo.changeSkin(R.layout.uz_player_skin_2);
            }
        });
        findViewById(R.id.bt_change_skin_3).setOnClickListener(v -> {
            if (uzVideo != null) {
                uzVideo.changeSkin(R.layout.uz_player_skin_3);
            }
        });

        handleClickSampeText();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uzVideo.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        uzVideo.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uzVideo.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        uzVideo.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        uzVideo.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (resultCode == Activity.RESULT_OK) {
                uzVideo.initializePiP();
            } else {
                LToast.show(activity, "Draw over other app permission not available");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void isInitResult(boolean isInitSuccess, boolean isGetDataSuccess, ResultGetLinkPlay resultGetLinkPlay, Data data) {
        if (isInitSuccess) {
            uzVideo.setEventBusMsgFromActivityIsInitSuccess();
        }
    }

    @Override
    public void onClickListEntityRelation(Item item, int position) {
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickPip(Intent intent) {
    }

    @Override
    public void onClickPipVideoInitSuccess(boolean isInitSuccess) {
        if (isInitSuccess) {
            onBackPressed();
        }
    }

    @Override
    public void onSkinChange() {
        handleClickSampeText();
    }

    @Override
    public void onError(Exception e) {
    }

    @Override
    public void onBackPressed() {
        if (LScreenUtil.isFullScreen(activity)) {
            uzVideo.toggleFullscreen();
        } else {
            super.onBackPressed();
        }
    }

    private void handleClickSampeText() {
        TextView tvSample = uzVideo.getPlayerView().findViewById(R.id.tv_sample);
        if (tvSample != null) {
            tvSample.setText("This is a view from custom skin.\nTry to tap me.");
            LUIUtil.setTextShadow(tvSample);
            tvSample.setOnClickListener(v -> {
                LAnimationUtil.play(v, Techniques.Pulse);
                LToast.show(activity, "Click!");
            });
        }
    }
}
