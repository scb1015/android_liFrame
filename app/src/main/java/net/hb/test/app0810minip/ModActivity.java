package net.hb.test.app0810minip;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModActivity extends Activity implements View.OnClickListener
{
    int nowData = 0;

    EditText etTitle, etContent;
    TextView tvId, tvDate;

    int mid;
    String mtitle;
    String mdate;
    String mcontent;

    Button btn_mod, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);

        tvId = findViewById(R.id.tvId);
        etTitle = findViewById(R.id.etTitle);
        tvDate = findViewById(R.id.tvDate);
        etContent = findViewById(R.id.etContent);

        btn_mod = findViewById(R.id.btn_mod);
        btn_exit = findViewById(R.id.btn_exit);

        Intent it = getIntent();

        String sId = it.getExtras().getString("sendId");
        String sTitle = it.getExtras().getString("sendTitle");
        String sDate = it.getExtras().getString("sendDate");
        String sContent = it.getExtras().getString("sendContent");

        tvId.setText(sId);
        etTitle.setText(sTitle);
        tvDate.setText(sDate);
        etContent.setText(sContent);

        btn_exit.setOnClickListener(this);
        btn_mod.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int sel = view.getId();

        if (sel == R.id.btn_exit)
        {
            finish();
        }
        else if(sel == R.id.btn_mod)
        {
            try{
                mid = Integer.parseInt(tvId.getText().toString());
                mtitle = etTitle.getText().toString();
                mcontent = etContent.getText().toString();

                String sql = String.format("UPDATE reviewTB SET dTitle = '%s', dContent = '%s' WHERE dId = %d", mtitle, mcontent, mid);

                DBManager dbm = new DBManager(getApplicationContext());
                SQLiteDatabase sdb = dbm.getWritableDatabase();

                sdb.execSQL(sql);
                dbm.close();
            }catch (SQLiteException ex){}

            Intent it = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(it);
        }
    }
}
