package org.gp.dataservice.cassandra.repository;

import org.gp.dataservice.cassandra.model.Event;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends CassandraRepository<Event, UUID> {

  // @AllowFiltering
  // List<Event> findByPublished(boolean published);

  List<Event> findByTypeContaining(String type);
}
