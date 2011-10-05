package com.example.api;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;


/**
 * Demonstrates sample expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class ExpandleList2 extends ExpandableListActivity {

    ExpandableListAdapter mAdapter;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // Set up our adapter
        mAdapter = new MyExpandableListAdapter();
        setListAdapter(mAdapter);
        registerForContextMenu(getExpandableListView());
        
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample menu");
        //menu.add(0, 0, 0, R.string.expandable_list_sample_action);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();
        
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
                    Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return false;
    }

    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids. 
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
//        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };
    	 private String[] groups = { "Table","Paragraph1", "Paragraph2" };
    	 String[][] mParaData ={ {""},
    	 { "skdlcnsdlknclkdsnc kjsdnckjnsdc jnsdkcjnsdv kjnkjsdnv"},
    	 {"jzsbckjbsdzkljvhbdslkjvblkjdsv ljsdnvlkjsndvlkjndsv kjsndvkjnsdvkjsd"}};
//        private String[][] children = {
//                { "Arnold", "Barry", "Chuck", "David" },
//                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
//                { "Fluffy", "Snuggles" },
//                { "Goldy", "Bubbles" }
//        };
//    	 private String[][] children = {
//                 { "Arnold" },
//                 { "Ace"}
//               
//         };
//        
    	 private Integer[][] children = {
                 { R.layout.table },
                 { R.layout.childview},
                 { R.layout.childview}
               
         };
    	 public String getPara(int groupPosition, int childPosition) {
             return mParaData[groupPosition][childPosition];
         }
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }
        public View getChildView()
        {
        	LayoutInflater mInflater = LayoutInflater.from(mContext);
        	View lView = mInflater.inflate(R.layout.childview, null);
        	return lView;
        }
        public View getChildView(int groupPosition, int childPosition)
        {
        	LayoutInflater mInflater = LayoutInflater.from(mContext);
        	View lView = mInflater.inflate((Integer)getChild(groupPosition, childPosition), null);
        	return lView;
        }
        public View getGenericView() {
            // Layout parameters for the ExpandableListView
        	LayoutInflater mInflater = LayoutInflater.from(mContext);
        	View lView = mInflater.inflate(R.layout.main, null);
            return lView;
        }
        
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            View lView = getChildView(groupPosition, childPosition);
            TextView lTextView = (TextView)lView.findViewById(R.id.textView1);
            if (lTextView != null)
            lTextView.setText(getPara(groupPosition, childPosition));
            return lView;
        }

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            View lView = getGenericView();
            TextView lTextView = (TextView)lView.findViewById(R.id.textid);
            lTextView.setText(getGroup(groupPosition).toString());
            return lView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }
}
