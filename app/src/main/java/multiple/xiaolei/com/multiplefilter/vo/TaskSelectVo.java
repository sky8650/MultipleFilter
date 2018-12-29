package multiple.xiaolei.com.multiplefilter.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaolei on 2016/11/18.
 */
public class TaskSelectVo implements Serializable {
    private List<TaskFilterVo> taskFilterType;//任务类型
    private List<TaskFilterVo> taskFilterStatus;//任务类型
    private List<TaskFilterVo> taskFilterResult;//核卡结果
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String licensePlate;//车牌号
    private List<MapVo> mapVos;//是否选中


    public List<TaskFilterVo> getTaskFilterResult() {
        return taskFilterResult;
    }

    public void setTaskFilterResult(List<TaskFilterVo> taskFilterResult) {
        this.taskFilterResult = taskFilterResult;
    }

    public List<MapVo> getMapVos() {
        return mapVos;
    }

    public void setMapVos(List<MapVo> mapVos) {
        this.mapVos = mapVos;
    }

    public List<TaskFilterVo> getTaskFilterType() {
        return taskFilterType;
    }

    public void setTaskFilterType(List<TaskFilterVo> taskFilterType) {
        this.taskFilterType = taskFilterType;
    }

    public List<TaskFilterVo> getTaskFilterStatus() {
        return taskFilterStatus;
    }

    public void setTaskFilterStatus(List<TaskFilterVo> taskFilterStatus) {
        this.taskFilterStatus = taskFilterStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
