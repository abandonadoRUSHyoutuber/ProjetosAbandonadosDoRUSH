// 
// Decompiled by Procyon v0.5.30
// 

package edmarreports;

import java.util.Date;

public class Report
{
    private int id;
    private String sender;
    private String reported;
    private String reason;
    private Date date;
    
    public Report(final String sender, final String reported, final String reason, final Date date) {
        this.id = -1;
        this.sender = sender;
        this.reported = reported;
        this.reason = reason;
        this.date = date;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getSender() {
        return this.sender;
    }
    
    public String getReported() {
        return this.reported;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public Date getDate() {
        return this.date;
    }
}
