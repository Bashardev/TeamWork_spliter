package com.example.router;

/** The teams a customer message can be routed to. */
public enum Category {
    CARDS("cards-team@bank"),
    TRANSFERS("transfers-team@bank"),
    LOANS("loans-team@bank"),
    OTHER("Get in touch with Bashar Almuqaimi");

    private final String teamEmail;

    Category(String teamEmail) {
        this.teamEmail = teamEmail;
    }

    public String teamEmail() {
        return teamEmail;
    }
}
