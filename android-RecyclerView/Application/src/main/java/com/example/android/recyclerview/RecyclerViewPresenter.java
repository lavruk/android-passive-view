package com.example.android.recyclerview;

public class RecyclerViewPresenter {
    private static final int DATASET_COUNT = 60;

    private final RecyclerViewScreen mScreen;
    private String[] mDataset;

    public RecyclerViewPresenter(RecyclerViewScreen screen) {
        mScreen = screen;
    }

    public void onScreenCreated() {
        initDataset();
        mScreen.setItemCount(mDataset.length);
    }

    public void onAdapterBindViewHolder(int position, RecyclerViewScreen.ViewHolder viewHolder) {
        viewHolder.setText(mDataset[position]);
    }

    public void onLinearLayoutRadioButtonPressed() {
        mScreen.setLayout(RecyclerViewScreen.Layout.LINEAR);
    }

    public void onGridLayoutRadioButtonPressed() {
        mScreen.setLayout(RecyclerViewScreen.Layout.GRID);
    }

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }

}
