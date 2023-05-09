package com.diatop.model.document;


import java.time.LocalDate;
import java.util.UUID;

public class DocumentDto {

  private String uid;

  private byte[] file;

  private String encodedFile;

  private String name;

  private LocalDate creationTimestamp;

  public DocumentDto(String uid, byte[] file, String name, LocalDate creationTimestamp, String encodedFile) {
    this.uid = uid == null ? UUID.randomUUID().toString() : uid;
    this.file = file;
    this.name = name;
    this.creationTimestamp = creationTimestamp;
    this.encodedFile = encodedFile;
  }

  public DocumentDto(byte[] file, String name, LocalDate creationTimestamp) {
    this.uid = UUID.randomUUID().toString();
    this.file = file;
    this.name = name;
    this.creationTimestamp = creationTimestamp;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public String getEncodedFile() {
    return encodedFile;
  }

  public void setEncodedFile(String encodedFile) {
    this.encodedFile = encodedFile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(LocalDate creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }
}
