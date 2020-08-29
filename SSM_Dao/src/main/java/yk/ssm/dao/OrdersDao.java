package yk.ssm.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import yk.ssm.domain.Member;
import yk.ssm.domain.Orders;
import yk.ssm.domain.Product;
import yk.ssm.domain.Traveller;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property ="orderNum",column = "orderNum"),
            @Result(property ="orderTime",column = "orderTime"),
            @Result(property ="orderStatus",column = "orderStatus"),
            @Result(property ="peopleCount",column = "peopleCount"),
            @Result(property ="payType",column = "payType"),
            @Result(property ="payTypeStr",column = "payTypeStr"),
            @Result(property ="orderDesc",column = "orderDesc"),
            @Result(property ="product",column = "productId",javaType = Product.class,one = @One(select ="yk.ssm.dao.ProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property ="orderNum",column = "orderNum"),
            @Result(property ="orderTime",column = "orderTime"),
            @Result(property ="orderStatus",column = "orderStatus"),
            @Result(property ="peopleCount",column = "peopleCount"),
            @Result(property ="payType",column = "payType"),
            @Result(property ="payTypeStr",column = "payTypeStr"),
            @Result(property ="orderDesc",column = "orderDesc"),
            @Result(property ="product",column = "productId",javaType = Product.class,one = @One(select ="yk.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one=@One(select = "yk.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "yk.ssm.dao.TravellerDao.findByOrdersId")),
    })
    public Orders findById(String ordersId) throws Exception;
}
