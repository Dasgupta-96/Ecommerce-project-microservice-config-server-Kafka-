package com.common_util.event;

import java.util.List;

public class BookingEvent {

  private String userId;

  private String showName;

  private List<String> seats;

  public BookingEvent() {}

  public BookingEvent(String userId, String showName, List<String> seats) {
    this.userId = userId;
    this.showName = showName;
    this.seats = seats;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String showName) {
    this.showName = showName;
  }

  public List<String> getSeats() {
    return seats;
  }

  public void setSeats(List<String> seats) {
    this.seats = seats;
  }
}
