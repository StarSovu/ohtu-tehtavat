
package ohtu;

public class Player {
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
    public String toString() {
        return name + " assists " + Integer.toString(assists) + " goals " + Integer.toString(goals);
    }
      
}
