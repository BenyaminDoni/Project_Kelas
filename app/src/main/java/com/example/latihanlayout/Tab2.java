package com.example.latihanlayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Tab2 extends Fragment {
    private SQLiteDatabase mydb ;
    private RecyclerView rv;
    private EditText isinim;
    private RecycleViewdb la;
    private EditText isinama;
    private Button insert;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_2,container,false);
        rv = v.findViewById(R.id.listdb);
        la = new RecycleViewdb(getContext(),getAllItem());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(la);
        isinim = v.findViewById(R.id.insertnim);
        isinama = v.findViewById(R.id.insertnama);
        insert = v.findViewById(R.id.tombolinsert);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ContentValues contentValues = new ContentValues();
                contentValues.put("nim", isinim.getText().toString());
                contentValues.put("nama", isinama.getText().toString());
                mydb.insert("mahasiswa",null,contentValues);
                la.swapCursor(getAllItem());
                Toast.makeText(getActivity(),"Insert Berhasil",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DataHelper dataHelper = new DataHelper(getContext());
        mydb = dataHelper.getReadableDatabase();
    }

    private Cursor getAllItem(){
        return mydb.query(
                DataMahasiswa.mahasiswaEntry.TABLE_NAME,
                null,null,null,null,null,null
        );
    }
}
