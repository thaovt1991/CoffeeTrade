let idDrinksArr = new Array();
let oldQuantity = 0;
let total_money = 0;


$(".desk").on("click", function () {
    $("#order").removeClass("d-none");
    $(".add-drinks-to-order").removeClass("d-none");
    $("#list-desk-not-empty").addClass("d-none");
    let idDesk = $(this).data('id');
     drawDeskChecked(idDesk);

    $.ajax({
        type: "GET",
        url: `/api/desk/${idDesk}`
    }).done(function (desk) {
        console.log(desk)
        if (desk.empty) {
            $("#name-desk").html(desk.name);
            let dt = new Date();
            let day = dt.getDay() + "/" + dt.getMonth() + "/" + dt.getFullYear()
            let time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
            $("#time-order").html(time);
            $("#day-order").html(day);
            $("#new-order").removeClass("d-none");
            $("#old-order").addClass("d-none");
        } else {
            alert("ddax co nguoi")
            $("#name-desk").html(desk.name);
            let dt = new Date();
            let day = dt.getDay() + "/" + dt.getMonth() + "/" + dt.getFullYear()
            let time = dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
            $("#time-order").html(time);
            $("#day-order").html(day);
            $("#new-order").addClass("d-none");
            $("#old-order").removeClass("d-none");
        }
    })
});


$(".add-drinks-to-order").on("click", function () {
    let idDrinks = $(this).data("id");

    $.ajax({
        type: "GET",
        url: `/api/drinks/${idDrinks}`
    }).done(function (drinks) {
        if (!idDrinksArr.includes(drinks.id)) {
            idDrinksArr.push(drinks.id);
            $("#tbListOrderDetails tbody").append(getsShowOrderDetails(drinks, idDrinksArr.length));
        } else {
            let newQuantity = parseInt($("#quantity_" + idDrinks).val()) + 1;
            let total_money_drinks = drinks.price * newQuantity
            console.log(total_money_drinks)
            $("#quantity_" + idDrinks).val(newQuantity);
            $("#total_" + idDrinks).html(total_money_drinks);
        }
        total_money += drinks.price;
        $("#total_money").html(total_money + " VND");
    })
})

$(".quantity_drinks").on("onchange", function () {
    let idDrinks = $(this).data("id");
    $.ajax({
        type: "GET",
        url: `/api/drinks/${idDrinks}`
    }).done(function (drinks) {
        if (!idDrinksArr.includes(drinks.id)) {
            idDrinksArr.push(drinks.id);
            $("#tbListOrderDetails tbody").append(getsShowOrderDetails(drinks, idDrinksArr.length));
        } else {
            let newQuantity = parseInt($("#quantity_" + idDrinks).val()) + 1;
            let total_money_drinks = drinks.price * newQuantity
            console.log(total_money_drinks)
            $("#quantity_" + idDrinks).val(newQuantity);
            $("#total_" + idDrinks).html(total_money_drinks);
        }
        total_money += drinks.price;
        $("#total_money").html(total_money + " VND");
    })
})


function getsShowOrderDetails(drinks, index) {
    return str = `
               <tr id='tr_${drinks.id}'>
                <td>${index}</td>
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
    let order = {
        desk: desk,
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "/api/order/create",
        type: "POST",
        data: JSON.stringify(order)
    }).done(function (orderResp) {
        console.log(orderResp);
    }).fail(function () {
        $.notify("Tạo order Không thành công")
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
        console.log(order);
        $("#order_id").val(order.id);
    }).fail(function () {
        alert("fail")
        $.notify("order fail", "error")
    })
}

/////// order details
getOrderDetailOfDesk = function (desk_id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "/api/orderdetail/order-detail-of-deskid/" + desk_id,
        type: "GET",
    }).done(function (orderdetails) {
        console.log(orderdetails);
        $("#tbListOrderDetails").empty();
        // let btn_pay = $("#total")
        // btn_pay.empty();
        let total = 0;
        let amount = 0;
        $.each(orderdetails, function (index, orderDetail) {
            amount = (orderDetail.amount * orderDetail.quantity)
            total += unitPrice;
            console.log(orderDetail)
            // getsShowOrderDetails()
            // $("#tbListOrderDetails").append(
            //     `
            //     <tr>
            //         <td>${index + 1}</td>
            //         <td>${orderDetail.d}</td>
            //         <td>${orderDetail.}</td>
            //         <td>${orderDetail.quantity}</td>
            //         <td>${amount}</td>
            //     </tr>
            //     `
            // )
        })
        $("#total_money").html(total + "VND");

    }).fail(function () {
        alert("Tải thông tin bàn không thành công")
        // $.notify("Tải thông tin bàn không thành công", "error");
    })
}


$(
    "#done , #cancel-order "
).on("click", function () {
    resetOrderDesk();
    $("#order").addClass("d-none");
    $("#list-desk-not-empty").removeClass("d-none");
    $(".add-drinks-to-order").addClass("d-none")

});

function resetOrderDesk() {
    drawDesk() ;
    $("#tbListOrderDetails").html(` <thead>
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
                    </tr>
                    </tfoot>`);
    idDrinksArr = new Array();
    oldQuantity = 0;
    total_money = 0;
}
