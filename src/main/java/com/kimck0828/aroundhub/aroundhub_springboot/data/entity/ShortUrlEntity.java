package com.kimck0828.aroundhub.aroundhub_springboot.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="short_url")
public class ShortUrlEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique = true)
  private String shortUrl;
  
  @Column(nullable = false, unique = true)
  private String orgUrl;
}
