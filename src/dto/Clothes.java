package dto;

import lombok.Data;

@Data
public class Clothes {
  // 옷 번호
  private long clno;
  // 옷 이름
  private String clname;
  // 옷 재질
  private String texture;
  // 옷 두께
  private String thickness;
  // 상의,하의,신발
  private int type;
}
