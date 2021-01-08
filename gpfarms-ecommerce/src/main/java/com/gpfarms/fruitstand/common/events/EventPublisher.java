package com.gpfarms.fruitstand.common.events;

/**
 * Decouples the domain layer from the messaging implementation.
 */
public interface EventPublisher {
	
    void raise(DomainEvent event);
}
