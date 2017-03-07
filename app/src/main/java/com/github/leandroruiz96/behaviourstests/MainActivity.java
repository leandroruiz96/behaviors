package com.github.leandroruiz96.behaviourstests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.leandroruiz96.behaviourstests.chainedfab.ChainedFabActivity;
import com.github.leandroruiz96.behaviourstests.coordpager.CoordPagerActivity;
import com.github.leandroruiz96.behaviourstests.snackfab.SnackFabActivity;
import com.github.leandroruiz96.behaviourstests.tooltipview.TooltipActivity;
import com.github.leandroruiz96.behaviourstests.zoomleft.ScrollingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExampleList = (RecyclerView) findViewById(R.id.rv_examples);
        mExampleList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mExampleList.setAdapter(new ExamplesAdapter());
        mExampleList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private class ExamplesAdapter extends RecyclerView.Adapter<ExampleItem> {

        List<Example> mExamples = new ArrayList<>();

        ExamplesAdapter() {
            mExamples.add(new Example(ScrollingActivity.class,ScrollingActivity.DESCRIPTION));
            mExamples.add(new Example(SnackFabActivity.class,SnackFabActivity.DESCRIPTION));
            mExamples.add(new Example(TooltipActivity.class,TooltipActivity.DESCRIPTION));
            mExamples.add(new Example(ChainedFabActivity.class,ChainedFabActivity.DESCRIPTION));
            mExamples.add(new Example(CoordPagerActivity.class,CoordPagerActivity.DESCRIPTION));
        }

        @Override
        public ExampleItem onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
            return new ExampleItem(v);
        }

        @Override
        public void onBindViewHolder(ExampleItem holder, int position) {
            holder.bind(mExamples.get(position));
        }

        @Override
        public int getItemCount() {
            return mExamples.size();
        }
    }

    private class ExampleItem extends RecyclerView.ViewHolder {

        TextView description;

        ExampleItem(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.text);
        }

        void bind(final Example example) {
            description.setText(example.getDescription());
            description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,example.getSelfClass()));
                }
            });
        }
    }

    private class Example {
        Class<?> mClass;
        String mDescription;

        Example(Class<?> mClass, String mDescription) {
            this.mClass = mClass;
            this.mDescription = mDescription;
        }

        Class<?> getSelfClass() {
            return mClass;
        }

        String getDescription() {
            return mDescription;
        }
    }
}
