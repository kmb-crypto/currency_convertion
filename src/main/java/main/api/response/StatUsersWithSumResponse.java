package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatUsersWithSumResponse {
    private int amount;
    private String currency;
    private List<Integer> usersId;
}
