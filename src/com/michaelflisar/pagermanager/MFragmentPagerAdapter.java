
package com.michaelflisar.pagermanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class MFragmentPagerAdapter<Frag extends Fragment & IPagerFragment> extends FragmentPagerAdapter implements IPagerAdapterCallback<Frag>
{
    private MPagerAdapterHelper<Frag> mHelper;

    public MFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
        mHelper = new MPagerAdapterHelper<Frag>(this);
    }

    @Override
    public Fragment getItem(int index)
    {
        return (Fragment)mHelper.getItem(index);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        Object frag = super.instantiateItem(container, position);
        mHelper.instantiateItem(position, (Frag) frag);
        return frag;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        super.destroyItem(container, position, object);
        mHelper.destroyItem(position);
    }

    @Override
    public int getItemPosition(Object item)
    {
        return mHelper.getItemPosition(item);
    }

    @Override
    public Frag tryGetFragment(int key)
    {
        return mHelper.tryGetFragment(key);
    }

    @Override
    public View getPageTabCustomView(int position)
    {
        return null;
    }

    @Override
    public View getPageTabBackground(int position)
    {
        return null;
    }
}
