package com.fei.networdrequest.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fei.networdrequest.R;

import java.util.List;

import com.fei.networdrequest.bean.ChaptersBean;

/**
 * @author 初夏小溪
 * @date 2018/4/19 0019
 * 公众号
 */

public class OfficialAccountAdapter extends BaseQuickAdapter<ChaptersBean, BaseViewHolder> {

    public OfficialAccountAdapter(@Nullable List<ChaptersBean> data) {
        super(R.layout.item_official_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChaptersBean item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
