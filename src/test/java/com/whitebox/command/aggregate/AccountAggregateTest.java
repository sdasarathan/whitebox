package com.whitebox.command.aggregate;

import com.whitebox.command.commands.CreateAccountCommand;
import com.whitebox.command.events.AccountCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class AccountAggregateTest {

    private FixtureConfiguration<AccountAggregate> fixture;

    @BeforeAll
    public void setUp() {
        fixture = new AggregateTestFixture<>(AccountAggregate.class);
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