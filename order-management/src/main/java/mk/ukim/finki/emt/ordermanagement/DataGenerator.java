package mk.ukim.finki.emt.ordermanagement;

//@Component
//class DataGenerator {
//
//    private final OrderRepository orderRepository;
//
//    private final OrderItemRepository orderItemRepository;
//
//    DataGenerator(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
//        this.orderRepository = orderRepository;
//        this.orderItemRepository = orderItemRepository;
//    }
//
//    @PostConstruct
//    @Transactional
//    public void generateData() {
//
//        Set<OrderItem> orderItems;
//        Set<Order> orders;
//
//        if (orderItemRepository.findAll().size() == 0) {
//            orderItems = new HashSet<>();
//            orderItems.add(createOrderItem(new OrderItemId("1"), new ProductId("1"), new VariantId("1"), new Money(Currency.MKD, 500), new Quantity(1)));
//            orderItems.add(createOrderItem(new OrderItemId("2"), new ProductId("2"), new VariantId("2"), new Money(Currency.MKD, 1500), new Quantity(1)));
//            orderItems.add(createOrderItem(new OrderItemId("3"), new ProductId("1"), new VariantId("2"), new Money(Currency.MKD, 800), new Quantity(3)));
//            orderItems.add(createOrderItem(new OrderItemId("4"), new ProductId("3"), new VariantId("4"), new Money(Currency.MKD, 2000), new Quantity(2)));
//            orderItemRepository.saveAll(orderItems);
//        }
//
//        if (orderRepository.findAll().size() == 0) {
//            orders  = new HashSet<>();
//            orders.add(createOrder(new OrderId("1"), Instant.now(), Currency.MKD, new RecipientAddress("Kata Pockova", "Strumica", "Makedonija", Name.valueOf("Ljubica"), Name.valueOf("Boneva")), OrderState.PROCESSING));
//            orders.add(createOrder(new OrderId("2"), Instant.now(), Currency.MKD, new RecipientAddress("Kata Pockova", "Strumica", "Makedonija", Name.valueOf("Marija"), Name.valueOf("Shopova")), OrderState.PROCESSING));
//            orderRepository.saveAll(orders);
//
//            Order o = orderRepository.findById(new OrderId("1")).get();
//            OrderItem item = orderItemRepository.findById(new OrderItemId("2")).get();
//            o.addOrderItem(item);
//            orderRepository.save(o);
//        }
//    }
//
//    private OrderItem createOrderItem(OrderItemId orderItemId, ProductId productId, VariantId variantId, Money price, Quantity quantity) {
//        return new OrderItem(orderItemId, productId, variantId, price, quantity);
//    }
//
//    private Order createOrder(OrderId orderId, Instant orderedOn, Currency currency, RecipientAddress address, OrderState orderState) {
//        return new Order(orderId, orderedOn, currency, address, orderState);
//    }

//}
//////////////////////OrderRequest/////////////////////////
//{
//        "orderId":{
//        "id":"5"
//        },
//        "currency":"MKD",
//        "recipientAddress":{
//        "address":"Address",
//        "city":"City",
//        "country":"Country",
//        "fullName":{
//        "firstName":"First",
//        "lastName":"Last"
//        }
//        },
//        "orderItems":[
//        {
//        "orderItemId":{
//        "id":"5"
//        },
//        "productId":{
//        "id":"2"
//        },
//        "variantId":{
//        "id":"2"
//        },
//        "price":{
//        "currency":"MKD",
//        "amount":123
//        },
//        "quantity":{
//        "value":2
//        }
//        }
//        ]
//        }
