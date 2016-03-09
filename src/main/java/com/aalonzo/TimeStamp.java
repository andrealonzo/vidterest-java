
/**
 *  TimeStamp Model POJO
 * 
 **/
 
public class TimeStamp{
    Long unixDate;
    String naturalLanguageDate;
    public TimeStamp(){
        unixDate = null;
        naturalLanguageDate = null;
    }
    public TimeStamp(Long unixDate, String naturalLanguageDate){
        this.unixDate = unixDate;
        this.naturalLanguageDate = naturalLanguageDate;
    }
    public void setUnixDate(Long unixDate){
        this.unixDate = unixDate;
    }
    public Long getUnixDate(){
        return unixDate;
    }
    public void setNaturalLanguageDate(String naturalLanguageDate){
        this.naturalLanguageDate = naturalLanguageDate;
    }
    public String getNaturalLanguageDate(){
        return naturalLanguageDate;
    }
}