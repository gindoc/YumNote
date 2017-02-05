package com.cwenhui.yumnote.widgets.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.cwenhui.yumnote.BR;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Base Data Binding RecyclerView Adapter.
 *
 * @author markzhai on 16/8/25
 */
public abstract class BaseViewAdapter<T, R> extends RecyclerView.Adapter<BindingViewHolder> {

    protected final LayoutInflater mLayoutInflater;

    protected List<T> mCollection;
    protected Presenter mPresenter;
    protected Decorator mDecorator;
    private Provider<R> provider;

    public interface Presenter {

    }

    public interface Decorator {
        void decorator(BindingViewHolder holder, int position, int viewType);
    }

    public BaseViewAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final R viewModel = provider.get();
        final T item = mCollection.get(position);
//        holder.getBinding().setVariable(BR.item, item);
//        holder.getBinding().setVariable(BR.presenter, getPresenter());
//        holder.getBinding().executePendingBindings();

        binding(holder, position, getItemViewType(position), viewModel, item);

//        if (mDecorator != null) {
//            mDecorator.decorator(holder, position, getItemViewType(position));
//        }
    }

    public abstract void binding(BindingViewHolder holder, int position, int viewType, R viewModel, T item);

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void remove(int position) {
        mCollection.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        mCollection.clear();
        notifyDataSetChanged();
    }

    public T get(int pos) {
        return mCollection.get(pos);
    }

    public void setDecorator(Decorator decorator) {
        mDecorator = decorator;
    }

    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    protected Presenter getPresenter() {
        return mPresenter;
    }
}