
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name, String nationality, int assists, int goals, int penalties, String team, int games) {
        this.name = name;
        this.nationality = nationality;
        this.assists = assists;
        this.goals = goals;
        this.penalties = penalties;
        this.team = team;
        this.games = games;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public int getAssists() {
        return assists;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public int getPenalties() {
        return penalties;
    }
    
    public String getTeam() {
        return team;
    }
    
    public int getGames() {
        return games;
    }
    
    @Override
    public int compareTo(Player player) {
        return player.getAssists() + player.getGoals() - this.assists - this.goals;
    }

    @Override
    public String toString() {
        String spaces = "                    ";
        return name + spaces.substring(0, 20 - name.length()) + team + spaces.substring(0,2 - (goals+90)/100) + Integer.toString(goals) + " +" + spaces.substring(0,2 - (assists+90)/100) + Integer.toString(assists) + " =" + spaces.substring(0,2 - (goals + assists + 90)/100) + Integer.toString(goals + assists);
    }
      
}
