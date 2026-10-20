package com.example.router;

public class MessageRouter {

    private final MessageClassifier classifier;

    public MessageRouter(MessageClassifier classifier) {
        if (classifier == null) {
            throw new NullPointerException("null classifier");
        }
        this.classifier = classifier;
    }

    public String routeTo(String message) {
        return categorize(message).teamEmail();
    }

    public Category categorize(String message) {
        if (message == null) {
            throw new NullPointerException("null message");
        }
        if (message.trim().isEmpty()) {
            throw new IllegalArgumentException("empty message");
        }
        return classifier.classify(message);
    }
}
