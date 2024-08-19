package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InvoiceTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Invoice invoice = new Invoice();

        // Test setter
        invoice.setId(1L);
        invoice.setTitle("#001-2024-02-26");
        invoice.setDescription("This is invoice");
        //invoice.setTransportationId(1L);
        invoice.setAmount(100.00f);
        invoice.setStatus("not_paid");
        invoice.setCreatedAt(timestamp);
        invoice.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, invoice.getId());
        Assertions.assertEquals("#001-2024-02-26", invoice.getTitle());
        Assertions.assertEquals("This is invoice", invoice.getDescription());
        Assertions.assertEquals(100.00f, invoice.getAmount());
        Assertions.assertEquals("not_paid", invoice.getStatus());
        Assertions.assertEquals(timestamp, invoice.getCreatedAt());
        Assertions.assertEquals(timestamp, invoice.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("TUAPSE");
        barco.setYear(2022);
        barco.setSpeedometer(2000);

        City city = new City();
        city.setId(1L);
        city.setTitle("City 1");

        Storehouse storehouse = new Storehouse();
        storehouse.setId(1L);
        storehouse.setTitle("Storehouse 1");
        storehouse.setCity(city);

        Transportation transportation = new Transportation();
        transportation.setId(1L);
        transportation.setDistance(800);
        transportation.setWeight(100);
        transportation.setBarco(barco);
        transportation.setStorehouse(storehouse);

        Invoice invoice1 = new Invoice();
        invoice1.setId(1L);
        invoice1.setTitle("#001-2024-02-26");
        invoice1.setDescription("This is invoice");
        invoice1.setTransportation(transportation);
        invoice1.setAmount(100.00f);
        invoice1.setStatus("not_paid");
        invoice1.setCreatedAt(timestamp);
        invoice1.setUpdatedAt(timestamp);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1L);
        invoice2.setTitle("#001-2024-02-26");
        invoice2.setDescription("This is invoice");
        invoice2.setTransportation(transportation);
        invoice2.setAmount(100.00f);
        invoice2.setStatus("not_paid");
        invoice2.setCreatedAt(timestamp);
        invoice2.setUpdatedAt(timestamp);

        Invoice invoice3 = new Invoice();
        invoice3.setId(2L);
        invoice3.setTitle("#002-2024-02-26");
        invoice3.setDescription("This is invoice2");
        invoice3.setTransportation(transportation);
        invoice3.setAmount(102.00f);
        invoice3.setStatus("paid");
        invoice3.setCreatedAt(timestamp);
        invoice3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(invoice1, invoice2);
        Assertions.assertNotEquals(invoice1, invoice3);

        // Test equals hashCode()
        Assertions.assertEquals(invoice1.hashCode(), invoice2.hashCode());
        Assertions.assertNotEquals(invoice1.hashCode(), invoice3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("TUAPSE");
        barco.setYear(2022);
        barco.setSpeedometer(2000);

        City city = new City();
        city.setId(1L);
        city.setTitle("City 1");

        Storehouse storehouse = new Storehouse();
        storehouse.setId(1L);
        storehouse.setTitle("Storehouse 1");
        storehouse.setCity(city);

        Transportation transportation = new Transportation();
        transportation.setId(1L);
        transportation.setDistance(800);
        transportation.setWeight(100);
        transportation.setBarco(barco);
        transportation.setStorehouse(storehouse);

        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setTitle("#001-2024-02-26");
        invoice.setDescription("This is invoice");
        invoice.setTransportation(transportation);
        invoice.setAmount(100.00f);
        invoice.setStatus("not_paid");
        invoice.setCreatedAt(timestamp);
        invoice.setUpdatedAt(timestamp);

        String ExpectedToString = "Invoice{" +
                "id=1" +
                ", title='#001-2024-02-26'" +
                ", description='This is invoice'" +
                ", transportation=Transportation{id=1, barco=Barco{id=1, title='TUAPSE', description='null', year=2022, weight=null, speedometer=2000, createdAt=null, updatedAt=null}, storehouse=StoreHouse{id=1, title='Storehouse 1', description='null', createdAt=null, updatedAt=null}, distance=800, weight=100, createdAt=null, updatedAt=null}" +
                ", amount=100.0" +
                ", status=not_paid" +
                ", createdAt=2024-02-16 12:00:00.0" +
                ", updatedAt=2024-02-16 12:00:00.0" +
                "}";

        Assertions.assertEquals(ExpectedToString, invoice.toString());
    }
}

