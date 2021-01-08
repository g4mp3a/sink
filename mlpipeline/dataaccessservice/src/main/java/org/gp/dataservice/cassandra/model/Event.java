package org.gp.dataservice.cassandra.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table
@Getter
@Setter
public class Event {

  @PrimaryKey
  private UUID id;

  private String type;
  private String data;

  public Event() {}

  public Event(UUID id, String type, String data) {

    this.id = id;
    this.type = type;
    this.data = data;
  }

  @Override
  public String toString() {

    return "Event [id=" + id + ", type=" + type + ", data=" + data
        + "]";
  }
}
