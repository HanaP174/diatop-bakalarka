package com.diatop.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.NonNull;

import java.time.LocalDate;

@Data
@Generated
@Getter
@Setter
@NoArgsConstructor
@Table(name = "documents")
public class Document {

  @Id
  private Long id;

  @NonNull
  @Column("name")
  private String name;

  @NonNull
  @Column("file")
  private byte[] file;

  @NonNull
  @Column("creation_timestamp")
  private LocalDate creationTimestamp;

  @NonNull
  @Column("uid")
  private String uid;
}
