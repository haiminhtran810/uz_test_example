package uiza.test.android.uiza_sdk_player_2_2_7.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import uiza.test.android.uiza_sdk_player_2_2_7.R;
import uiza.test.android.uiza_sdk_player_2_2_7.app.LSApplication;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LAnimationUtil;
import vn.loitp.core.utilities.LScreenUtil;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.uiza.model.v2.listallentity.Item;
import vn.loitp.restapi.uiza.model.v3.linkplay.getlinkplay.ResultGetLinkPlay;
import vn.loitp.restapi.uiza.model.v3.metadata.getdetailofmetadata.Data;
import vn.loitp.uizavideo.view.rl.video.UizaIMAVideo;
import vn.loitp.uizavideov3.util.UizaUtil;
import vn.loitp.uizavideov3.view.rl.video.UizaCallback;
import vn.loitp.uizavideov3.view.rl.video.UizaIMAVideoV3;
import vn.loitp.views.LToast;


public class CustomSkinActivity extends BaseActivity implements UizaCallback {
    private UizaIMAVideoV3 uizaIMAVideoV3;

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
        return R.layout.activity_uiza_custom_skin;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        UizaUtil.setCasty(this);
        UizaUtil.setCurrentPlayerId(R.layout.uiza_controller_skin_custom_main);
        super.onCreate(savedInstanceState);
        uizaIMAVideoV3 = findViewById(R.id.uiza_video);
        uizaIMAVideoV3.setUizaCallback(this);

        final String entityId = LSApplication.entityIdDefaultVOD;
        UizaUtil.initEntity(activity, uizaIMAVideoV3, entityId);

        findViewById(R.id.bt_change_skin_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uizaIMAVideoV3 != null) {
                    uizaIMAVideoV3.changeSkin(R.layout.uiza_controller_skin_custom_main);
                }
            }
        });
        findViewById(R.id.bt_change_skin_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uizaIMAVideoV3 != null) {
                    uizaIMAVideoV3.changeSkin(R.layout.player_skin_default);
                }
            }
        });
        findViewById(R.id.bt_change_skin_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uizaIMAVideoV3 != null) {
                    uizaIMAVideoV3.changeSkin(R.layout.player_skin_1);
                }
            }
        });
        findViewById(R.id.bt_change_skin_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uizaIMAVideoV3 != null) {
                    uizaIMAVideoV3.changeSkin(R.layout.player_skin_2);
                }
            }
        });
        findViewById(R.id.bt_change_skin_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uizaIMAVideoV3 != null) {
                    uizaIMAVideoV3.changeSkin(R.layout.player_skin_3);
                }
            }
        });

        handleClickSampeText();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uizaIMAVideoV3.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        uizaIMAVideoV3.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uizaIMAVideoV3.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        uizaIMAVideoV3.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        uizaIMAVideoV3.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UizaIMAVideo.CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (resultCode == Activity.RESULT_OK) {
                uizaIMAVideoV3.initializePiP();
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
            uizaIMAVideoV3.setEventBusMsgFromActivityIsInitSuccess();
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
            uizaIMAVideoV3.toggleFullscreen();
        } else {
            super.onBackPressed();
        }
    }

    private void handleClickSampeText() {
        TextView tvSample = uizaIMAVideoV3.getPlayerView().findViewById(R.id.tv_sample);
        if (tvSample != null) {
            tvSample.setText("This is a view from custom skin.\nTry to tap me.");
            LUIUtil.setTextShadow(tvSample);
            tvSample.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LAnimationUtil.play(v, Techniques.Pulse);
                    LToast.show(activity, "Click!");
                }
            });
        }
    }
}
