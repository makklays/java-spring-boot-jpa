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

// (арг1, арг2...) -> { тело }
//
// (тип1 арг1, тип2 арг2...) -> { тело }

// Старый способ:
/*List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
int sum = 0;
for(Integer n : list) {
int x = n * n;
sum = sum + x;
}
System.out.println(sum);*/

// Новый способ:
/*List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
int sum = list.stream().map(x -> x*x).reduce((x,y) -> x + y).get();
System.out.println(sum);*/

