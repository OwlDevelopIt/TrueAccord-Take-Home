package hewitt.jalisa.trueaccord;

import hewitt.jalisa.trueaccord.service.ProcessorService;
import hewitt.jalisa.trueaccord.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TrueAccordCommandLineApp implements CommandLineRunner {
    @Autowired
    ProcessorService service;

    @Override
    public void run(String...args) {
        Util.print("Printing Debts");
        String debtJsonLines = service.processDebts();
        Util.print(debtJsonLines.trim());
    }
}
