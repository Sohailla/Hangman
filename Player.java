public class Player {

    private String username;
    private String password;
    private String nickname;
    private int Activation=0;
    private int score=0;

    public int getActivation() {
        return Activation;
    }
    
    public void setActivation(int activation) {
        this.Activation = activation;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getScore() {
        return score;
    }
    public void setScore (int score) {
        this.score = score;
    }
}
