package faculty.aspect;

import org.springframework.stereotype.Component;

@Component
public class SimpleService {

    @LogExecutionTime
    public void doSomeStuff() {
        System.out.println("Imitating doing some usefull stuff");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
