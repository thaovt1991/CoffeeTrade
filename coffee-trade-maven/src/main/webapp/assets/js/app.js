class Drinks {
    constructor(id, image, name, type, quantity, price,description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.type = type ;
        this.quantity = quantity;
        this.price = price;
        this.description = description ;
    }
}

class OrderDetail{
    constructor(id, name , quantity, price , amount, idDresk , idDrinks, idOrder ) {
        this.id = id ;
        this.name = name ;
        this.quantity = quantity ;
        this.price = price ;
        this.amount = amount ;
        this.idDresk = idDresk ;
        this.idDrinks = idDrinks ;
        this.idOrder = idOrder ;
    }
}