function drawDesk(){
    $.ajax({
        type: "GET" ,
        url : "/api/desk/getalldeskempty"
    }).done(function (listdesk){
        console.log(listdesk)
        let str =` <select id="name_desk" name="name_desk">`
         for(let i=0 ; i < listdesk.length; i++){
           str +=  `<option id="option_${listdesk[i].id}" data-id="${listdesk[i].id}" value="${listdesk[i].id}">${listdesk[i].name}</option>`
         }
         str+= `</select>`
         $("#select_desk").html(str) ;
    })
}

function drawDeskChecked(idDesk){
    $.ajax({
        type: "GET" ,
        url : "/api/desk/getalldeskempty"
    }).done(function (listdesk){
        console.log(listdesk)
        let str =` <select id="name_desk" name="name_desk">`
        for(let i=0 ; i < listdesk.length; i++) {
            if (listdesk[i].id != idDesk) {
                str += `<option id="option_${listdesk[i].id}" data-id="${listdesk[i].id}" value="${listdesk[i].id}">${listdesk[i].name}</option>`
            }else {
                str += `<option id="option_${listdesk[i].id}" data-id="${listdesk[i].id}" value="${listdesk[i].id}" selected>${listdesk[i].name}</option>`
            }
        }
        str+= `</select>`
        $("#select_desk").html(str) ;
    })
}

