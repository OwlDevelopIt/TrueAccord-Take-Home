package hewitt.jalisa.trueaccord.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentPlan {

    private int id;
    @JsonProperty("debt_id")
    private int debtId;
    @JsonProperty("amount_to_pay")
    private double amountToPay;
    @JsonProperty("installment_frequency")
    private String installmentFrequency;
    @JsonProperty("installment_amount")
    private double installmentAmount;
    @JsonProperty("start_date")
    private String startDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDebtId() {
        return debtId;
    }

    public void setDebtId(int debtId) {
        this.debtId = debtId;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getInstallmentFrequency() {
        return installmentFrequency;
    }

    public void setInstallmentFrequency(String installmentFrequency) {
        this.installmentFrequency = installmentFrequency;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
