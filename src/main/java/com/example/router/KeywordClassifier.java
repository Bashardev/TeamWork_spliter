package com.example.router;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KeywordClassifier implements MessageClassifier {

    private final Map<Category, List<String>> keywords = new LinkedHashMap<>();

    public KeywordClassifier() {
        keywords.put(Category.CARDS, Arrays.asList("card", "atm", "declined", "pin", "visa"));
        keywords.put(Category.TRANSFERS, Arrays.asList("transfer", "sent", "send", "iban", "swift"));
        keywords.put(Category.LOANS, Arrays.asList("loan", "mortgage", "interest", "installment"));
    }

    @Override
    public Category classify(String message) {
        String text = message.toLowerCase();
        for (Map.Entry<Category, List<String>> entry : keywords.entrySet()) {
            for (String keyword : entry.getValue()) {
                if (text.contains(keyword)) {
                    return entry.getKey();
                }
            }
        }
        return Category.OTHER;
    }
}
