package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.Debt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DebtServiceTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    DebtService service;

    @Test
    public void test() {
        Debt[] debts = apiResponse();
        ResponseEntity<Debt[]> myEntity = new ResponseEntity<>(debts, HttpStatus.ACCEPTED);

        when(restTemplate.getForEntity(
                Mockito.anyString(),
                ArgumentMatchers.any(Class.class)
        )).thenReturn(myEntity);

        Map<Integer, Debt> res = service.getAllDebts();
        Assert.assertEquals(debts[0], res.get(0));
        Assert.assertEquals(debts[1], res.get(1));
    }

    private Debt[] apiResponse(){
        Debt debt0 = new Debt();
        Debt debt1 = new Debt();

        debt0.setId(0);
        debt0.setAmount(100.50);
        debt1.setId(1);
        debt1.setAmount(500.00);

        Debt[] arr = new Debt[] {debt0, debt1};
        return arr;
    }
}
