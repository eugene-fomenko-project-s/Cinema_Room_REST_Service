package cinema;

import java.util.UUID;

public class Token {
    UUID token;
    Token(UUID token){
        this.token = token;
    }
    Token(){
        token = UUID.randomUUID();
    }
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

}
