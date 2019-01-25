package com.peng.lottery.mvp.ui.fragment;

import android.support.design.button.MaterialButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;

import com.peng.lottery.R;
import com.peng.lottery.app.utils.ToastUtil;
import com.peng.lottery.app.widget.LotteryLayout;
import com.peng.lottery.base.BaseFragment;
import com.peng.lottery.mvp.model.db.bean.LotteryNumber;
import com.peng.lottery.mvp.presenter.fragment.DaLeDouPresenter;
import com.peng.lottery.mvp.ui.activity.WebActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;

import static com.peng.lottery.base.BaseLotteryPresenter.LotteryType.LOTTERY_TYPE_DALETOU;

/**
 * Created by Peng on 2019/01/24.
 * 大乐透页面
 */
public class DaLeDouFragment extends BaseFragment<DaLeDouPresenter> {

    @BindView(R.id.et_lucky_str)
    MaterialEditText etLuckyStr;
    @BindView(R.id.bt_get_lucky_number)
    MaterialButton btGetLuckyNumber;
    @BindView(R.id.bt_get_random_number)
    MaterialButton btGetRandomNumber;
    @BindView(R.id.bt_lottery_record)
    MaterialButton btLotteryRecord;
    @BindView(R.id.layout_lottery_number)
    CardView layoutLotteryNumber;
    @BindView(R.id.layout_lottery_daletou)
    LotteryLayout layoutDaLeTou;
    @BindView(R.id.bt_save_lottery_number)
    MaterialButton btSaveLotteryNumber;

    private String isLucky;
    private List<LotteryNumber> mLotteryValue;

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_daletou;
    }

    @Override
    protected void initListener() {
        btGetLuckyNumber.setOnClickListener(v -> {
            String luckyStr = etLuckyStr.getText().toString().trim();
            if (TextUtils.isEmpty(luckyStr)) {
                ToastUtil.showToast(mActivity, "请先输入一些内容吧！");
                return;
            }

            isLucky = "幸运号码";
            checkShowLayout();
            mLotteryValue = mPresenter.getLotteryNumber(true, luckyStr);
            layoutDaLeTou.setLotteryValue(mLotteryValue, LOTTERY_TYPE_DALETOU.type);
        });
        btGetRandomNumber.setOnClickListener(v -> {
            isLucky = "";
            checkShowLayout();
            mLotteryValue = mPresenter.getRandomLottery();
            layoutDaLeTou.setLotteryValue(mLotteryValue, LOTTERY_TYPE_DALETOU.type);
        });
        btLotteryRecord.setOnClickListener(v -> {
            String url = "http://kaijiang.500.com/dlt.shtml";
            WebActivity.start(mActivity, url);
        });
        btSaveLotteryNumber.setOnClickListener(v -> {
            mPresenter.saveLottery(mLotteryValue, LOTTERY_TYPE_DALETOU, isLucky);
            ToastUtil.showToast(mActivity, "保存成功!");
        });
    }

    private void checkShowLayout() {
        if (layoutLotteryNumber.getVisibility() == View.GONE) {
            layoutLotteryNumber.setVisibility(View.VISIBLE);
        }
    }
}
