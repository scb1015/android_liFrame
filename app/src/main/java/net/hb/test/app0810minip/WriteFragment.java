package net.hb.test.app0810minip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class WriteFragment extends Fragment {

    private DBManager dbm;
    String wtitle;
    String wdate;
    String wcontent;

    Button btn_s;
    EditText ed_t, ed_d, ed_c;

    SQLiteDatabase sdb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write, container, false);

        Button btn_s = v.findViewById(R.id.btn_save);

        ed_t = v.findViewById(R.id.edit_title);
        ed_d = v.findViewById(R.id.edit_date);
        ed_c = v.findViewById(R.id.edit_content);

        btn_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw)
            {
                String sSql = "";
                String ssSql = "";

                wtitle = ed_t.getText().toString();
                wdate = ed_d.getText().toString();
                wcontent = ed_c.getText().toString();

                Log.i("wtitle", wtitle);
                Log.i("wdate", wdate);
                Log.i("wcontent", wcontent);

                sSql = "insert into reviewTB (dTitle, dDate, dContent) values ('" + wtitle + "', '" + wdate + "', '" + wcontent + "');";

                try{
                   dbm = new DBManager(getActivity());
                   sdb = dbm.getWritableDatabase();
                   sdb.execSQL(sSql);

                    dbm.close();

                    //((MainActivity)getActivity()).call(0);
                    Intent it = new Intent(getContext(), MainActivity.class);
                    startActivity(it);
                }catch (SQLiteException ex){}
            }
        });
        return v;
    }//onCreateView end

}//WF class end
