// let arrayOrderDetail = new Array()
let idDrinksArr = new Array();
let quantityArr = new Array();
let oldQuantity = 0;
let total_money = 0;
let new_bill = new Bill();
let new_staff = new Staff();

// let orderDetailArr = [];

// let orderDetail = new OrderDetail();




function callStaff(id_staff) {
    $.ajax({
        type: "GET",
        url: `/api/staff/${id_staff}`
    }).done(function (staff) {
        new_staff = {
            idStaff: staff.id,
            nameStaff: staff.fullName,
        }
    })
}


let clickDesk = function () {
    console.log("desk on click")
    $(".desk").on("click", function () {
        $("#order").removeClass("d-none");
        $(".add-drinks-to-order").removeClass("d-none");
        $("#list-desk-not-empty").addClass("d-none");
        let idDesk = $(this).data('id');
        console.log(idDesk)

        $.ajax({
            type: "GET",
            url: `/api/desk/${idDesk}`
        }).done(function (desk) {
            // console.log(desk)
            // $("desk-order").html(desk.name)
            if (desk.empty) {
                $("#done ,#btn-payment-order").prop("disabled",true);
                idDrinksArr = new Array()
                quantityArr = new Array()
                drawNewOrderOfDesk()
                drawDeskChecked(idDesk);
                // $("#select_desk").html(idDesk);
                $("#name-desk").html(desk.name);
                let dt = new Date();
                let day = dt.getFullYear() + "-" + dt.getMonth() + "-" + dt.getDate()
                let time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
                $("#time-order").html(time);
                $("#day-order").html(day);
                $("#new-order").removeClass("d-none");

                $("#new-order").removeClass("d-none");
                $("#old-order").addClass("d-none");
                // addOrder();
                // clickDesk();
            } else {
                getOrderDetailOfDesk(desk.id)
                drawOrderNotPayment(desk.id)
                $("#old-order").removeClass("d-none");
                $("#new-order").addClass("d-none");
                $("#btn-payment-order").prop("disabled",false);
                $("#btn-edit-order").prop("disabled",true);

            }
        })
    });
}


addOrder = function () {
    $("#done").on("click", function () {
        // let idDesk = $(this).data("id");
        let idDesk = $("#name_desk").val();
        console.log(idDesk)

        $.ajax({
            type: "GET",
            url: `/api/desk/${idDesk}`
        }).done(function (desk) {
            // console.log(desk)
            console.log(idDrinksArr)
            createOrder(desk).then(function () {
                updateDesk(desk,false).then(function () {
                    drawDeskAll().then(function () {
                        // drawDeskNotEmpty().then(function (){
                        clickDesk();
                        // })
                    });
                    // clickDesk();
                });
            });

            // drawDeskAll();
            // drawDeskNotEmpty();
            backOrderDesk();
            alert("Success create order")

        }).fail(function () {
            alert("Fail addOrder")
        });

    })
}



$(".add-drinks-to-order").on("click", function () {
    let idDrinks = $(this).data("id");
    $("#done, #btn-payment-order").prop("disabled", false);
    $("#btn-edit-order").prop("disabled", false);
    $("#btn-payment-order").prop("disabled", true);

    $.ajax({
        type: "GET",
        url: `/api/drinks/${idDrinks}`
    }).done(function (drinks) {
        if (!idDrinksArr.includes(drinks.id)) {
            idDrinksArr.push(drinks.id);
            $("#tbListOrderDetails tbody").append(getsShowOrderDetails(drinks, idDrinksArr.length));
            quantityArr.push(1);
        } else {
            let newQuantity = parseInt($("#quantity_" + idDrinks).val()) + 1;
            quantityArr[idDrinksArr.indexOf(drinks.id)] = newQuantity;
            let total_money_drinks = drinks.price * newQuantity
            console.log(total_money_drinks)
            $("#quantity_" + idDrinks).val(newQuantity);
            $("#total_" + idDrinks).html(total_money_drinks);
        }
        total_money += drinks.price;
        $("#total_money").html(total_money + " VND");
        $("#total_money_order").val(total_money);
    })
})


// $(".quantity_drinks").change(function () {
//     alert("ok")
// })
// //     // let idDrinks = $(this).data("id")
// //     // let quantity = $(this).val();
// //     // $.ajax({
// //     //     type: "GET",
// //     //     url: `/api/drinks/${idDrinks}`
// //     // }).done(function (drinks) {
// //     //     let amount = quantity * drinks.price ;
// //     //     $("total_"+drinks.id).val(amount) ;
// //     // })
// // })


function getsShowOrderDetails(drinks, index) {
    return str = `
               <tr id='tr_${drinks.id}'>
                <td><span>${index}</span>
                <input type="hidden" class="idOrderDetail"  id="drinks_${drinks.id}" value="0">
                </td>
                <td >${drinks.name}</td>
                 <td>
                 <input type="number" class="quantity_drinks" id="quantity_${drinks.id}" name="quantity_${drinks.id}" style="width: 60px" data-id="${drinks.id}" value="1">
                 </td>
            <td>${drinks.price}</td>
            <td id="total_${drinks.id}">${drinks.price}</td>
            </tr>
`
}

// Tạo order
createOrder = function (desk) {
    let idStaff = $("#name_staff").val();
    let order = {
        desk: desk,
        create_by: idStaff,
    }
    return $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "/api/order/create",
        type: "POST",
        data: JSON.stringify(order)
    }).done(function (orderResp) {
        console.log(orderResp);
        for (let i = 0; i < idDrinksArr.length; i++) {

            // let orrderDetailDTO = {
            //     id_drink: idDrinksArr[i],
            //     id_order: orderResp.id,
            //     quantity: quantityArr[i]
            // }
            // orderDetailArr.push(orrderDetailDTO);

            crateOrderDetail(orderResp.id, idDrinksArr[i])
        }

        // crateOrderDetail(orderResp.id, orderDetailArr)

        // orderDetailArr = new Array();

        idDrinksArr = new Array()
        quantityArr = new Array()
    }).fail(function () {
        $.notify("Tạo order Không thành công",)
    })
}

// Nhận order bằng desk bang id.

getOrderByDeskId = function (id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "/api/order/getorderbydeskid/" + id,
        type: "GET"
    }).done(function (order) {
        // console.log(order);

    }).fail(function () {
        alert("fail")
        $.notify("order fail", "error")
    })
}

function crateOrderDetail(idOrder, idDrinks) {
    // let quantity = $("#quantity_" + idDrinks).val()
    let quantity = quantityArr[idDrinksArr.indexOf(idDrinks)];
    console.log(quantity)

    let orrderDetailDTO = {
        id_drink: idDrinks,
        id_order: idOrder,
        quantity: quantity,
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: `/api/order_detail/create`,
        data: JSON.stringify(orrderDetailDTO)
    }).done(function (orderDetail) {
        // console.log(orderDetail)
    }).fail(function () {
        alert("fails create order detail")
        // clickDesk();
    })


}

/////// order details
getOrderDetailOfDesk = function (desk_id) {
    $.ajax({
        url: `/api/order_detail/orderdetailofdeskid/${desk_id}`,
        type: "GET",
    }).done(function (orderdetails) {
        console.log(orderdetails);
        let str = "";
        let str_bill = "";
        let sum = 0;
        for (i = 0; i < orderdetails.length; i++) {
            idDrinksArr.push(orderdetails[i].id_drink);
            quantityArr.push(orderdetails[i].quantity)
            // orderDetail = new OrderDetail(orderdetails[i].id, orderdetails[i].name, orderdetails[i].quantity, orderdetails[i].price, orderdetails[i].amount, orderdetails[i].id_drink, orderdetails[i].id_order);
            // arrayOrderDetail.push(orderDetail);
            sum += orderdetails[i].amount

            str += `
               <tr id='tr_${orderdetails[i].id_drink}'>
                <td> <span>${i + 1}</span>
                <input type="hidden" class="idOrderDetail"  id="drinks_${orderdetails[i].id_drink}" value="${orderdetails[i].id}">
                </td>
                <td >${orderdetails[i].name}</td>
                 <td>
                 <input type="number" class="quantity_drinks" id="quantity_${orderdetails[i].id_drink}" name="quantity_${orderdetails[i].id_drink}" style="width: 60px" data-id="${orderdetails[i].id_drink}" value="${orderdetails[i].quantity}">
                 </td>
            <td>${orderdetails[i].price}</td>
            <td id="total_${orderdetails[i].id_drink}">${orderdetails[i].amount}</td>
            </tr>`


            str_bill += `<tr'>
                <td> <span>${i + 1}</span>
                </td>
                <td >${orderdetails[i].name}</td>
                 <td style="width: 60px" >${orderdetails[i].quantity}
                 </td>
            <td>${orderdetails[i].price}</td>
            <td>${orderdetails[i].amount}</td>
            </tr>`

        }
        drawOrderNotPayment(desk_id);
        $("#tbListOrderDetails tbody").html(str)
        $("#tbListOrderDetailsBill tbody").html(str_bill);
        console.log(sum)
        $("#total_money").html(sum + " VND")
        $("#total_money_order").val(sum);
        total_money = sum;

    }).fail(function () {
        alert("Tải thông tin bàn không thành công")
        // $.notify("Tải thông tin bàn không thành công", "error");
    })
}


function editOrderDetail(idOrder, idDrinks) {
    let quantity = quantityArr[idDrinksArr.indexOf(idDrinks)];

    let idOrderDetail = $("#drinks_" + idDrinks).val()
    console.log(idOrderDetail)
    if (idOrderDetail != 0) {
        let orrderDetailDTO = {
            id: idOrderDetail,
            id_drink: idDrinks,
            id_order: idOrder,
            quantity: quantity,
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            url: `/api/order_detail/update`,
            data: JSON.stringify(orrderDetailDTO)
        }).done(function (orderDetail) {
            // alert("edit success order detail")
        }).fail(function () {
            alert("Fail edit order detail")
        })
    } else {
        crateOrderDetail(idOrder, idDrinks)
    }
}

//click de sua hhoạc thêm oder
// editOrder = function () {
$("#btn-edit-order").on("click", function () {
    let idDesk = $("#name_desk").val();
    // console.log(idDesk)
    $.ajax({
        type: "GET",
        url: "/api/order/getorderbydeskid/" + idDesk,
    }).done(function (order) {
        for (let i = 0; i < idDrinksArr.length; i++) {
            editOrderDetail(order.id, idDrinksArr[i]);
        }

        idDrinksArr = new Array()
        quantityArr = new Array()
        drawNewOrderOfDesk()
        $("#order").addClass("d-none");
        $("#list-desk-not-empty").removeClass("d-none");
        $(".add-drinks-to-order").addClass("d-none")
    })

})



function drawOrderNotPayment(desk_id) {
    $.ajax({
        type: "GET",
        url: `/api/order/getorderbydeskiddto/${desk_id}`
    }).done(function (orderDto) {
        console.log(orderDto)
        let str = `<select id="name_desk" name="name_desk">
                       <option data-id="${desk_id}" value="${desk_id}" selected>${orderDto.nameDesk}</option>
                    </select>
                    <input type="hidden" id="id_order"  value="${orderDto.id}">
                    <input type="hidden" id="name_desk_order" value="${orderDto.nameDesk}">
                    <input type="hidden" id="time_begin" value="${orderDto.createAtTime} - ${orderDto.createAtDay}">
                     <input type="hidden" id="idStaffOrder" value="${orderDto.idStaff}">
                  `
        $("#select_desk").html(str)
        $("#time-order").html(orderDto.createAtTime);
        $("#day-order").html(orderDto.createAtDay);
    })
}


$("#btn-payment-order").on("click", function () {
    // callStaff($("#idStaffOrder").val())
    console.log(new_staff)
    new_bill.idOrderBill = $("#id_order").val();
    new_bill.nameDesk = $("#name_desk_order").val();
    new_bill.timeBegin = $("#time_begin").val();
    let dt = new Date();
    new_bill.timeEnd = dt.getMonth() + ":" + dt.getMinutes() + ":" + dt.getSeconds() + " - " + dt.getFullYear() + "-" + dt.getMonth() + "-" + dt.getDate();
    new_bill.idStaff = $("#idStaffOrder").val()
    new_bill.nameStaff = $("#idStaffOrder").val();//tam thoi doi id vao
    let money_reality = $("#total_money_order").val();
    new_bill.promotionBill = 0;
    new_bill.surchargeBill = 0;
    new_bill.taxBill = 10;
    new_bill.totalMoneyBill = Math.round(money_reality * (1 - new_bill.promotionBill / 100 + new_bill.surchargeBill / 100) * (1 + new_bill.taxBill / 100));

    $("#id_order_of_bill").html(new_bill.idOrderBill);
    $("#desk_of_bill").html(new_bill.nameDesk);
    $("#time_order").html(new_bill.timeBegin);
    $("#time_bill").html(new_bill.timeEnd);
    $("#staff_of_bill").html(new_bill.nameStaff);
    $("#total_money_bill").html(money_reality + " VND");
    $("#promotion").html(new_bill.promotionBill + "%");
    $("#money_promotion").html((new_bill.promotionBill * money_reality / 100) + " VND");
    $("#surcharge").html(new_bill.surchargeBill + "%");
    $("#money_surcharge").html((new_bill.surchargeBill * money_reality / 100) + " VND");
    $("#tax").html(new_bill.taxBill + "%");
    $("#money_tax").html(new_bill.taxBill * money_reality / 100 + " VND");
    $("#total_money_bill_after").html(new_bill.totalMoneyBill + " VND");

})

$("#create_bill").on("click", function () {
    let billOdt = {
        idOrder: new_bill.idOrderBill,
        idStaff: new_bill.idStaff,
        promotion: new_bill.promotionBill,
        surcharge: new_bill.surchargeBill,
        tax: new_bill.taxBill,
        totalMoney: new_bill.totalMoneyBill
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: `/api/bill/create`,
        data: JSON.stringify(billOdt)
    }).done(function (billDto) {

         drawDeskNotEmpty()
         drawDeskAll().then(function () {
             clickDesk();
         })

        idDrinksArr = new Array()
        quantityArr = new Array()
        drawNewOrderOfDesk()
        $("#order").addClass("d-none");
        $("#list-desk-not-empty").removeClass("d-none");
        $(".add-drinks-to-order").addClass("d-none")
        alert("Thanh toán thành công !")
        clickDesk()
    }).fail(function () {
        alert("Toang")
    })
})


$("#cancel-order").on("click", function () {
    idDrinksArr = new Array()
    quantityArr = new Array()
    drawNewOrderOfDesk()
    $("#order").addClass("d-none");
    $("#list-desk-not-empty").removeClass("d-none");
    $(".add-drinks-to-order").addClass("d-none")

});

function backOrderDesk() {
    drawNewOrderOfDesk()
    $("#order").addClass("d-none");
    $("#list-desk-not-empty").removeClass("d-none");
    $(".add-drinks-to-order").addClass("d-none")
    oldQuantity = 0;
    total_money = 0;

}

function drawNewOrderOfDesk() {
    $("#tbListOrderDetails").html(` 
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên thức uống</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Thành tiền (VND)</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
            <tr style="font-weight: bolder">
                <td colspan="4">Tổng tiền :</td>
                <td id="total_money" >0 VND</td>
                <input type="hidden" id="total_money_order">
            </tr>
            </tfoot>`);

}


clickDesk();

addOrder();