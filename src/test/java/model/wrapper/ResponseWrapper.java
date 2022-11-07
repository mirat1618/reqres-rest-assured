package model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapper {
    private User data;

    public ResponseWrapper(User data) {
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
