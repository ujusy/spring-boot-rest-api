package com.uju.springbootrestapi.orderItem

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uju.springbootrestapi.delivery.Delivery
import com.uju.springbootrestapi.item.Item
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.order.Order
import javax.persistence.*

@Entity
class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    lateinit var item: Item

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    lateinit var order: Order

    var orderPrice: Int = 0

    var count: Int = 0

    // 비즈니스 로직
    fun cancel(): Unit {
        item.addStock(count)
    }
    
    fun getTotalPrice(): Int {
        return orderPrice * count
    }

    companion object {

        // 생성 메서드
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            var orderItem: OrderItem = OrderItem()
            orderItem.item = item
            orderItem.orderPrice = orderPrice
            orderItem.count = count

            item.removeStock(count)
            return orderItem
        }
    }
}
