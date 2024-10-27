package sit.int202.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employeeNumber", nullable = false)
    private Integer id;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "officeCode", nullable = false)
    private Office officeCode;

    @Column(name = "jobTitle", nullable = false, length = 50)
    private String jobTitle;

    @OneToMany(mappedBy = "salesRepEmployeeNumber")
    private Set<Customer> customers = new LinkedHashSet<>();

    @Getter
    @Setter
    @Entity
    @Table(name = "products")
    public static class Product {
        @Id
        @Column(name = "productCode", nullable = false, length = 15)
        private String productCode;

        @Column(name = "productName", nullable = false, length = 70)
        private String productName;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "productLine", nullable = false)
        private Productline productLine;

        @Column(name = "productScale", nullable = false, length = 10)
        private String productScale;

        @Column(name = "productVendor", nullable = false, length = 50)
        private String productVendor;

        @Lob
        @Column(name = "productDescription", nullable = false)
        private String productDescription;

        @Column(name = "quantityInStock", nullable = false)
        private Short quantityInStock;

        @Column(name = "buyPrice", nullable = false, precision = 10, scale = 2)
        private BigDecimal buyPrice;

        @Column(name = "MSRP", nullable = false, precision = 10, scale = 2)
        private BigDecimal msrp;

    }

    @Getter
    @Setter
    @Entity
    @Table(name = "productlines")
    public static class Productline {
        @Id
        @Column(name = "productLine", nullable = false, length = 50)
        private String productLine;

        @Column(name = "textDescription", length = 4000)
        private String textDescription;

        @Lob
        @Column(name = "htmlDescription")
        private String htmlDescription;

        @Column(name = "image")
        private byte[] image;

    }
}