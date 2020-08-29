package yk.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import yk.ssm.domain.Product;

import java.util.List;

public interface ProductDao {
    @Select("select * from product where id =#{id}")
    public Product findById(String id) throws  Exception;

    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(id,productNum,productName,cityName,departureTime," +
            "productPrice,productDesc,productStatus) values('',#{productNum},#{productName}," +
            "#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
