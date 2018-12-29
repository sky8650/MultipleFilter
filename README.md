# MultipleFilter

简单易用的多样式筛选器（选择控件支持多选和反选，选中或输入任一项则有小红点提示标记）
---

# Example
<image src="https://github.com/sky8650/MultipleFilter/blob/master/app/img/device-2018-12-28-165732.png" width="260px"/>    <image src="https://github.com/sky8650/MultipleFilter/blob/master/app/img/device-2018-12-28-165658.png" width="260px"/>     <image src="https://github.com/sky8650/MultipleFilter/blob/master/app/img/GIF.gif" width="260px"/>





---
# Usage
### 调用dialog样式的activity：
```java
    Intent intent=new Intent(MainActivity.this,DoneTaskListFilter.class);
                //intent.putExtra("VO",null);
                intent.putExtra("TYPE","NOTASKDONE");
                startActivityForResult(intent,reQuestCodeFilter)
```                
### 设置左侧栏目的数据：
```java
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
            filterName=taskSelectVo.getMapVos();//可以从代码动态设置或者动态传递
        }
```
 ###  设置右侧栏目的数据:
```java
        
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
             taskFilterVos=taskSelectVo.getTaskFilterType();//可以动态设置或者动态传递
         }
         
```
### 右侧栏的样式可自定义进行设置:

```java
 private  void  initView(){
        if (left_type == 0) {//任务类型
            myview = inflater.inflate(R.layout.activity_group_layout, null);
            list_no_title = (ListView) myview.findViewById(R.id.list_no_title);
            initTaskType();
        }
        else if(left_type==1){//任务状态
            myview = inflater.inflate(R.layout.activity_group_layout,null);
            list_no_title = (ListView) myview.findViewById(R.id.list_no_title);
            if("TASKDONE".equals(taskType)){
                initTaskStatusDone();
            }else if("NOTASKDONE".equals(taskType)){
                initTaskStatusNoDone();
            }

        }
        else if(left_type==2){//委托时间
            myview = inflater.inflate(R.layout.che_activity_time_filter,null);
            initTime();
        }
        else if(left_type==3){ //车牌号
            myview = inflater.inflate(R.layout.che_activity_card_filter,null);
            initCarId();
        }
    }


```


 ### 获取筛选器的值：
```java
        if(resultCode== Activity.RESULT_OK){
            TaskSelectVo taskSelectVo = (TaskSelectVo) data.getSerializableExtra(Constants.FILTER_VO);
            String  json= JSONObject.toJSONString(taskSelectVo);
            Toast.makeText(this,json,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"取消选择",Toast.LENGTH_LONG).show();
        }
 ```
## Version: 1.0.0

       有其他好的建议或者需要改动的地方欢迎给我提Issues哦
        
        
        
        
        
        
        
        
