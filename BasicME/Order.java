class Order {
    final String[] validOrders = {"BUY", "SEL", "CAN"};
    String type;
    int quantity; // for cancel order, this holds the sequence id of the cancelled order
    float price;
    int sequenceId;

    public Order(String _type, int _quantity, float _price) {
        boolean isValidOrder = false;
        for (int i = 0; i < validOrders.length; i++) {
            if (validOrders[i].equals(_type)) {
                isValidOrder = true;
                break;
            }
        }
        if (!isValidOrder) {
            throw new RuntimeException("Not a valid order type.");
        }
        this.type = _type;
        this.quantity = _quantity;
        this.price = _price;
        this.sequenceId = -1;
    }

    public int generateSequenceId(Order previousOrder) {
        if (previousOrder == null) {
            this.sequenceId = 1;
        }
        else {
            this.sequenceId = previousOrder.sequenceId + 1;
        }
        return this.sequenceId;
    }

    @Override
    public String toString() {
        return "(" + this.type + "," + Integer.toString(this.quantity) + "," +
                Float.toString(this.price) + "," + Integer.toString(this.sequenceId) + ")";
    }
}
