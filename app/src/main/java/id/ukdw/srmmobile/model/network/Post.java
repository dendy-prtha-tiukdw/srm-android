package id.ukdw.srmmobile.model.network;

public class Post {

    private String serverAuthCode;
    private String nomorInduk;
    private String provider;
    private String role;
    private String idToken;


    public Post(String provider, String idToken,String authCode) {
        this.provider=provider;
        this.idToken=idToken;
        this.serverAuthCode= authCode;
    }

    public Post(String provider, String authCode, String nomorInduk, String role) {
        this.provider=provider;
        this.serverAuthCode= authCode;
        this.nomorInduk = nomorInduk;
        this.role=role;

    }
}
