package net.hb.test.app0810minip;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReadFragment extends Fragment
{
    int nowData = 0;
    Cursor cursor;
    TextView id, title, date, content;
    String rtitle, rdate, rcontent;
    int rid, numdata;
    Button btn_first, btn_pre, btn_next, btn_del, btn_mod, btn_last;

    DBManager dbm;
    SQLiteDatabase sdb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        rid = 0;
        rtitle = "";
        rdate = "";
        rcontent = "";

        View v = inflater.inflate(R.layout.fragment_read, container, false);

        id = v.findViewById(R.id.id);
        title = v.findViewById(R.id.title);
        date = v.findViewById(R.id.date);
        content = v.findViewById(R.id.content);

        btn_first = v.findViewById(R.id.btn_first);
        btn_pre = v.findViewById(R.id.btn_pre);
        btn_next = v.findViewById(R.id.btn_next);
        btn_del = v.findViewById(R.id.btn_del);
        btn_mod = v.findViewById(R.id.btn_mod);
        btn_last = v.findViewById(R.id.btn_last);

        try {
            dbm = new DBManager(getActivity());
            sdb = dbm.getReadableDatabase();
            cursor = sdb.query("reviewTB", null, null, null, null, null, null);

            numdata = cursor.getCount();
            cursor.moveToFirst();

            if (numdata == 0)
            {
                nowData = 0;
            }
            else
            {
                nowData = 1;
            }

            if (cursor.getCount() > 0)
            {
                rid = cursor.getInt(0);
                rtitle = cursor.getString(1);
                rdate = cursor.getString(2);
                rcontent = cursor.getString(3);
            }
        }catch (SQLException e){}
        finally {
            if(rid != 0) { id.setText(String.valueOf(rid)); } else { id.setText(""); }
            title.setText(rtitle);
            date.setText(rdate);
            content.setText(rcontent);

            cursor.close();
            dbm.close();
        }

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rid = 0;
                rtitle = "";
                rdate = "";
                rcontent = "";

                switch (view.getId()) {
                    case R.id.btn_first:
                    {
                        try{
                            DBManager dbm = new DBManager(getActivity());
                            SQLiteDatabase sdb = dbm.getReadableDatabase();
                            cursor = sdb.query("reviewTB", null, null, null, null, null, null);

                            if (numdata == 0 || cursor.getCount() == 0)
                            {
                                nowData = 0;
                                Toast.makeText(getContext(), "데이터 없음", Toast.LENGTH_SHORT).show();
                            }
                            else if (cursor.getCount() > 0) // else {} // 1보다 작은 경우
                            {
                                cursor.moveToPosition(0);
                                nowData = 1;

                                rid = cursor.getInt(0);
                                rtitle = cursor.getString(1);
                                rdate = cursor.getString(2);
                                rcontent = cursor.getString(3);
                            }
                        }catch (SQLiteException e){}
                        finally {
                            if(rid != 0) { id.setText(String.valueOf(rid)); } else { id.setText(""); }
                            title.setText(rtitle);
                            date.setText(rdate);
                            content.setText(rcontent);

                            cursor.close();
                            dbm.close();
                        }
                        break;
                    }
                    case R.id.btn_pre:
                    {
                        try {
                            DBManager dbm = new DBManager(getActivity());
                            SQLiteDatabase sdb = dbm.getReadableDatabase();
                            cursor = sdb.query("reviewTB", null, null, null, null, null, null);

                            if (numdata == 0 || cursor.getCount() == 0)
                            {
                                nowData = 0;
                                Toast.makeText(getContext(), "데이터 없음", Toast.LENGTH_SHORT).show();
                            }
                            else if (cursor.getCount() > 0) // else {} // 1보다 작은 경우
                            {
                                if (nowData < 1) { nowData = 1; }

                                if(nowData == 1)
                                {
                                    nowData = nowData;
                                }
                                else if(nowData > 1)
                                {
                                    nowData = nowData - 1;
                                }

                                cursor.moveToPosition(nowData-1);

                                rid = cursor.getInt(0);
                                rtitle = cursor.getString(1);
                                rdate = cursor.getString(2);
                                rcontent = cursor.getString(3);
                            }
                        } catch (SQLException e) {}
                        finally
                        {
                            if(rid > 0) { id.setText(String.valueOf(rid)); } else { id.setText(""); }
                            title.setText(rtitle);
                            date.setText(rdate);
                            content.setText(rcontent);

                            cursor.close();
                            dbm.close();
                        }
                        break;
                    }
                    case R.id.btn_next:
                    {
                        try{
                            dbm = new DBManager(getActivity());
                            sdb = dbm.getReadableDatabase();
                            cursor = sdb.query("reviewTB", null, null, null, null, null, null);

                            if (numdata == 0 || cursor.getCount() == 0)
                            {
                                nowData = 0;
                                Toast.makeText(getContext(), "데이터 없음", Toast.LENGTH_SHORT).show();
                            }
                            else if(cursor.getCount()>0 && nowData <= numdata)
                            {
                                if(nowData >= numdata) { nowData = numdata; }
                                else if(nowData < numdata)
                                {
                                    nowData = nowData + 1;
                                }

                                cursor.moveToPosition(nowData-1);

                                rid = cursor.getInt(0);
                                rtitle = cursor.getString(1);
                                rdate = cursor.getString(2);
                                rcontent = cursor.getString(3);
                            }//if end
                        }catch (SQLException e){}
                        finally
                        {
                            if(rid > 0) { id.setText(String.valueOf(rid)); } else { id.setText(""); }
                            title.setText(rtitle);
                            date.setText(rdate);
                            content.setText(rcontent);

                            cursor.close();
                            dbm.close();
                        }
                        break;
                    }
                    case R.id.btn_last:
                    {
                        try{
                            DBManager dbm = new DBManager(getActivity());
                            SQLiteDatabase sdb = dbm.getReadableDatabase();
                            cursor = sdb.query("reviewTB", null, null, null, null, null, null);

                            if (numdata == 0 || cursor.getCount() == 0)
                            {
                                nowData = 0;
                                Toast.makeText(getContext(), "데이터 없음", Toast.LENGTH_SHORT).show();
                            }
                            else if(cursor.getCount()>0 && nowData <= numdata)
                            {
                                if(nowData >= numdata) { nowData = numdata; }

                                cursor.moveToPosition(numdata-1);
                                nowData = numdata;
                                Log.i("btn_first-rid", String.valueOf(numdata-1));
                                rid = cursor.getInt(0);
                                rtitle = cursor.getString(1);
                                rdate = cursor.getString(2);
                                rcontent = cursor.getString(3);

                                Log.i("btn_first-rid", String.valueOf(rid));
                                Log.i("btn_first-rtitle", rtitle);
                                Log.i("btn_first-rdate", rdate);
                                Log.i("btn_first-rcontent", rcontent);
                            }
                        }catch (SQLiteException e){}
                        finally {
                            if(rid != 0) { id.setText(String.valueOf(rid)); } else { id.setText(""); }
                            title.setText(rtitle);
                            date.setText(rdate);
                            content.setText(rcontent);

                            cursor.close();
                            dbm.close();
                        }
                        break;
                    }
                    case R.id.btn_del:
                    {
                        int positionNum = 0;
                        int positionNumNew = 0;

                        if(numdata == 0)
                        {
                            Toast.makeText(getContext(), "삭제할 데이터 없음.", Toast.LENGTH_SHORT).show();
                        }
                        else if(numdata >= 1)
                        {
                            try{
                                dbm = new DBManager(getActivity());
                                sdb = dbm.getWritableDatabase();
                                cursor = sdb.query("reviewTB", null, null, null, null, null, null);

                                if(numdata == 1)
                                {
                                    positionNum = 0;
                                    positionNumNew = 0;
                                    numdata = 0;

                                    Toast.makeText(getContext(), "데이터가 하나도 안 남았습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else // numdata > 1
                                {
                                    positionNum = nowData -1;

                                    if(nowData == numdata) // (맨뒤인 경우 -1)
                                    {
                                        positionNumNew = positionNum - 1;
                                    }
                                    else // nowData == 0(맨뒤인 경우 제외하면 +1)
                                    {
                                        positionNumNew = positionNum + 1;
                                    }

                                    numdata = numdata - 1;
                                }
                                cursor.moveToPosition(positionNum);
                                rid = cursor.getInt(0);
                                String sql = String.format("DELETE FROM reviewTB WHERE dId = %d", rid);
                                sdb.execSQL(sql);

                                cursor.moveToPosition(positionNumNew);
                                rid = cursor.getInt(0);
                                rtitle = cursor.getString(1);
                                rdate = cursor.getString(2);
                                rcontent = cursor.getString(3);
                            }catch (SQLException e){}
                            finally
                            {
                                if(rid > 0) { id.setText(String.valueOf(rid)); } else { id.setText(""); }
                                title.setText(rtitle);
                                date.setText(rdate);
                                content.setText(rcontent);

                                cursor.close();
                                dbm.close();
                            }
                        }
                        break;
                    }
                    case R.id.btn_mod:
                    {
                        if (id.getText().toString().equals("") == false)
                        {


                            String sendId = id.getText().toString();
                            String sendTitle = title.getText().toString();
                            String sendDate = date.getText().toString();
                            String sendContent = content.getText().toString();

                            Intent it = new Intent(getContext(), ModActivity.class);

                            it.putExtra("sendId", sendId);
                            it.putExtra("sendTitle",sendTitle);
                            it.putExtra("sendDate",sendDate);
                            it.putExtra("sendContent",sendContent);

                            startActivity(it);
                        }
                        else
                        {
                            Toast.makeText(getContext(), "수정할 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }
        };

        btn_pre.setOnClickListener(cl);
        btn_next.setOnClickListener(cl);
        btn_del.setOnClickListener(cl);
        btn_mod.setOnClickListener(cl);
        btn_first.setOnClickListener(cl);
        btn_last.setOnClickListener(cl);

        return v;
    }//onC end
}//RF class end
