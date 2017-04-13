package com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cwenhui.domain.model.NoteBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by bpncool on 2/23/2016.
 */
public class SectionedExpandableLayoutHelper implements SectionStateChangeListener {

    //data list
    private LinkedHashMap<Section, ArrayList<NoteBook>> mSectionDataMap = new LinkedHashMap<Section,
            ArrayList<NoteBook>>();
    private ArrayList<Object> mDataArrayList = new ArrayList<Object>();

    //section map
    //TODO : look for a way to avoid this
    private HashMap<String, Section> mSectionMap = new HashMap<String, Section>();

    //adapter
    private SectionedExpandableGridAdapter mSectionedExpandableGridAdapter;

    //recycler view
    RecyclerView mRecyclerView;

    public SectionedExpandableLayoutHelper(Context context, RecyclerView recyclerView, ItemClickListener
            itemClickListener,
                                           int gridSpanCount) {

        //setting the recycler view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, gridSpanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        mSectionedExpandableGridAdapter = new SectionedExpandableGridAdapter(context, mDataArrayList,
                gridLayoutManager, itemClickListener, this);
        recyclerView.setAdapter(mSectionedExpandableGridAdapter);

        mRecyclerView = recyclerView;
    }

    public void notifyDataSetChanged() {
        //TODO : handle this condition such that these functions won't be called if the recycler view is on
        // scroll
        generateDataList();
        mSectionedExpandableGridAdapter.notifyDataSetChanged();
    }

    public void addSection(NoteBook section, ArrayList<NoteBook> items) {
        Section newSection;
        mSectionMap.put(section.getName(), (newSection = new Section(section)));
        mSectionDataMap.put(newSection, items);
        if (items != null && items.size() > 0) {
            newSection.setHasChildren(true);
        }
    }

    public void addItem(String section, NoteBook item) {
        Section key = mSectionMap.get(section);
        if (item != null) {
            mSectionDataMap.get(key).add(item);
            key.setHasChildren(true);
        }
    }

    public void removeItem(String section, NoteBook item) {
        Section key = mSectionMap.get(section);
        ArrayList<NoteBook> noteBooks = mSectionDataMap.get(key);
        if (noteBooks == null || noteBooks.size() == 0) return;
        noteBooks.remove(item);
        if (noteBooks.size() == 0) {
            key.setHasChildren(false);
        }
    }

    public void removeSection(String section) {
        mSectionDataMap.remove(mSectionMap.get(section));
        mSectionMap.remove(section);
    }

    private void generateDataList() {
        mDataArrayList.clear();
        for (Map.Entry<Section, ArrayList<NoteBook>> entry : mSectionDataMap.entrySet()) {
            Section key;
            mDataArrayList.add((key = entry.getKey()));
            if (key.isExpanded)
                mDataArrayList.addAll(entry.getValue());
        }
    }

    @Override
    public void onSectionStateChanged(Section section, boolean isOpen) {
        if (!mRecyclerView.isComputingLayout()) {
            section.isExpanded = isOpen;
            notifyDataSetChanged();
        }
    }
}