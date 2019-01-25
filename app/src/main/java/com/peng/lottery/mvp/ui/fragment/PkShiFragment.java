package com.peng.lottery.mvp.ui.fragment;

import android.support.design.button.MaterialButton;
import android.support.v7.widget.CardView;
import android.view.View;

import com.peng.lottery.R;
import com.peng.lottery.app.utils.ToastUtil;
import com.peng.lottery.app.widget.LotteryLayout;
import com.peng.lottery.base.BaseFragment;
import com.peng.lottery.mvp.model.db.bean.LotteryNumber;
import com.peng.lottery.mvp.presenter.fragment.PkShiPresenter;

import java.util.List;

import butterknife.BindView;

import static com.peng.lottery.base.BaseLotteryPresenter.LotteryType.LOTTERY_TYPE_PKSHI;

/**
 * Created by Peng on 2019/01/24.
 * 北京PK10页面
 */
public class PkShiFragment extends BaseFragment<PkShiPresenter> {

    @BindView(R.id.bt_get_random_number)
    MaterialButton btGetRandomNumber;
    @BindView(R.id.layout_lottery_number)
    CardView layoutLotteryNumber;
    @BindView(R.id.layout_lottery_pkshi)
    LotteryLayout layoutPkShi;
    @BindView(R.id.bt_save_lottery_number)
    MaterialButton btSaveLotteryNumber;

    private List<LotteryNumber> mLotteryValue;

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_pkshi;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initListener() {
        btGetRandomNumber.setOnClickListener(v -> {
            checkShowLayout();
            mLotteryValue = mPresenter.getRandomLottery();
            layoutPkShi.setLotteryValue(mLotteryValue, LOTTERY_TYPE_PKSHI.type);
        });
        btSaveLotteryNumber.setOnClickListener(v -> {
            mPresenter.saveLottery(mLotteryValue, LOTTERY_TYPE_PKSHI);
            ToastUtil.showToast(mActivity, "保存成功!");
        });
    }

    private void checkShowLayout() {
        if (layoutLotteryNumber.getVisibility() == View.GONE) {
            layoutLotteryNumber.setVisibility(View.VISIBLE);
        }
    }

}
