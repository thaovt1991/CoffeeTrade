// class Drinks {
//     constructor(id, image, name, type, quantity, price,description) {
//         this.id = id;
//         this.image = image;
//         this.name = name;
//         this.type = type ;
//         this.quantity = quantity;
//         this.price = price;
//         this.description = description ;
//     }
// }

// class OrderDetail{
//     constructor(id, nameDrinks , quantity, price , amount, idDrinks, idOrder ) {
//         this.id = id ;
//         this.nameDrinks = nameDrinks ;
//         this.quantity = quantity ;
//         this.price = price ;
//         this.amount = amount ;
//         this.idDrinks = idDrinks ;
//         this.idOrder = idOrder ;
//     }
// }
//
// class Order{
//     constructor(id, idDresk , amount ) {
//         this.id = id ;
//         this.idDresk = idDresk ;
//         this.idDrinks = idDrinks ;
//     }
// }

class Bill{
    constructor(idOrderBill,nameDesk,timeBegin, timeEnd, idStaff,nameStaff , promotionBill , surchargeBill ,taxBill , totalMoneyBill) {
        this.idOrderBill = idOrderBill ;
        this.nameDesk = nameDesk ;
        this.timeBegin= timeBegin ;
        this.timeEnd = timeEnd ;
        this.idStaff = idStaff
        this.nameStaff = nameStaff ;
        this.promotionBill = promotionBill ;
        this.surchargeBill = surchargeBill ;
        this.taxBill = taxBill ;
        this.totalMoneyBill = totalMoneyBill ;
    }
}
class Staff{
    constructor(idStaff , nameStaff) {
        this.idStaff = idStaff ;
        this.nameStaff = nameStaff ;
    }
}

