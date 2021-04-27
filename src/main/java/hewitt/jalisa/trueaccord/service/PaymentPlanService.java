package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.PaymentPlan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
@Configurable
public class PaymentPlanService {
    private RestTemplate restTemplate = new RestTemplate();
    private String API = "https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans";


    public Map<Integer, PaymentPlan> getAllPaymentPlans(){

        ResponseEntity<PaymentPlan[]> res = restTemplate.getForEntity(API,PaymentPlan[].class);
        PaymentPlan[] plans = res.getBody();
        return buildMap(plans);
    }

    private Map<Integer, PaymentPlan> buildMap(PaymentPlan[] plans){
        Map<Integer, PaymentPlan> map = new HashMap<>();
        for(PaymentPlan plan: plans){
            map.put(plan.getDebtId(),plan);
        }

        return map;
    }
}
