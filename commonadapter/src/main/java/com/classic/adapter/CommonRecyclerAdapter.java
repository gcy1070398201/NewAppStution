package com.classic.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.classic.adapter.interfaces.IAdapter;
import com.classic.adapter.interfaces.IData;
import com.classic.adapter.interfaces.IScrollHideListener;

import java.util.ArrayList;
import java.util.List;


import static com.classic.adapter.BaseAdapterHelper.get;

/**
 * 项目名称: CommonAdapter
 * 包 名 称: com.classic.adapter
 *
 * 类 描 述: 通用Adapter,适用于RecyclerView,简化大量重复代码
 * 创 建 人: 续写经典
 * 创建时间: 2016/1/27 17:50.
 */
@SuppressWarnings({"WeakerAccess", "unused"}) public abstract class CommonRecyclerAdapter<T>
        extends RecyclerView.Adapter<CommonRecyclerAdapter.RecyclerViewHolder> implements IAdapter<T>, IData<T> {

    private final ArrayList<Integer>           mChildViewIds       = new ArrayList<>();
    private final ArrayList<ChildViewListener> mChildViewListeners = new ArrayList<>();

    private final Context mContext;
    private final int     mLayoutResId;
    private final List<T> mData;

    private OnItemClickListener     mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    public CommonRecyclerAdapter(@NonNull Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public CommonRecyclerAdapter(@NonNull Context context, int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.mContext = context;
        this.mLayoutResId = layoutResId;
    }

    @Override public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseAdapterHelper helper = get(mContext, null, parent, viewType, -1);
        return new RecyclerViewHolder(helper);
    }

    @Override public void onBindViewHolder(CommonRecyclerAdapter.RecyclerViewHolder holder, int position) {
        BaseAdapterHelper helper = holder.mAdapterHelper;
        helper.setAssociatedObject(getItem(position));
        applyChildViewListeners(helper, holder.getAdapterPosition());
        onUpdate(helper, getItem(position), position);
    }

    @Override public void onBindViewHolder(CommonRecyclerAdapter.RecyclerViewHolder holder, int position,
                                           List<Object> payloads) {
        if (null != payloads && payloads.size() > 0) {
            BaseAdapterHelper helper = holder.mAdapterHelper;
            helper.setAssociatedObject(getItem(position));
            onItemContentChanged(helper, payloads);
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override public int getItemViewType(int position) {
        return getLayoutResId(getItem(position), position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public int getItemCount() {
        return mData.size();
    }

    @Override public int getLayoutResId(T item, int position) {
        return this.mLayoutResId;
    }

    @Override public List<T> getData() {
        return mData;
    }

    @Override public void add(@NonNull T item) {
        mData.add(item);
        notifyItemInserted(mData.size());
    }

    @Override public void addAll(@NonNull List<T> list) {
        mData.addAll(list);
        notifyItemRangeInserted(mData.size() - list.size(), list.size());
    }

    @Override public void set(@NonNull T oldItem, @NonNull T newItem) {
        set(mData.indexOf(oldItem), newItem);
    }

    @Override public void set(int index, @NonNull T item) {
        if (index >= 0 && index < getItemCount()) {
            mData.set(index, item);
            notifyItemChanged(index);
        }
    }

    @Override public void remove(@NonNull T item) {
        remove(mData.indexOf(item));
    }

    @Override public void remove(int index) {
        if (index >= 0 && index < getItemCount()) {
            mData.remove(index);
            notifyItemRemoved(index);
        }
    }

    @Override public void replaceAll(@NonNull List<T> item) {
        replaceAll(item, true);
    }

    @Override public boolean contains(@NonNull T item) {
        return mData.contains(item);
    }

    @Override public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void replaceAll(@NonNull List<T> elem, boolean notifyDataSetChanged) {
        mData.clear();
        mData.addAll(elem);
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }

    /**
     * 更小粒度的更新，比如某个对象的某个属性值改变了，只改变此属性
     *
     * <pre>
     * 此回调执行的前提是：
     * 使用 {@link android.support.v7.util.DiffUtil.Callback} 进行数据更新，
     * 并且重写了 {@link android.support.v7.util.DiffUtil.Callback#getChangePayload} 方法
     * 使用方法见：<a href="https://github.com/qyxxjd/CommonAdapter/blob/master/app/src/main/java/com/classic/adapter/simple/activity/RecyclerViewSimpleActivity.java">RecyclerViewSimpleActivity</a>
     * </pre>
     *
     * @param helper
     * @param payloads
     */
    public void onItemContentChanged(@NonNull BaseAdapterHelper helper, @NonNull List<Object> payloads) { }

    public T getItem(int position) {
        return position < 0 || position >= mData.size() ? null : mData.get(position);
    }

    public CommonRecyclerAdapter<T> setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
        return this;
    }

    public CommonRecyclerAdapter<T> setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
        return this;
    }

    public CommonRecyclerAdapter<T> addChildViewListener(@IdRes int viewId, @NonNull ChildViewListener listener) {
        mChildViewIds.add(viewId);
        mChildViewListeners.add(listener);
        return this;
    }

    private void applyChildViewListeners(BaseAdapterHelper helper, final int position) {
        if (null == helper || mChildViewIds.size() == 0) {
            return;
        }
        int size = mChildViewIds.size();
        for (int i = 0; i < size; i++) {
            View view = helper.getView(mChildViewIds.get(i));
            final ChildViewListener listener = mChildViewListeners.get(i);
            if (null == view || null == listener) {
                continue;
            }
            if (listener instanceof OnChildViewClickListener) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        ((OnChildViewClickListener)listener).onItemClick(v, position);
                    }
                });
            } else if (listener instanceof OnChildViewLongClickListener) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override public boolean onLongClick(View v) {
                        return ((OnChildViewLongClickListener)listener).onItemLongClick(v, position);
                    }
                });
            } else if (listener instanceof OnChildViewTouchListener) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override public boolean onTouch(View v, MotionEvent event) {
                        return ((OnChildViewTouchListener)listener).onTouch(v, event, position);
                    }
                });
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    public interface ChildViewListener { }

    public interface OnChildViewClickListener extends ChildViewListener {
        void onItemClick(View view, int position);
    }

    public interface OnChildViewLongClickListener extends ChildViewListener {
        boolean onItemLongClick(View view, int position);
    }

    public interface OnChildViewTouchListener extends ChildViewListener {
        boolean onTouch(View v, MotionEvent event, int position);
    }

    protected final class RecyclerViewHolder extends RecyclerView.ViewHolder {
        BaseAdapterHelper mAdapterHelper;

        public RecyclerViewHolder(BaseAdapterHelper adapterHelper) {
            super(adapterHelper.getView());
            this.mAdapterHelper = adapterHelper;
            if (null != mItemClickListener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mItemClickListener.onItemClick(RecyclerViewHolder.this, v, getAdapterPosition());
                    }
                });
            }
            if (null != mItemLongClickListener) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override public boolean onLongClick(View v) {
                        return mItemLongClickListener.onItemLongClick(RecyclerViewHolder.this, v, getAdapterPosition());
                    }
                });
            }
        }
    }

    public static abstract class AbsScrollControl extends RecyclerView.OnScrollListener implements IScrollHideListener {
        private static final int DEFAULT_SCROLL_HIDE_OFFSET = 20; //滑动隐藏的偏移量

        private int     mCurrentScrollOffset;
        private boolean isControlVisible;

        /**
         * 自定义LayoutManager需要实现此方法
         */
        protected int getFirstVisibleItemPositions() {
            return 0;
        }

        /** 获取滑动隐藏的偏移量 */
        protected int getScrollHideOffset() {
            return DEFAULT_SCROLL_HIDE_OFFSET;
        }

        @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            final int firstVisibleItemPosition = findFirstVisibleItemPosition(recyclerView.getLayoutManager());

            if (firstVisibleItemPosition == 0 && !isControlVisible) {
                onShow();
                isControlVisible = true;
            } else if (firstVisibleItemPosition != 0 && mCurrentScrollOffset > getScrollHideOffset() &&
                       isControlVisible) {
                //向上滚动,并且视图为显示状态
                onHide();
                isControlVisible = false;
                mCurrentScrollOffset = 0;
            } else if (firstVisibleItemPosition != 0 && mCurrentScrollOffset < -getScrollHideOffset() &&
                       !isControlVisible) {
                //向下滚动,并且视图为隐藏状态
                onShow();
                isControlVisible = true;
                mCurrentScrollOffset = 0;
            }

            //dy>0:向上滚动
            //dy<0:向下滚动
            if ((isControlVisible && dy > 0) || (!isControlVisible && dy < 0)) {
                mCurrentScrollOffset += dy;
            }
        }

        private int findFirstVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
            if (layoutManager instanceof GridLayoutManager) {
                return ((GridLayoutManager)layoutManager).findFirstVisibleItemPosition();
            } else if (layoutManager instanceof LinearLayoutManager) {
                return ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                return ((StaggeredGridLayoutManager)layoutManager).findFirstVisibleItemPositions(null)[0];
            } else {
                return getFirstVisibleItemPositions();
            }
        }
    }
}
