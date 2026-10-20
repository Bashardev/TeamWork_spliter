package com.example.router;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KeywordClassifierTest {

    private final MessageClassifier classifier = new KeywordClassifier();

    @Test
    void givenCardMessage_whenClassify_thenCards() {
        assertEquals(Category.CARDS, classifier.classify("My card was declined at the supermarket"));
    }

    @Test
    void givenTransferMessage_whenClassify_thenTransfers() {
        assertEquals(Category.TRANSFERS, classifier.classify("I sent 500 JOD but he has not received it"));
    }

    @Test
    void givenLoanMessage_whenClassify_thenLoans() {
        assertEquals(Category.LOANS, classifier.classify("What is the interest rate on a home loan?"));
    }

    @Test
    void givenUnrelatedMessage_whenClassify_thenOther() {
        assertEquals(Category.OTHER, classifier.classify("How do I update my phone number?"));
    }

    @Test
    void givenUpperCaseMessage_whenClassify_thenStillMatches() {
        assertEquals(Category.CARDS, classifier.classify("MY CARD IS BLOCKED"));
    }
}
