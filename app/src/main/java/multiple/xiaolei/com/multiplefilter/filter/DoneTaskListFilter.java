package multiple.xiaolei.com.multiplefilter.filter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import multiple.xiaolei.com.multiplefilter.R;
import multiple.xiaolei.com.multiplefilter.vo.MapVo;
import multiple.xiaolei.com.multiplefilter.vo.TaskSelectVo;


/**
 * xiaolei
 */

public class DoneTaskListFilter extends Activity implements DoneTaskCallBack,View.OnClickListener{
    LinearLayout ll_mainLi,btn_cancle,btn_ok;
    LeftListAdpter leftAdapter;
    ListView list_left, list_right;
    DoneTaskListRightAdapter doneTaskListRightAdapter;
    TaskSelectVo taskSelectVo;
    String taskType;//任务的类型，是未办还是已办


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_task_list_filter);
        initView();
        doBusiness();
    }



    public void initView() {
        ll_mainLi=(LinearLayout) findViewById(R.id.ll_mainLi);
        int screenHeight=(int) (getHeight(this)*0.5);
        ViewGroup.LayoutParams layoutParams=ll_mainLi.getLayoutParams();
        layoutParams.height=screenHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);// 需要添加的语句
        btn_cancle= (LinearLayout) findViewById(R.id.btn_cancle);
        btn_ok= (LinearLayout) findViewById(R.id.btn_ok);
        btn_cancle.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        list_left = (ListView) findViewById(R.id.list_left);
        list_right= (ListView) findViewById(R.id.list_right);

    }

    /**
     * 初始化左侧筛选栏类型
     */
    public void doBusiness() {
        taskType=getIntent().getStringExtra("TYPE");
        taskSelectVo= (TaskSelectVo) getIntent().getSerializableExtra("VO");
        if(null==taskSelectVo){
            taskSelectVo=new TaskSelectVo();
        }
        List<MapVo> filterName=null;
        if(null==taskSelectVo.getMapVos()) {
            filterName=new ArrayList<MapVo>();
            MapVo mapVo0=new MapVo();
            mapVo0.setName("任务类型");

            MapVo mapVo1=new MapVo();
            mapVo1.setName("任务状态");

            MapVo mapVo2=new MapVo();
            mapVo2.setName("委托时间");

            MapVo mapVo3=new MapVo();
            mapVo3.setName("车牌号");

            filterName.add(mapVo0);
            filterName.add(mapVo1);
            filterName.add(mapVo2);
            filterName.add(mapVo3);
            taskSelectVo.setMapVos(filterName);
        }else {
            filterName=taskSelectVo.getMapVos();
        }


        leftAdapter=new LeftListAdpter(this.getApplicationContext());
        leftAdapter.addItem(filterName);
        list_left.setAdapter(leftAdapter);
        list_left.setOnItemClickListener(onitemLeftClick);


        doneTaskListRightAdapter=new DoneTaskListRightAdapter(DoneTaskListFilter.this,
                0,DoneTaskListFilter.this,taskSelectVo,taskType);//初始化第一个选中项
        list_right.setAdapter(doneTaskListRightAdapter);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
           case R.id.btn_cancle:
             finish();
              break;

            case  R.id.btn_ok:
                Intent intent=new Intent();
                intent.putExtra(Constants.FILTER_VO,taskSelectVo);
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;

        }
    }
  private AdapterView.OnItemClickListener onitemLeftClick=new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          doneTaskListRightAdapter=new DoneTaskListRightAdapter(DoneTaskListFilter.this,
                  position,DoneTaskListFilter.this,taskSelectVo,taskType);
          list_right.setAdapter(doneTaskListRightAdapter);
          leftAdapter.changeSelected(position);

      }
  };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.timepicker_anim_exit_bottom, 0);

    }

   //任务选中回调
    @Override
    public void taskCallBack(TaskSelectVo taskSelectVo,int position,boolean isSelect) {
          this.taskSelectVo=taskSelectVo;
        ((MapVo)leftAdapter.getItem(position)).setCheck(isSelect);
        this.taskSelectVo.getMapVos().get(position).setCheck(isSelect);//保存当前选中的状态
        leftAdapter.notifyDataSetChanged();
    }

    class   LeftListAdpter extends BaseAdapter {
       Context context;
       LayoutInflater inflater;
        private int mSelect = 0;   //选中项int mSelect = 0;
       public LeftListAdpter(Context context) {
           this.context = context;
           this.inflater = (LayoutInflater) context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       }




       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           MapVo mapVo= (MapVo) getDataList().get(position);
           if (convertView == null) {
               LayoutInflater inflater = LayoutInflater.from(context);
               convertView = inflater.inflate(
                       R.layout.comment_spinner_item, parent, false);
           }
           ImageView is_check_img= (ImageView) convertView.findViewById(R.id.is_check_img);
           TextView tv = (TextView) convertView
                   .findViewById(R.id.spinner_tv);
           LinearLayout task_check_li= (LinearLayout) convertView.findViewById(R.id.task_check_li);
           tv.setText(mapVo.getName());
           if(!mapVo.isCheck()){
               is_check_img.setVisibility(View.INVISIBLE);
           }else {
               is_check_img.setVisibility(View.VISIBLE);
           }
           if(mSelect==position){
               task_check_li.setBackgroundResource(R.color.white); //选中项背景
           }else{
               task_check_li.setBackgroundResource(R.color.greyeee);  //其他项背景
           }
           return convertView;
       }


        public void changeSelected(int positon){ //刷新方法
            if(positon != mSelect){
                mSelect = positon;
                notifyDataSetChanged();
            }
        }
   }


    // 获取屏幕的高度
    public static int getHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;

    }

}
