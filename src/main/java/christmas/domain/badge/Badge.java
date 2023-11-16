package christmas.domain.badge;

public enum Badge {
    NONE("없음"), STAR("별"), TREE("트리"), SANTA("산타");

    private final String description;

    Badge(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
