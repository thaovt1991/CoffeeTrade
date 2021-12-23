function drawDeskEmpty() {
    $.ajax({
        type: "GET",
        url: "/api/desk/getalldeskempty"
    }).done(function (listdesk) {
        console.log(listdesk)
        let str = `<select id="name_desk" name="name_desk">`

        for (let i = 0; i < listdesk.length; i++) {
            str += `<option id="option_${listdesk[i].id}" data-id="${listdesk[i].id}" value="${listdesk[i].id}">${listdesk[i].name}</option>`
        }
        str += `</select>`
        $("#select_desk").html(str);
    })

}

function drawDeskChecked(idDesk) {
    // $("#select_desk").html(idDesk);
    $.ajax({
        type: "GET",
        url: "/api/desk/getalldeskempty"
    }).done(function (listdesk) {
        console.log(listdesk)
        let str = ` <select id="name_desk" name="name_desk">`
        for (let i = 0; i < listdesk.length; i++) {
            if (listdesk[i].id != idDesk) {
                str += `<option id="option_${listdesk[i].id}" data-id="${listdesk[i].id}" value="${listdesk[i].id}">${listdesk[i].name}</option>`
            } else {
                str += `<option id="option_${listdesk[i].id}" data-id="${listdesk[i].id}" value="${listdesk[i].id}" selected>${listdesk[i].name}</option>`
            }
        }
        str += `</select>`
        $("#select_desk").html(str);
    })

}

function drawDeskAll() {
    return $.ajax({
        type: "GET",
        url: "/api/desk/getalldesk"
    }).done(function (listdesk) {
        // console.log(listdesk)
        let str = "";
        for (let i = 0; i < listdesk.length; i++) {
            if (listdesk[i].empty) {
                str += `<div class="col-md-1 " style="float: left">
                         <button class="desk" id="desk_${listdesk[i].id}" style="color: blue" data-id="${listdesk[i].id}">${listdesk[i].name}</button>
                         </div>`
            } else {
                str += `
                <div class="col-md-1 " style="float: left">
                  <button class="desk" id="desk_${listdesk[i].id}" style="color: red" data-id="${listdesk[i].id}">${listdesk[i].name}</button>
                 </div>`
            }
        }
        $(".show-all-desk").html(str);

    })
    .fail(function () {
        alert("draw all desk fail")
    });
}

function drawDeskNotEmpty(){
     $.ajax({
         type: "GET" ,
         url: `/api/desk/getalldesknotempty`
     }).done(function (listdesk){
         let str = ` <h3>The table is occupied</h3>`;
         for (let i = 0; i< listdesk.length ; i++){
             str+=` <div class="col-md-4" style="float: left">
                            <button class="desk" style="color: red" data-id="${listdesk[i].id}"
                                   >${listdesk[i].name}</button>
                        </div>`
        }
         $("#list-desk-not-empty").html(str) ;

    });
}


function updateDesk(desk,ept) {
    let oldDesk = {
        id: desk.id,
        name: desk.name,
        deleted: false ,
        empty: ept ,
    }
    return $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: `/api/desk/update`,
        data: JSON.stringify(oldDesk)
    }).done(function () {
        drawDeskAll();
        // drawDeskEmpty()
        // drawDeskChecked();
        drawDeskNotEmpty()
    }).fail(function () {
       console.log("Fail  updateDesk")
    });

}
