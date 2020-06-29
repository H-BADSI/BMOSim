package bmosim.ihm3.Hibernate.hibernateFeed;

import javax.persistence.*;

@Entity
@Table(name = "Feed")
public class DBFeed{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFeed")
    private int idFeed;
    @Column(name = "avgSat")
    private double avgSat;
    @Column(name = "nbSat")
    private double nbSat;
    @Column(name = "nbAcc")
    private double nbAcc;
    @Column(name = "nbunAcc")
    private double nbunAcc;
    @Column(name = "waitAvgTime")
    private double waitAvgTime;
    @Column(name = "ordersTotalNb")
    private Integer ordersTotalNb;
    @Column(name = "purchasesTotalNb")
    private Integer purchasesTotalNb;
    @Column(name = "turnoverProbe")
    private double turnoverProbe;
    @Column(name = "refundProbe")
    private double refundProbe;
    @OneToOne
    @JoinColumn(name = "idInstance")
    private DBInstance idInstance;

    public DBFeed clone(){
        DBFeed twin = new DBFeed();
        twin.setIdInstance(idInstance);
        twin.setIdFeed(idFeed);
        twin.setAvgSat(avgSat);
        twin.setNbAcc(nbAcc);
        twin.setNbunAcc(nbunAcc);
        twin.setNbSat(nbSat);
        twin.setOrdersTotalNb(ordersTotalNb);
        twin.setPurchasesTotalNb(purchasesTotalNb);
        twin.setRefundProbe(refundProbe);
        twin.setTurnoverProbe(turnoverProbe);
        twin.setWaitAvgTime(waitAvgTime);
        return twin;
    }

    public int getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(int idFeed) {
        this.idFeed = idFeed;
    }

    public double getAvgSat() {
        return avgSat;
    }

    public void setAvgSat(double avgSat) {
        this.avgSat = avgSat;
    }

    public double getNbSat() {
        return nbSat;
    }

    public void setNbSat(double nbSat) {
        this.nbSat = nbSat;
    }

    public double getNbAcc() {
        return nbAcc;
    }

    public void setNbAcc(double nbAcc) {
        this.nbAcc = nbAcc;
    }

    public double getNbunAcc() {
        return nbunAcc;
    }

    public void setNbunAcc(double nbunAcc) {
        this.nbunAcc = nbunAcc;
    }

    public double getWaitAvgTime() {
        return waitAvgTime;
    }

    public void setWaitAvgTime(double waitAvgTime) {
        this.waitAvgTime = waitAvgTime;
    }

    public Integer getOrdersTotalNb() {
        return ordersTotalNb;
    }

    public void setOrdersTotalNb(Integer ordersTotalNb) {
        this.ordersTotalNb = ordersTotalNb;
    }

    public Integer getPurchasesTotalNb() {
        return purchasesTotalNb;
    }

    public void setPurchasesTotalNb(Integer purchasesTotalNb) {
        this.purchasesTotalNb = purchasesTotalNb;
    }

    public double getTurnoverProbe() {
        return turnoverProbe;
    }

    public void setTurnoverProbe(double turnoverProbe) {
        this.turnoverProbe = turnoverProbe;
    }

    public double getRefundProbe() {
        return refundProbe;
    }

    public void setRefundProbe(double refundProbe) {
        this.refundProbe = refundProbe;
    }

    public DBInstance getIdInstance() {
        return idInstance;
    }

    public void setIdInstance(DBInstance idInstance) {
        this.idInstance = idInstance;
    }
}