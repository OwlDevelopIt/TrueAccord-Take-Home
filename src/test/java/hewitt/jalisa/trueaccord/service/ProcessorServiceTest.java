package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.Debt;
import hewitt.jalisa.trueaccord.models.Payment;
import hewitt.jalisa.trueaccord.models.PaymentPlan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProcessorServiceTest {
    @Mock
    DebtService debtService;
    @Mock
    PaymentService paymentService;
    @Mock
    PaymentPlanService paymentPlanService;
    @InjectMocks
    ProcessorService service;

    @Test
    public void testProcessPayments1() {
        when(debtService.getAllDebts()).thenReturn(debtResponse());
        when(paymentService.getAllPayments()).thenReturn(paymentsResponse1());
        when(paymentPlanService.getAllPaymentPlans()).thenReturn(planResponse());
        String actual = service.processDebts();
        String expected = "{\"id\":0,\"amount\":100.5,\"is_in_payment_plan\":false,\"remaining_amount\":0.0,\"next_payment_due_date\":null}\n" +
                "{\"id\":1,\"amount\":500.0,\"is_in_payment_plan\":false,\"remaining_amount\":500.0,\"next_payment_due_date\":null}\n" +
                "{\"id\":2,\"amount\":250.3,\"is_in_payment_plan\":true,\"remaining_amount\":50.0,\"next_payment_due_date\":\"2020-02-19\"}\n" +
                "{\"id\":3,\"amount\":3000.0,\"is_in_payment_plan\":false,\"remaining_amount\":3000.0,\"next_payment_due_date\":null}\n" +
                "{\"id\":4,\"amount\":19550.0,\"is_in_payment_plan\":true,\"remaining_amount\":10937.5,\"next_payment_due_date\":\"2020-01-17\"}";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testProcessPayments2() {
        when(debtService.getAllDebts()).thenReturn(debtResponse());
        when(paymentService.getAllPayments()).thenReturn(paymentsResponse2());
        when(paymentPlanService.getAllPaymentPlans()).thenReturn(planResponse());
        String actual = service.processDebts();
        String expected = "{\"id\":0,\"amount\":100.5,\"is_in_payment_plan\":true,\"remaining_amount\":50.25,\"next_payment_due_date\":\"2020-03-20\"}\n" +
                "{\"id\":1,\"amount\":500.0,\"is_in_payment_plan\":false,\"remaining_amount\":500.0,\"next_payment_due_date\":null}\n" +
                "{\"id\":2,\"amount\":250.3,\"is_in_payment_plan\":true,\"remaining_amount\":50.0,\"next_payment_due_date\":\"2020-02-19\"}\n" +
                "{\"id\":3,\"amount\":3000.0,\"is_in_payment_plan\":false,\"remaining_amount\":3000.0,\"next_payment_due_date\":null}\n" +
                "{\"id\":4,\"amount\":19550.0,\"is_in_payment_plan\":true,\"remaining_amount\":10937.5,\"next_payment_due_date\":\"2020-01-17\"}";
        Assert.assertEquals(expected,actual);
    }

    private Map<Integer, Debt> debtResponse(){
        Map<Integer, Debt> map = new HashMap<>();
        Debt debt0 = new Debt();
        Debt debt1 = new Debt();
        Debt debt2 = new Debt();
        Debt debt3 = new Debt();
        Debt debt4 = new Debt();

        debt0.setId(0);
        debt0.setAmount(100.50);
        debt1.setId(1);
        debt1.setAmount(500.00);
        debt2.setId(2);
        debt2.setAmount(250.30);
        debt3.setId(3);
        debt3.setAmount(3000);
        debt4.setId(4);
        debt4.setAmount(19550);

        map.put(0,debt0);
        map.put(1,debt1);
        map.put(2,debt2);
        map.put(3,debt3);
        map.put(4,debt4);

        return map;
    }

    private Map<Integer, PaymentPlan> planResponse(){
        Map<Integer, PaymentPlan> map = new HashMap<>();

        PaymentPlan plan0 = new PaymentPlan();
        PaymentPlan plan1 = new PaymentPlan();
        PaymentPlan plan2 = new PaymentPlan();

        plan0.setId(0);
        plan0.setDebtid(0);
        plan0.setAmountToPay(100.50);
        plan0.setInstallmentAmount(50.25);
        plan0.setInstallmentFrequency("BI_WEEKLY");
        plan0.setStartDate("2020-03-06");

        plan1.setId(1);
        plan1.setDebtid(2);
        plan1.setAmountToPay(200.00);
        plan1.setInstallmentAmount(50.00);
        plan1.setInstallmentFrequency("WEEKLY");
        plan1.setStartDate("2020-01-01");

        plan2.setId(2);
        plan2.setDebtid(4);
        plan2.setAmountToPay(12500.00);
        plan2.setInstallmentAmount(1562.50);
        plan2.setInstallmentFrequency("WEEKLY");
        plan2.setStartDate("2020-01-10");

        map.put(0,plan0);
        map.put(2,plan1);
        map.put(4,plan2);

        return map;
    }

    private Map<Integer, List<Payment>> paymentsResponse1(){
        Map<Integer, List<Payment>> map = new HashMap<>();
        List<Payment> plan0 = new ArrayList<>();
        List<Payment> plan1 = new ArrayList<>();
        List<Payment> plan2 = new ArrayList<>();

        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        Payment payment3 = new Payment();
        Payment payment4 = new Payment();
        Payment payment5 = new Payment();

        payment1.setAmount(50.25);
        payment1.setDate("2020-03-06");
        payment1.setPaymentPlanId(0);
        payment2.setAmount(50.25);
        payment2.setDate("2020-03-13");
        payment2.setPaymentPlanId(0);
        plan0.add(payment1);
        plan0.add(payment2);

        payment3.setAmount(100.00);
        payment3.setDate("2020-01-01");
        payment3.setPaymentPlanId(1);
        payment4.setAmount(50.00);
        payment4.setDate("2020-02-13");
        payment4.setPaymentPlanId(1);
        plan1.add(payment3);
        plan1.add(payment4);

        payment5.setAmount(1562.50);
        payment5.setDate("2020-01-12");
        payment5.setPaymentPlanId(2);
        plan2.add(payment5);

        map.put(0,plan0);
        map.put(1,plan1);
        map.put(2,plan2);

        return map;
    }

    private Map<Integer, List<Payment>> paymentsResponse2(){
        Map<Integer, List<Payment>> map = new HashMap<>();
        List<Payment> plan0 = new ArrayList<>();
        List<Payment> plan1 = new ArrayList<>();
        List<Payment> plan2 = new ArrayList<>();

        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        Payment payment3 = new Payment();
        Payment payment4 = new Payment();

        payment1.setAmount(50.25);
        payment1.setDate("2020-03-06");
        payment1.setPaymentPlanId(0);
        plan0.add(payment1);

        payment2.setAmount(100.00);
        payment2.setDate("2020-01-01");
        payment2.setPaymentPlanId(1);
        payment3.setAmount(50.00);
        payment3.setDate("2020-02-13");
        payment3.setPaymentPlanId(1);
        plan1.add(payment2);
        plan1.add(payment3);

        payment4.setAmount(1562.50);
        payment4.setDate("2020-01-12");
        payment4.setPaymentPlanId(2);
        plan2.add(payment4);

        map.put(0,plan0);
        map.put(1,plan1);
        map.put(2,plan2);

        return map;
    }

}
