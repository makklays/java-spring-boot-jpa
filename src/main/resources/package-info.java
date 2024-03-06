
Commands:

    mvn flyway:migrate
    mvn flyway:info
    mvn clean install wildfly:deploy

// add sequence to db
create table `barco_product_sequence` (
    `id` bigint(10) not null,
    `next_val` bigint(10) not null
);

