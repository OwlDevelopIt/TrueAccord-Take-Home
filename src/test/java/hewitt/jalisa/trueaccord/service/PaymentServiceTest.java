package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.Payment;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PaymentServiceTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    PaymentService service;

    @Test
    public void test() {
        Payment[] payments = apiResponse();
        List<Payment> plannedPayments0 = new ArrayList<>();
        List<Payment> plannedPayments1 = new ArrayList<>();
        plannedPayments0.add(payments[0]);
        plannedPayments0.add(payments[1]);
        plannedPayments1.add(payments[2]);

        ResponseEntity<Payment[]> myEntity = new ResponseEntity<>(payments, HttpStatus.ACCEPTED);

        when(restTemplate.getForEntity(
                Mockito.anyString(),
                ArgumentMatchers.any(Class.class)
        )).thenReturn(myEntity);

        Map<Integer, List<Payment>> actual = service.getAllPayments();
        Assert.assertEquals(plannedPayments0, actual.get(0));
        Assert.assertEquals(plannedPayments1, actual.get(1));
    }

    private Payment[] apiResponse(){

        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        Payment payment3 = new Payment();
        payment1.setAmount(50.25);
        payment1.setDate("2020-03-06");
        payment1.setPaymentPlanId(0);
        payment2.setAmount(50.25);
        payment2.setDate("2020-03-13");
        payment2.setPaymentPlanId(0);


        payment3.setAmount(100.00);
        payment3.setDate("2020-01-01");
        payment3.setPaymentPlanId(1);

        Payment[] arr = new Payment[] {payment1, payment2, payment3};
        return arr;
    }
}
