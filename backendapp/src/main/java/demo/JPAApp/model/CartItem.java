package demo.JPAApp.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.stream.Collectors;

@Data
@Builder
public class CartItem implements Serializable {
 private int id;
 private String title;
 private int count;
 private Double price;

 public void increaseCount(int productCount) {
  count +=productCount;
 }
 public void decreaseCount(int productCount) {
  count -=productCount;
 }
 public double getTotalPrice(){
  return count*price;
 }
}
