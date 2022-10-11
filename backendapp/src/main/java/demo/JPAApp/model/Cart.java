package demo.JPAApp.model;

import com.paypal.http.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private int userId;
    private List<CartItem> items;
    public double getTotalPrice(){
        return getItems().stream().collect(Collectors.summingDouble((CartItem::getTotalPrice)));
    }
}

