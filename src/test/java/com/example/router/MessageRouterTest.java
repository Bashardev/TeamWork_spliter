package com.example.router;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MessageRouterTest {

    @Test
    void givenClassifierSaysCards_whenRoute_thenCardsTeamEmail() {
        MessageClassifier fake = message -> Category.CARDS;   // lambda fake
        MessageRouter router = new MessageRouter(fake);

        assertEquals("cards-team@bank", router.routeTo("anything"));
    }

    @Test
    void givenClassifierSaysOther_whenRoute_thenGeneralSupportEmail() {
        MessageRouter router = new MessageRouter(message -> Category.OTHER);

        assertEquals("Get in touch with Bashar Almuqaimi", router.routeTo("anything"));
    }

    @Test
    void givenNullMessage_whenRoute_thenThrowNullPointerException() {
        MessageRouter router = new MessageRouter(message -> Category.OTHER);

        NullPointerException thrown = assertThrows(NullPointerException.class,
                () -> router.routeTo(null));

        assertEquals("null message", thrown.getMessage());
    }

    @Test
    void givenBlankMessage_whenRoute_thenThrowIllegalArgumentException() {
        MessageRouter router = new MessageRouter(message -> Category.OTHER);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> router.routeTo("   "));

        assertEquals("empty message", thrown.getMessage());
    }

    @Test
    void givenNullClassifier_whenConstruct_thenThrowNullPointerException() {
        NullPointerException thrown = assertThrows(NullPointerException.class,
                () -> new MessageRouter(null));

        assertEquals("null classifier", thrown.getMessage());
    }

    @Test
    void givenRealClassifier_whenRoute_thenWorksEndToEnd() {
        MessageRouter router = new MessageRouter(new KeywordClassifier());

        assertEquals("loans-team@bank", router.routeTo("Can I get a mortgage?"));
    }

    @Test
    void givenClassifierSaysLoans_whenCategorize_thenLoansCategory() {
        MessageRouter router = new MessageRouter(message -> Category.LOANS);

        assertEquals(Category.LOANS, router.categorize("anything"));
    }
}
