package com.example.quizapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quizapp.DBQuery;
import com.example.quizapp.Model.CategoryModel;
import com.example.quizapp.R;
import com.example.quizapp.TestActivity;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    private List<CategoryModel> categoryList;
    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView;
        if (view == null) {
            myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_layout, viewGroup, false);

        } else {
            myView = view;
        }

        myView.setOnClickListener(view1 -> {
            DBQuery.get_selected_cat_index = i;
            Intent intent = new Intent(myView.getContext(), TestActivity.class);
            myView.getContext().startActivity(intent);
        });

        TextView catName = myView.findViewById(R.id.category_name);
        TextView noOfTests = myView.findViewById(R.id.no_tests);

        catName.setText(categoryList.get(i).getName());
        noOfTests.setText(String.valueOf(categoryList.get(i).getNoOfTests() + " Tests"));

        return myView;
    }
}
