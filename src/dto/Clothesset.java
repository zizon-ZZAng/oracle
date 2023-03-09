package dto;

import lombok.Data;

@Data
public class Clothesset {
  // 8가지 온도 분류
  private String setno;
  // 외투
  private String outwear;
  // 상의
  private String top;
  // 바지
  private String bottom;
  // 신발
  private String shoes;
  // 악세사리
  private String accessory;
  // 추천번호
  private String reno;
}
