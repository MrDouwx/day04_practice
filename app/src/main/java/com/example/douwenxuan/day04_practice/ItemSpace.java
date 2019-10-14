package com.example.douwenxuan.day04_practice;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by douwenxuan on 2019/6/18.
 */

public class ItemSpace extends RecyclerView.ItemDecoration {
    private int space;

    public ItemSpace(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;

    }

}
