package dev.codder.models;

/**
 * Created by Daniil on 2/25/2016.
 */
public class TODOModel {
    private Long id;

    private boolean completed;

    private String title;

    public TODOModel(){

    }

    public TODOModel(boolean completed, String name){
        this.completed = completed;
        this.title = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
