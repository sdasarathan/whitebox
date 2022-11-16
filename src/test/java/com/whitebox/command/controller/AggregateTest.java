package com.whitebox.command.controller;

import com.whitebox.command.events.AccountCreatedEvent;
import com.whitebox.command.events.TransactionCreatedEvent;
import org.axonframework.test.aggregate.StubAggregateLifecycleExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggregateTest {

    @RegisterExtension
    static StubAggregateLifecycleExtension testSubject = new StubAggregateLifecycleExtension();

    @Test
    void testEvents() {
        apply(new AccountCreatedEvent());
        apply(new TransactionCreatedEvent());
        assertEquals(2, testSubject.getAppliedEvents().size());
    }

}
