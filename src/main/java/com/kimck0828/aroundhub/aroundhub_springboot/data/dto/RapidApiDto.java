package com.kimck0828.aroundhub.aroundhub_springboot.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
  ショットＵＲＬ取得レスポンス情報をマッピングするＤＴＯクラス
 */
public class RapidApiDto {
  /** ショットURL*/
  private String result_url;
}
