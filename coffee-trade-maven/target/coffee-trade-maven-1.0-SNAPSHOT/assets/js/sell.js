let idDrinksArr = new Array();
let quantityArr = new Array();
let oldQuantity = 0;
let total_money = 0;

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
                $("#done ,#btn-total-money-order").prop("disabled",true);
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

                // $("#new-order").html(`
                // <div className="col-md-6" style="text-align: center">
                //     <button type="button" className="btn btn-outline-primary" id="done" data-id="${desk.id}" >Xong</button>
                // </div>
                // <div className="col-md-6" style="text-align: center">
                //       <button type="button" className="btn btn-outline-danger" id="cancel-order" onclick="backOrderDesk()">Hủy</button>
                // </div>`
                // )

                $("#new-order").removeClass("d-none");
                $("#old-order").addClass("d-none");
                addOrder();
                // clickDesk();
            } else {
                getOrderDetailOfDesk(desk.id)
                drawOrderNotPayment(desk.id)
                $("#old-order").removeClass("d-none");
                $("#new-order").addClass("d-none");
                $("#btn-total-money-order").prop("disabled",false);
                $("#btn-edit-order").prop("disabled",true);

                // $("#old-order").html(`
                //       <div class="col-md-6" style="text-align: center">
                //         <button type="button" class="btn btn-outline-warning" id="btn-edit-order" data-id="${desk.id}">Sửa</button>
                //     </div>
                //     <div class="col-md-6" style="text-align: center">
                //         <button type="button" class="btn btn-outline-info" id="btn-total-money-order" data-id="${desk.id}">Tính tiền
                //         </button>
                //     </div>`)

                // alert("Desk not empty")
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
    $("#done, #btn-total-money-order").prop("disabled",false);
    $("#btn-edit-order").prop("disabled",false);
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
            crateOrderDetail(orderResp.id, idDrinksArr[i])
        }

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
        // headers: {
        //     'Accept': 'application/json',
        //     'Content-Type': 'application/json'
        // },
        url: `/api/order_detail/orderdetailofdeskid/${desk_id}`,
        type: "GET",
    }).done(function (orderdetails) {
        console.log(orderdetails);
        let str = ""
        let sum = 0;
        for (i = 0; i < orderdetails.length; i++) {
            idDrinksArr.push(orderdetails[i].id_drink);
            quantityArr.push(orderdetails[i].quantity)
            sum += orderdetails[i].amount

            str += `
               <tr id='tr_${orderdetails[i].id_drink}'>
                <td>${i + 1}</td>
                <td >${orderdetails[i].name}</td>
                 <td>
                 <input type="number" class="quantity_drinks" id="quantity_${orderdetails[i].id_drink}" name="quantity_${orderdetails[i].id_drink}" style="width: 60px" data-id="${orderdetails[i].id_drink}" value="${orderdetails[i].quantity}">
                 </td>
            <td>${orderdetails[i].price}</td>
            <td id="total_${orderdetails[i].id_drink}">${orderdetails[i].amount}</td>
            </tr>`
        }
        drawOrderNotPayment(desk_id)
        $("#tbListOrderDetails tbody").html(str)
        console.log(sum)
        $("#total_money").html(sum + " VND")
        total_money = sum;


    }).fail(function () {
        alert("Tải thông tin bàn không thành công")
        // $.notify("Tải thông tin bàn không thành công", "error");
    })
}


function drawOrderNotPayment(desk_id) {
    $.ajax({
        type: "GET",
        url: `/api/order/getorderbydeskiddto/${desk_id}`
    }).done(function (orderDto) {
        let str = `<select id="name_desk" name="name_desk">
                       <option data-id="${orderDto.idDresk}" value="${orderDto.idDresk}">${orderDto.nameDesk}</option>
                    </select>
                  `
        $("#select_desk").html(str)
        // $("#select_desk").html(orderDto.nameDesk)
        $("#time-order").html(orderDto.createAtTime);
        $("#day-order").html(orderDto.createAtDay);
    })
}


$("#cancel-order").on("click", function () {
    idDrinksArr = new Array()
    quantityArr = new Array()
    drawNewOrderOfDesk()
    $("#order").addClass("d-none");
    $("#list-desk-not-empty").removeClass("d-none");
    $(".add-drinks-to-order").addClass("d-none")

});

function backOrderDesk() {
    // clickDesk();
    // drawDesk();
    drawNewOrderOfDesk()
    $("#order").addClass("d-none");
    $("#list-desk-not-empty").removeClass("d-none");
    $(".add-drinks-to-order").addClass("d-none")
    oldQuantity = 0;
    total_money = 0;

}

function drawNewOrderOfDesk() {
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
}

clickDesk();