package multiple.xiaolei.com.multiplefilter.filter;


import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import multiple.xiaolei.com.multiplefilter.R;
import multiple.xiaolei.com.multiplefilter.vo.TaskFilterVo;
import multiple.xiaolei.com.multiplefilter.vo.TaskSelectVo;


/**
 * Created by xiaolei on 2016/11/17.
 */
public class DoneTaskListRightAdapter extends BaseAdapter {
    Activity context;
    LayoutInflater inflater;
    private int left_type;
    TaskCheckAdapter  taskCheckAdapter;
    List<TaskFilterVo> taskFilterVos;
    ListView list_no_title;
    private View myview;
    DoneTaskCallBack doneTaskCallBack;
    TaskSelectVo taskSelectVo;
    private String taskType;


    public DoneTaskListRightAdapter(Activity context, int left_type, DoneTaskCallBack doneTaskCallBack
              , TaskSelectVo taskSelectVo, String taskType) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.left_type = left_type;
        this.doneTaskCallBack=doneTaskCallBack;
        this.taskSelectVo=taskSelectVo;
        if(null==this.taskSelectVo){
            this.taskSelectVo=new TaskSelectVo();
        }
        this.taskType=taskType;
        initView();//必须放在构造方法中， 不能放在getview中

    }

    private  void  initView(){
        if (left_type == 0) {//任务类型
            myview = inflater.inflate(R.layout.activity_group_layout, null);
            list_no_title = (ListView) myview.findViewById(R.id.list_no_title);
            initTaskType();
        }
        else if(left_type==1)
        {//任务状态
            myview = inflater.inflate(R.layout.activity_group_layout,null);
            list_no_title = (ListView) myview.findViewById(R.id.list_no_title);
            if("TASKDONE".equals(taskType)){
                initTaskStatusDone();
            }else if("NOTASKDONE".equals(taskType)){
                initTaskStatusNoDone();
            }

        }
        else if(left_type==2)
        {//委托时间
            myview = inflater.inflate(R.layout.che_activity_time_filter,null);
            initTime();
        }

        else if(left_type==3)
        {//车牌号
            myview = inflater.inflate(R.layout.che_activity_card_filter,null);
            initCarId();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         return myview;
      }


    /**
     * 初始化车牌号筛选
     */
    private void  initCarId(){
        EditText car_id= (EditText) myview.findViewById(R.id.car_id);
        if(!TextUtils.isEmpty(taskSelectVo.getLicensePlate())){
            car_id.setText(taskSelectVo.getLicensePlate());
        }

        car_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 if(s.length()==0){
                     taskSelectVo.setLicensePlate(s.toString());
                     doneTaskCallBack.taskCallBack(taskSelectVo,left_type,false);//回调    对象，索引， 全部是否选中
                 }else if(s.length()>0){
                     taskSelectVo.setLicensePlate(s.toString());
                     doneTaskCallBack.taskCallBack(taskSelectVo,left_type,true);//回调    对象，索引， 全部是否选中
                 }
            }
        });

    }

    /**
     * 初始化委托时间
     */
    private void  initTime(){
        final RadioButton radioButton= (RadioButton) myview.findViewById(R.id.radio0);
        final EditText start_time= (EditText) myview.findViewById(R.id.start_time);
        final EditText end_time= (EditText) myview.findViewById(R.id.end_time);
        if(!TextUtils.isEmpty(taskSelectVo.getEndTime())||!TextUtils.isEmpty(taskSelectVo.getStartTime())){
            start_time.setText(taskSelectVo.getStartTime());
            end_time.setText(taskSelectVo.getEndTime());
            radioButton.setChecked(false);
        }

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    taskSelectVo.setStartTime("");
                    taskSelectVo.setEndTime("");
                    start_time.setText("");
                    end_time.setText("");
                    doneTaskCallBack.taskCallBack(taskSelectVo,left_type,false);//回调    对象，索引， 全部是否选中
                }
            }
        });

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"开始时间", Toast.LENGTH_LONG).show();

            }
        });

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"开始时间", Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 初始化任务类型
     */
      private  void  initTaskType(){
         if(taskSelectVo!=null&&taskSelectVo.getTaskFilterType()==null) {
             TaskFilterVo taskFilterVo0 = new TaskFilterVo();
             taskFilterVo0.setName("全部");
             taskFilterVo0.setId("");
             taskFilterVo0.setCheck(true);

             TaskFilterVo taskFilterVo1 = new TaskFilterVo();
             taskFilterVo1.setName("现场任务");
             taskFilterVo1.setId("1");
             taskFilterVo1.setCheck(false);

             TaskFilterVo taskFilterVo2 = new TaskFilterVo();
             taskFilterVo2.setName("定损任务");
             taskFilterVo2.setId("2");
             taskFilterVo2.setCheck(false);

             TaskFilterVo taskFilterVo3 = new TaskFilterVo();
             taskFilterVo3.setName("物损任务");
             taskFilterVo3.setId("3");
             taskFilterVo3.setCheck(false);

             taskFilterVos = new ArrayList<TaskFilterVo>();
             taskFilterVos.add(taskFilterVo0);
             taskFilterVos.add(taskFilterVo1);
             taskFilterVos.add(taskFilterVo2);
             taskFilterVos.add(taskFilterVo3);
             taskSelectVo.setTaskFilterType(taskFilterVos);
         }else if(taskSelectVo.getTaskFilterType()!=null){
             taskFilterVos=taskSelectVo.getTaskFilterType();
         }
          taskCheckAdapter =new TaskCheckAdapter();
          taskCheckAdapter.addItem(taskFilterVos);
          list_no_title.setAdapter(taskCheckAdapter);
          //选项的点击事件
        // list_no_title.setOnItemClickListener(onItemClick);
       }

    /**
     * 初始化已办任务状态
     */
    private void  initTaskStatusDone(){
        if(taskFilterVos==null&&taskSelectVo.getTaskFilterStatus()==null) {
            TaskFilterVo taskFilterVo0 = new TaskFilterVo();
            taskFilterVo0.setName("全部");
            taskFilterVo0.setId("");
            taskFilterVo0.setCheck(true);

            TaskFilterVo taskFilterVo1 = new TaskFilterVo();
            taskFilterVo1.setName("待审核");
            taskFilterVo1.setId("0");
            taskFilterVo1.setCheck(false);

            TaskFilterVo taskFilterVo2 = new TaskFilterVo();
            taskFilterVo2.setName("已审核");
            taskFilterVo2.setId("1");
            taskFilterVo2.setCheck(false);

            TaskFilterVo taskFilterVo3 = new TaskFilterVo();
            taskFilterVo3.setName("审核退回");
            taskFilterVo3.setId("2");
            taskFilterVo3.setCheck(false);

            taskFilterVos = new ArrayList<TaskFilterVo>();
            taskFilterVos.add(taskFilterVo0);
            taskFilterVos.add(taskFilterVo1);
            taskFilterVos.add(taskFilterVo2);
            taskFilterVos.add(taskFilterVo3);
            taskSelectVo.setTaskFilterStatus(taskFilterVos);
        }else if(taskSelectVo.getTaskFilterStatus()!=null)
        {
            taskFilterVos=taskSelectVo.getTaskFilterStatus();
        }
        taskCheckAdapter =new TaskCheckAdapter();
        taskCheckAdapter.addItem(taskFilterVos);
        list_no_title.setAdapter(taskCheckAdapter);
        //选项的点击事件
    }


    /**
     * 初始化未办任务状态
     */
    private void  initTaskStatusNoDone(){
        if(taskFilterVos==null&&taskSelectVo.getTaskFilterStatus()==null) {
            TaskFilterVo taskFilterVo0 = new TaskFilterVo();
            taskFilterVo0.setName("全部");
            taskFilterVo0.setId("");
            taskFilterVo0.setCheck(true);

            TaskFilterVo taskFilterVo1 = new TaskFilterVo();
            taskFilterVo1.setName("待受理");
            taskFilterVo1.setId("0");
            taskFilterVo1.setCheck(false);

            TaskFilterVo taskFilterVo2 = new TaskFilterVo();
            taskFilterVo2.setName("待提交");
            taskFilterVo2.setId("1");
            taskFilterVo2.setCheck(false);

            TaskFilterVo taskFilterVo3 = new TaskFilterVo();
            taskFilterVo3.setName("提交失败");
            taskFilterVo3.setId("2");
            taskFilterVo3.setCheck(false);

            taskFilterVos = new ArrayList<TaskFilterVo>();
            taskFilterVos.add(taskFilterVo0);
            taskFilterVos.add(taskFilterVo1);
            taskFilterVos.add(taskFilterVo2);
            taskFilterVos.add(taskFilterVo3);
            taskSelectVo.setTaskFilterStatus(taskFilterVos);
        }else if(taskSelectVo.getTaskFilterStatus()!=null)
        {
            taskFilterVos=taskSelectVo.getTaskFilterStatus();
        }
        taskCheckAdapter =new TaskCheckAdapter();
        taskCheckAdapter.addItem(taskFilterVos);
        list_no_title.setAdapter(taskCheckAdapter);
        //选项的点击事件
    }



    class   TaskCheckAdapter extends  BaseAdapter{
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TaskFilterVo taskFilterVo= (TaskFilterVo) getDataList().get(position);
            convertView = inflater.inflate(R.layout.item_task_check, parent, false);
            RadioButton task_filter_ra= (RadioButton) convertView.findViewById(R.id.task_filter_ra);
            task_filter_ra.setText(taskFilterVo.getName());
            if(taskFilterVo.isCheck()){
                task_filter_ra.setTextColor(context.getResources().getColor(R.color.main_red));
            }else{
                task_filter_ra.setTextColor(context.getResources().getColor(R.color.font_color));
            }
            task_filter_ra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setOnitemClickType(position);
                }
            });
            task_filter_ra.setChecked(taskFilterVo.isCheck());
            return convertView;
        }
    }






    private  void  setOnitemClickType(int  position){
        if(position!=0){//如果点击的不是第一个
            taskFilterVos.get(0).setCheck(false);
            taskFilterVos.get(position).setCheck(taskFilterVos.get(position).isCheck()?false:true);//不让取消选择
            if(left_type==0){
                taskSelectVo.setTaskFilterType(taskFilterVos);
            }else if(left_type==1){
                taskSelectVo.setTaskFilterStatus(taskFilterVos);
            }
            int  i=0;
            for (TaskFilterVo vo:taskFilterVos){//判断是否有选中的选项
                if(!vo.isCheck()){
                    i++;
                }
            }
            if(i==taskFilterVos.size()){
                taskFilterVos.get(0).setCheck(taskFilterVos.get(0).isCheck()?true:true);
                doneTaskCallBack.taskCallBack(taskSelectVo,left_type,false);//回调    对象，索引， 全部是否选中
            }else{
                doneTaskCallBack.taskCallBack(taskSelectVo,left_type,true);//回调,   对象，索引， 全部是否选中
            }
            taskCheckAdapter.clear();
            taskCheckAdapter.addItem(taskFilterVos);
            list_no_title.setAdapter(taskCheckAdapter);

        }else if(position==0){
            taskFilterVos.get(0).setCheck(taskFilterVos.get(0).isCheck()?true:true);
            for (int i=1;i<taskFilterVos.size();i++){
                taskFilterVos.get(i).setCheck(false);
            }
            if(left_type==0){
                taskSelectVo.setTaskFilterType(taskFilterVos);
            }else if(left_type==1){
                taskSelectVo.setTaskFilterStatus(taskFilterVos);
            }
            doneTaskCallBack.taskCallBack(taskSelectVo,left_type,false);//回调    对象，索引， 全部是否选中
            taskCheckAdapter.clear();
            taskCheckAdapter.addItem(taskFilterVos);
            list_no_title.setAdapter(taskCheckAdapter);
        }
        
    }





    }
