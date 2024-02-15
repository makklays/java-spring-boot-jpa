package com.techmatrix18.model;

// example: one-to-many
//    @Entity
//    @Table(name = "users")
//    class User {
//        @OneToMany(fetch = FetchType.LAZY, mappedBy = "position", cascade = CascadeType.ALL)
//        @JoinColumn(name = "position_id", insertable = false, updatable = false)
//        private List<User> users;
//    }

// example: many-to-one
//    @Entity
//    @Table(name = "positions")
//    class Position {
//        @ManyToOne
//        @JoinColumn(name = "position_id", nullable = false)
//        private Position position;
//    }

// example: @Bean
//    @Component
//    class Position {
//        @Bean('myBean')
//        @Scope('prototype') // области видимости бинов:
//

