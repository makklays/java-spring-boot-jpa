import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;Commands:

    mvn flyway:migrate
    mvn flyway:info
    mvn clean install wildfly:deploy

// add table (sequence) to db - not correct
create table `barco_product_sequence` (
    `id` bigint(10) not null,
    `next_val` bigint(10) not null
);

// add sequence to db - correct
DROP SEQUENCE IF EXISTS `barco_products_seq`;
CREATE SEQUENCE `barco_products_seq`;

// TODO
// Return relation entre models User and Position (one-to-many) ..


// Return relation entre models User and Position (many-to-one) ..
@ManyToOne
@JoinColumn(name = "position_id") //, nullable = false)
private Position position;

