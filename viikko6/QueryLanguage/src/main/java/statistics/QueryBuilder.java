package statistics;

import statistics.matcher.*;

public class QueryBuilder {
    Matcher matcher;
    
    public QueryBuilder() {
        this.matcher = new All();
    }
    
    public Matcher build() {
        Matcher oldMatcher = this.matcher;
        this.matcher = new All();
        return oldMatcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }
    
}
