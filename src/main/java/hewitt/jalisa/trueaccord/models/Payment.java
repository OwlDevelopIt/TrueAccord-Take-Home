package hewitt.jalisa.trueaccord.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

    @JsonProperty("payment_plan_id")
    private int paymentPlanId;
    private double amount;
    private String date;

    public int getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(int paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
