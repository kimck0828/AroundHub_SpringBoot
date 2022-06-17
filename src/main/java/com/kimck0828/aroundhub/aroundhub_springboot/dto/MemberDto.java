package com.kimck0828.aroundhub.aroundhub_springboot.dto;

import lombok.*;

@Data
@Builder
// ★デフォルトコンストラクタを生成しないと、API通信でレスポンスとしてEntityインスタンスを受け取った場合、JSON変換処理でエラーになる
// これは、Spring処理で、Entityインスタンスを受け取るとデフォルトコンストラクタを呼び出してインスタンスを生成する模様
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

  private String id;
  private String name;
  private String email;
  private String organization;

}
