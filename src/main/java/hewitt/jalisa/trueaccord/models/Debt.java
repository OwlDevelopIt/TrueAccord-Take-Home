package hewitt.jalisa.trueaccord.models;

import com.google.gson.annotations.SerializedName;

public class Debt {

    private int id;
    private double amount;
    @SerializedName("is_in_payment_plan")
    private boolean hasPaymentPlan = false;
    @SerializedName("remaining_amount")
    private double remainingAmount;
    @SerializedName("next_payment_due_date")
    private String nextPaymentDueDate = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public boolean isHasPaymentPlan() {
        return hasPaymentPlan;
    }

    public void setHasPaymentPlan(boolean hasPaymentPlan) {
        this.hasPaymentPlan = hasPaymentPlan;
    }

    public String getNextPaymentDueDate() {
        return nextPaymentDueDate;
    }

    public void setNextPaymentDueDate(String nextPaymentDueDate) {
        this.nextPaymentDueDate = nextPaymentDueDate;
    }
}
