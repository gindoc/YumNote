package com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Section {

    private final String name;

    public boolean isExpanded;
    private boolean hasChildren;

    public Section(String name) {
        this.name = name;
        isExpanded = false;
        hasChildren = false;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getName() {
        return name;
    }
}