package dto;

import lombok.Data;

//의류테이블
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
// 옷 종류 이름
private String name;
}
