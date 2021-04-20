package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    private RestTemplate restTemplate = new RestTemplate();
    private String API = "https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments";

    public Map<Integer, List<Payment>> getAllPayments(){
        ResponseEntity<Payment[]> res = restTemplate.getForEntity(API,Payment[].class);
        Payment[] payments = res.getBody();
        return buildMap(payments);
    }

    private Map<Integer, List<Payment>> buildMap(Payment[] payments){
        Map<Integer, List<Payment>> map = new HashMap<>();

        for(Payment d: payments){
            int id = d.getPaymentPlanId();
            List<Payment> planList = map.getOrDefault(id,new ArrayList<>());
            planList.add(d);
            map.put(id,planList);
        }

        return map;
    }
}
