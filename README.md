# MultipleFilter

简单易用的多样式筛选器（选择控件支持多选和反选，选中或输入任一项则有小红点提示标记）
---

# Example
<image src="https://github.com/sky8650/MultipleFilter/blob/master/app/img/device-2018-12-28-165732.png" width="240px"/>
<image src="https://github.com/sky8650/MultipleFilter/blob/master/app/img/device-2018-12-28-165658.png" width="240px"/> 
<image src="https://github.com/sky8650/MultipleFilter/blob/master/app/img/GIF.gif" width="240px"/>



---
# Usage
### 调用dialog样式的activity：
```java
    Intent intent=new Intent(MainActivity.this,DoneTaskListFilter.class);
                //intent.putExtra("VO",null);
                intent.putExtra("TYPE","NOTASKDONE");//如果是已办任务的筛选
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
            filterName=taskSelectVo.getMapVos();//也可以从前一个页面动态传递
        }
```
 ###  设置右侧栏目的数据
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
             taskFilterVos=taskSelectVo.getTaskFilterType();//可以动态设置或者从前一个页面传递
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
        
        
        
        
        
        
        
        
