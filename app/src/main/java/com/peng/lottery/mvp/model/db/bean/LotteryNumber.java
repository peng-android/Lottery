package com.peng.lottery.mvp.model.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class LotteryNumber {

    @Id(autoincrement = true)
    private Long Id;
    @NotNull
    private Long lotteryId;//设计此字段，然后在Card中引用它，以便于Card来识别它

    // 号码
    private String numberValue;
    // 类型
    private String numberType;
    @Generated(hash = 1845375310)
    public LotteryNumber(Long Id, @NotNull Long lotteryId, String numberValue,
            String numberType) {
        this.Id = Id;
        this.lotteryId = lotteryId;
        this.numberValue = numberValue;
        this.numberType = numberType;
    }
    @Generated(hash = 1069411732)
    public LotteryNumber() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public Long getLotteryId() {
        return this.lotteryId;
    }
    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }
    public String getNumberValue() {
        return this.numberValue;
    }
    public void setNumberValue(String numberValue) {
        this.numberValue = numberValue;
    }
    public String getNumberType() {
        return this.numberType;
    }
    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }
}
