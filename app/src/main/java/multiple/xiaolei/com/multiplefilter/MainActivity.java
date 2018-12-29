package multiple.xiaolei.com.multiplefilter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.alibaba.fastjson.JSONObject;

import multiple.xiaolei.com.multiplefilter.filter.Constants;
import multiple.xiaolei.com.multiplefilter.filter.DoneTaskListFilter;
import multiple.xiaolei.com.multiplefilter.vo.TaskSelectVo;

public class MainActivity extends AppCompatActivity {
    Button open_filter;
    private  static final int reQuestCodeFilter=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open_filter= (Button) findViewById(R.id.open_filter);
        open_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DoneTaskListFilter.class);
                //intent.putExtra("VO",null);
                intent.putExtra("TYPE","NOTASKDONE");//如果是已办任务的筛选
                startActivityForResult(intent,reQuestCodeFilter);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            TaskSelectVo taskSelectVo = (TaskSelectVo) data.getSerializableExtra(Constants.FILTER_VO);
            String  json= JSONObject.toJSONString(taskSelectVo);
            Toast.makeText(this,json,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"取消选择",Toast.LENGTH_LONG).show();
        }

    }
}
