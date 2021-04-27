package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.PaymentPlan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PaymentPlanServiceTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    PaymentPlanService service;

    @Test
    public void test() {
        PaymentPlan[] plans = apiResponse();
        ResponseEntity<PaymentPlan[]> myEntity = new ResponseEntity<>(plans, HttpStatus.ACCEPTED);

        when(restTemplate.getForEntity(
                Mockito.anyString(),
                ArgumentMatchers.any(Class.class)
        )).thenReturn(myEntity);

        Map<Integer, PaymentPlan> actual = service.getAllPaymentPlans();
        Assert.assertEquals(plans[0], actual.get(0));
        Assert.assertEquals(plans[1], actual.get(2));
    }

    private PaymentPlan[] apiResponse(){
        PaymentPlan plan0 = new PaymentPlan();
        PaymentPlan plan1 = new PaymentPlan();

        plan0.setId(0);
        plan0.setDebtId(0);
        plan0.setAmountToPay(100.50);
        plan0.setInstallmentAmount(50.25);
        plan0.setInstallmentFrequency("BI_WEEKLY");
        plan0.setStartDate("2020-03-06");

        plan1.setId(1);
        plan1.setDebtId(2);
        plan1.setAmountToPay(200.00);
        plan1.setInstallmentAmount(50.00);
        plan1.setInstallmentFrequency("WEEKLY");
        plan1.setStartDate("2020-01-01");

        PaymentPlan[] arr = new PaymentPlan[] {plan0, plan1};
        return arr;
    }
}
