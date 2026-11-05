package primer.intento.time.booking.infraestructure.security;

public class JwtPrincipal {

    private final Long uid;
    private final String username;
    private final String role;

    public JwtPrincipal(Long uid, String username, String role) {
        this.uid = uid;
        this.username = username;
        this.role = role;
    }

    public Long getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}