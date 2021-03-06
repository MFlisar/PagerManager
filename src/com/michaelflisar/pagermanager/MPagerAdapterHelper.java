package com.michaelflisar.pagermanager;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;

public class MPagerAdapterHelper<Frag extends Fragment & IPagerFragment>
{
    private IPagerAdapterCallback<Frag> mCallback = null;
    private SparseArray<Frag> mPageFragments = new SparseArray<Frag>();

    public MPagerAdapterHelper(IPagerAdapterCallback<Frag> callback)
    {
        mCallback = callback;
    }

    public Frag getItem(int index)
    {
        Frag myFragment = tryGetFragment(index);
        if (myFragment == null)
        {
            myFragment = mCallback.createFragment(index);
            mPageFragments.put(index, myFragment);
        }
        return myFragment;
    }

    public void instantiateItem(int position, Frag frag)
    {
        mPageFragments.append(position, frag);
    }

    public void destroyItem(int position)
    {
        mPageFragments.remove(position);
    }

    @SuppressWarnings("unchecked")
    public int getItemPosition(Object item)
    {
        int pos = ((Frag) item).getPagerIndex();
        if (tryGetFragment(pos) != null)
            return pos;
        // otherwise get a new fragment
        return PagerAdapter.POSITION_NONE;
    }

    public Frag tryGetFragment(int pos)
    {
        return mPageFragments.get(pos);
    }
}
