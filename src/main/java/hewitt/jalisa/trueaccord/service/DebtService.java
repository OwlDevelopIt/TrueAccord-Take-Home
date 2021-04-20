package hewitt.jalisa.trueaccord.service;

import hewitt.jalisa.trueaccord.models.Debt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class DebtService {
    private RestTemplate restTemplate = new RestTemplate();
    private String API = "https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts";


    public Map<Integer, Debt> getAllDebts(){
        ResponseEntity<Debt[]> res = restTemplate.getForEntity(API,Debt[].class);
        Debt[] debts = res.getBody();
        return buildMap(debts);
    }

    private Map<Integer, Debt> buildMap(Debt[] debts){
        Map<Integer, Debt> map = new HashMap<>();

        for(Debt d: debts){
            map.put(d.getId(),d);
        }

        return map;
    }
}
