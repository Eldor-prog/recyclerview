package uz.eldor.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ExampleAdapter mAdapter;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        createExampleList();
        buildRecyclerView();
        setButton();


    }

    private void setButton() {
        buttonInsert.setOnClickListener(v -> {
            int position = Integer.parseInt(editTextInsert.getText().toString());
            insertItems(position);
            mAdapter.notifyItemInserted(position);
        });
        buttonRemove.setOnClickListener(v -> {
            int position = Integer.parseInt(editTextRemove.getText().toString());
            removeItems(position);
        });
    }

    public void changeText(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);

    }

    public void insertItems(int position) {
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android, "New Item at position " + position, "Text Line 2"));

    }

    public void removeItems(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private void initData() {
        mRecyclerView = findViewById(R.id.recyclerView);
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edit_text_insert);
        editTextRemove = findViewById(R.id.edit_text_remove);

    }

    private void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemListener(int position) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("Example Item", mExampleList.get(position));
                startActivity(intent);

            }

            @Override
            public void onDeleteClick(int position) {
                removeItems(position);
            }
        });
    }

    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_music_note, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_brightness, "Line 5", "Line 6"));

    }

}