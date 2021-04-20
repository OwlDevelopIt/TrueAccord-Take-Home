package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.Debt;
import hewitt.jalisa.trueaccord.models.Payment;
import hewitt.jalisa.trueaccord.models.PaymentPlan;
import hewitt.jalisa.trueaccord.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProcessorService {
    @Autowired
    DebtService debtService;
    @Autowired
    PaymentPlanService paymentPlanService;
    @Autowired
    PaymentService paymentService;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public String processDebts(){
        Map<Integer, Debt> debts = debtService.getAllDebts();
        Map<Integer, List<Payment>> payments = paymentService.getAllPayments();
        Map<Integer, PaymentPlan> plans = paymentPlanService.getAllPaymentPlans();
        StringBuilder result = new StringBuilder();

        for(int debtId: debts.keySet()){
            PaymentPlan plan = plans.get(debtId);
            Debt debt = debts.get(debtId);
            List<Payment> debtPayments = null;

            if(plan!=null){
                debtPayments = payments.get(plan.getId());
            }

            processPayments(debt, plan, debtPayments);
            String jsonLineObj = Util.convertToJson(debt);
            result.append(jsonLineObj).append("\n");
        }
        return result.toString().trim();
    }

    private void processPayments(Debt debt, PaymentPlan plan, List<Payment> payments) {
        double remainingAmount = debt.getAmount();

        if(plan != null){
            debt.setHasPaymentPlan(true);
            remainingAmount = calculateRemainingAmount(plan.getAmountToPay(), payments);
        }

        double roundedAmount = Math.round(remainingAmount*100)/100.00;
        debt.setRemainingAmount(roundedAmount);

        if(roundedAmount <= 0 ){
            debt.setHasPaymentPlan(false);
        }

        if(debt.isHasPaymentPlan()) {
            calculateNextDueDate(debt, plan, payments);
        }
    }

    private double calculateRemainingAmount(double remainingAmount, List<Payment> payments ) {
        for(Payment payment: payments){
            remainingAmount -= payment.getAmount();
        }
        return remainingAmount;
    }

    private void calculateNextDueDate(Debt debt,PaymentPlan plan, List<Payment> debtPayments) {
        try {
            Date latestPayment = getLastPaymentDate(debtPayments);
            Date nextPaymentDate = formatter.parse(plan.getStartDate());
            Calendar c = new GregorianCalendar();
            c.setTime(nextPaymentDate);

            while (latestPayment.after(nextPaymentDate) || latestPayment.equals(nextPaymentDate)) {
                if (plan.getInstallmentFrequency().equals("WEEKLY")) {
                    c.add(Calendar.DATE, 7);
                } else {
                    c.add(Calendar.DATE, 14);
                }
                nextPaymentDate = c.getTime();
            }

            debt.setNextPaymentDueDate(formatter.format(nextPaymentDate));

        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Date getLastPaymentDate(List<Payment> debtPayments) throws ParseException {
        String lastPaymentDate = debtPayments.get(debtPayments.size()-1).getDate();
        return formatter.parse(lastPaymentDate);
    }
}
