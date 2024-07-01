package study2.views;

public class Transmission {
//  new TableColumn(table, SWT.NONE).setText("ID号");
//  layout.addColumnData(new ColumnWeightData(40));
//  new TableColumn(table, SWT.NONE).setText("姓名");
//  layout.addColumnData(new ColumnWeightData(20));
//  new TableColumn(table, SWT.NONE).setText("性别");
//  layout.addColumnData(new ColumnWeightData(20));
//  new TableColumn(table, SWT.NONE).setText("年龄");
//  layout.addColumnData(new ColumnWeightData(60));
//  new TableColumn(table, SWT.NONE).setText("记录建立时间");
  private String serverORlocal;
  private String direction;
  private String remotefile;
  private String size;
  private String priority;
  private String time;
  private String reason;
  
  
  public Transmission(String serverORlocal,String direction,String remotefile,String size,String priority,String time,String reason){
      this.serverORlocal = serverORlocal;
      this.direction = direction;
      this.remotefile = remotefile;
      this.size = size;
      this.priority = priority;
      this.time = time;
      this.reason = reason;
  }
  
  public Transmission(String serverORlocal,String direction,String remotefile,String size,String priority,String time){
      this.serverORlocal = serverORlocal;
      this.direction = direction;
      this.remotefile = remotefile;
      this.size = size;
      this.priority = priority;
      this.time = time;
  }
  public String getServerORlocal() {
      return serverORlocal;
  }
  public void setServerORlocal(String serverORlocal) {
      this.serverORlocal = serverORlocal;
  }
  public String getDirection() {
      return direction;
  }
  public void setDirection(String direction) {
      this.direction = direction;
  }
  public String getRemotefile() {
      return remotefile;
  }
  public void setRemotefile(String remotefile) {
      this.remotefile = remotefile;
  }
  public String getSize() {
      return size;
  }
  public void setSize(String size) {
      this.size = size;
  }
  public String getPriority() {
      return priority;
  }
  public void setPriority(String priority) {
      this.priority = priority;
  }
  public String getTime() {
      return time;
  }
  public void setTime(String time) {
      this.time = time;
  }
  public String getReason() {
      return reason;
  }
  public void setReason(String reason) {
      this.reason = reason;
  }
  
  
  
  
}
