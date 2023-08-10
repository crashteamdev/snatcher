package dev.crashteam.snatcher.model;

import java.util.Arrays;

public enum UserProductStatus {
    succeeded("succeeded"),
    pending("pending"),
    created("created");

    private final String title;

    UserProductStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static UserProductStatus getStatus(String name) {
        return Arrays.stream(UserProductStatus.values())
                .filter(it -> it.title.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
