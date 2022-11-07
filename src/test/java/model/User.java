package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Основная модель -- для создания инстансов */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id;
    private String job;
    private String name;

    public User() { }

    public User(User anotherUser) { // Конструктор копирования -- для создания новых инстансов-копий по данным из users.yaml
        this.name = anotherUser.getName();
        this.job = anotherUser.getJob();
    }

    public User(String job, String name) {
        this.job = job;
        this.name = name;
    }

    public User(String id, String job, String name) {
        this(job, name);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) { // Для сравнения отправленных и полученных обратно объектов пользователей
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!this.getId().equals(user.getId())) return false;
        if (!this.getName().equals(user.getName())) return false;

        return this.getJob().equals(user.getJob());
    }

    @Override
    public int hashCode() { // Для сравнения отправленных и полученных обратно объектов пользователей
        int result = this.getId().hashCode();
        result = 31 * result + this.getName().hashCode();
        result = 31 * result + this.getJob().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
