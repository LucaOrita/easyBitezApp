package ro.easybites.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Document("dumped_orders")
public class Dump{

    private Order o;

    public Dump(Order o) {
        this.o = o;
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }
}
