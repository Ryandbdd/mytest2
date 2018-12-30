package com.itheima.jpa.domain;

import javax.persistence.*;

@Entity
@Table(name = "cst_customer", schema = "itcastspringdatajpa", catalog = "")
public class CstCustomer {
    private long custId;
    private String custAddress;
    private String custIndustry;
    private String custLevel;
    private String custName;
    private String custPhone;
    private String custSource;

    @Id
    @Column(name = "cust_id", nullable = false)
    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    @Basic
    @Column(name = "cust_address", nullable = true, length = 255)
    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Basic
    @Column(name = "cust_industry", nullable = true, length = 255)
    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    @Basic
    @Column(name = "cust_level", nullable = true, length = 255)
    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    @Basic
    @Column(name = "cust_name", nullable = true, length = 255)
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "cust_phone", nullable = true, length = 255)
    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Basic
    @Column(name = "cust_source", nullable = true, length = 255)
    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CstCustomer that = (CstCustomer) o;

        if (custId != that.custId) return false;
        if (custAddress != null ? !custAddress.equals(that.custAddress) : that.custAddress != null) return false;
        if (custIndustry != null ? !custIndustry.equals(that.custIndustry) : that.custIndustry != null) return false;
        if (custLevel != null ? !custLevel.equals(that.custLevel) : that.custLevel != null) return false;
        if (custName != null ? !custName.equals(that.custName) : that.custName != null) return false;
        if (custPhone != null ? !custPhone.equals(that.custPhone) : that.custPhone != null) return false;
        if (custSource != null ? !custSource.equals(that.custSource) : that.custSource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (custId ^ (custId >>> 32));
        result = 31 * result + (custAddress != null ? custAddress.hashCode() : 0);
        result = 31 * result + (custIndustry != null ? custIndustry.hashCode() : 0);
        result = 31 * result + (custLevel != null ? custLevel.hashCode() : 0);
        result = 31 * result + (custName != null ? custName.hashCode() : 0);
        result = 31 * result + (custPhone != null ? custPhone.hashCode() : 0);
        result = 31 * result + (custSource != null ? custSource.hashCode() : 0);
        return result;
    }
}
