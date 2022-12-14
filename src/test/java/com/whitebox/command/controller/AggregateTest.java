package com.whitebox.command.controller;

import com.whitebox.command.aggregate.AccountAggregate;
import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.command.events.AccountCreatedEvent;
import com.whitebox.command.events.TransactionCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.StubAggregateLifecycleExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggregateTest {

    @RegisterExtension
    static StubAggregateLifecycleExtension testSubject = new StubAggregateLifecycleExtension();

    private FixtureConfiguration<AccountAggregate> fixture;

    @BeforeAll
    public void setUp() {
        fixture = new AggregateTestFixture<>(AccountAggregate.class);
    }

    @Test
    void testEvents() {
        apply(new AccountCreatedEvent());
        apply(new TransactionCreatedEvent());
        assertEquals(2, testSubject.getAppliedEvents().size());
    }

    @Test
    public void testRedeemCardCommand() {
        CreateAccountCommand createAccountCommand = null;
        createAccountCommand.builder().id("TestId").holderName("testHolderName").balance(BigDecimal.TEN).build();
        AccountCreatedEvent accountCreatedEvent = null;
        accountCreatedEvent.builder().id("TestId")
                .holderName("testHolderName").balance(BigDecimal.TEN).build();
        fixture
                .given(accountCreatedEvent)
                .when(createAccountCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        accountCreatedEvent
                );
    }

}
