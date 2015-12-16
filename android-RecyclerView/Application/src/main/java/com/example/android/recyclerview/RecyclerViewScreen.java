package com.example.android.recyclerview;

public interface RecyclerViewScreen {

    interface ViewHolder {
        void setText(String text);
    }

    void setItemCount(int count);

    enum Layout {
        GRID, LINEAR
    }

    void setLayout(Layout layout);
}
