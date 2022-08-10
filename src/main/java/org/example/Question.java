package org.example;

public abstract class Question {
    private static int questionId = 0;
    protected int id;
    protected final String name;
    protected final String description;
    protected boolean answered;

    public Question(String description) {
        generateId();
        this.name = String.format("Question #%d", id);
        this.description = description;
        answered = false;
    }

    public Question(String name, String description) {
        generateId();
        this.name = name;
        this.description = description;
        answered = false;
    }

    abstract void parseResponse(String input);

    abstract boolean isCorrect();

    @Override
    public String toString() {
        return String.format("== %s ==\n%s", name, description);
    }

    private void generateId() {
        id = ++questionId;
    }
}
