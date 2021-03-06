package main.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_currency", nullable = false, columnDefinition = "enum('RUB','USD','EUR','GBP')")
    private Currency currencyFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_currency", nullable = false, columnDefinition = "enum('RUB','USD','EUR','GBP')")
    private Currency currencyTo;

    @Column(name = "amount", nullable = false)
    private long amountInCents;

    @Column(name = "exchange_amount", nullable = false)
    private long resultExchangeAmountInCents;

    @Column(nullable = false)
    private Timestamp time;


}
