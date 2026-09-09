package com.common_util.event;

public class SeatReserveEvent {

  private String showName;

  private boolean isReserved;

  private String userId;

  public SeatReserveEvent() {}

  public SeatReserveEvent(String showName, boolean isReserved, String userId) {
    this.showName = showName;
    this.isReserved = isReserved;
    this.userId = userId;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String showName) {
    this.showName = showName;
  }

  public boolean isReserved() {
    return isReserved;
  }

  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
