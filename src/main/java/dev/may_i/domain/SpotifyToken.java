package dev.may_i.domain;

import com.amazonaws.services.dynamodbv2.document.Item;

import java.util.Optional;

public class SpotifyToken {
    private String access_token;
    private String token_type;
    private Long expires_in;
    private String refresh_token;
    private String scope;

    public SpotifyToken(String access_token, String token_type, Long expires_in, String refresh_token, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.scope = scope;
    }

    public SpotifyToken() {
    }

    public SpotifyToken(SpotifyToken other, long expires_in) {
        this.access_token = other.access_token;
        this.token_type = other.token_type;
        this.refresh_token = other.refresh_token;
        this.expires_in = expires_in;
        this.scope = other.scope;
    }

    public SpotifyToken(SpotifyToken other, String refresh_token) {
        this.access_token = other.access_token;
        this.token_type = other.token_type;
        this.expires_in = other.expires_in;
        this.scope = other.scope;
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Item toItem() {
        return new Item()
                .with("id", "token")
                .with("access_token", access_token)
                .with("token_type", token_type)
                .with("expires_in", expires_in)
                .with("refresh_token", refresh_token)
                .with("scope", scope);
    }

    public SpotifyToken(Item item) {
        this.access_token = item.getString("access_token");
        this.token_type = item.getString("token_type");
        this.expires_in = item.getLong("expires_in");
        this.refresh_token = item.getString("refresh_token");
        this.scope = item.getString("scope");
    }

    public boolean isExpired() {
        long expiresIn = Optional.ofNullable(getExpires_in()).orElse(0L);
        return (System.currentTimeMillis() / 1000) > expiresIn;
    }

    @Override
    public String toString() {
        return "SpotifyToken{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}