public class Account
{
    private long money;
    private Integer accNumber;
    private Boolean isBlocked;

    public void setAccNumber(Integer accNumber)
    {
        this.accNumber = accNumber;
    }
    public Integer getAccNumber()
    {
        return accNumber;
    }
    public void setMoney(long money)
    {
        this.money = money;
    }
    public long getMoney() {
        return money;
    }
    public void setIsBlocked(Boolean isBlocked){
        this.isBlocked = isBlocked;
    }
    public Boolean getIsBlocked(){
        return isBlocked;
    }
}
