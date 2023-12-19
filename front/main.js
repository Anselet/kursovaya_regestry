import tablesInfo from './tables.json' assert { type: 'json' };

var tableContent;
var currTableProp;
var user; 
var table_select = document.getElementById("table_select");

var keys=[];

var sort = false;

document.addEventListener('DOMContentLoaded', FillPage(), false);
table_select.addEventListener("change", SelectEntity, false);

$('#modal_save').click(function(e){
    save()
})

$('#modal_saveUpdate').click(function(e){
    update()
})

$('#createModal').on('shown.bs.modal', function (e) {
    FillModalWindow();
})

$('#save_form').submit(function (e) {
    
    e.preventDefault();
    onSaveSubmit(e)
})

$('#save_formUpdate').submit(function (e) {

    e.preventDefault();
    onSaveSubmitUpdate(e)
})

$(document).on('click', '.update_button', function () {
    FillModalWindowUpdate(this.id);
});

$(document).on('click','.sort_img',function (){
    let sortName = this.id.replace('sort_img_','');
    console.log(sortName)
    if(!sort){
        tableContent.sort((a, b)=>{
            let isTime = a[sortName].toString().split(' ').length>1
            let aDate =a[sortName].toString().split(' ')[0].split('.')
            let bDate =b[sortName].toString().split(' ')[0].split('.')
            let aTime;
            let bTime;

            if(isTime) {
                aTime = a[sortName].toString().split(' ')[1].split(':')
                bTime = b[sortName].toString().split(' ')[1].split(':')
            }
            //console.log(aDate)
            //console.log(bDate)

            if(+aDate[2]-+bDate[2] === 0){
                if(+aDate[1]-+bDate[1] === 0){
                    if(+aDate[0]-+bDate[0] === 0){
                        if(!isTime)
                            return +aDate[0]-+bDate[0]
                        else {
                            if(aTime[0]-bTime[0]===0){
                                if(aTime[1]-bTime[1]===0){
                                    return aTime[2]-bTime[2]
                                }else
                                    return  aTime[1]-bTime[1]


                            }else
                                return aTime[0]-bTime[0]

                        }
                    }
                    else
                        return +aDate[0]-+bDate[0]
                }
                else
                    return +aDate[1]-+bDate[1]
            }
            else
                return aDate[2]-bDate[2]
        })
        sort=!sort
    }
    else
        tableContent.reverse();

    $("#table_head tr").remove();
    $("#tbody tr").remove();
    FillTableHeader();
    FillTableBody();
})

$('#createModal').on('hidden.bs.modal', function () {
    removeAllChildNodes(document.getElementById("save_form"));

})
$('#createModalUpdate').on('hidden.bs.modal', function () {
    removeAllChildNodes(document.getElementById("save_formUpdate"));

})

$('#search_img').on('click',function(){
    search()

})

$('#search_field').on('keyup',function (e){
    if($('#search_field').val()==="")
        SelectEntity()
    if(e.which === 8){
        SelectEntity().then(r => search())
    }
})

$('#search_field').keypress(function(e) {
    if(e.which === 13) {
        search()
    }
})


function search(){
    let pattern = $('#search_field').val();

    if(pattern === "")
        SelectEntity()
    else{
        let i = 0;
        while (i < tableContent.length){
            if(!JSON.stringify(tableContent[i]).toLowerCase().includes(pattern.toLowerCase()))
                tableContent.splice(i, 1);
            else
                i++;
            }
        $("#table_head tr").remove();
        $("#tbody tr").remove();
        FillTableHeader();
        FillTableBody();
    }
}

async function FillPage(){
    $("#add_button").hide();
    $("#search-div").hide()
    user = JSON.parse(window.localStorage.getItem("user"));
    document.getElementById("welcom_text").innerHTML = user.citizen.name +" "+ user.citizen.patronymic+", добро пожаловать!"

    

    Object.keys(tablesInfo[user.post.id]).forEach(element => {
        const newOption = new Option(element, tablesInfo[user.post.id][element].tableName);
        table_select.options[table_select.options.length]=newOption;        
    });
    
    
}

function WriteTable(){
    document.querySelector('.table_db').innerHTML = `<table class = "table table-light table-hover" </table>`
    
    
}

async function SelectEntity()  {
    sort = false;
    let selectedOption = table_select.options[table_select.selectedIndex];
    
    if(selectedOption.text === "Выберите таблицу для работы"){
        $("#table_head tr").remove();
        $("#tbody tr").remove();
        $("#add_button").hide();
        $("#search-div").hide()
        return;
    }
        
    currTableProp = tablesInfo[user.post.id][selectedOption.text];

    let url = "http://localhost:8080/"+ selectedOption.value +"/findAll";

    await fetch(url)
        .then((res) => {
            return res.json(); 
        })
        .then((data) => {
            tableContent = data;
        });
    console.log(tableContent)
    FillTableHeader();
    FillTableBody();
    currTableProp.rights.toString().includes("C")? $("#add_button").show() :$("#add_button").hide()
    $("#search-div").show()
}

function FillTableHeader() {

   
    $("#table_head tr").remove();
    $("#tbody tr").remove()

    keys=[];
    let table_header = $("#table_head");
    let tr = document.createElement('TR');

    Object.keys(tableContent[0]).forEach(element => {
        var sortIdentifier="";
        const columnContent = tableContent[0][element];


        if(Array.isArray(columnContent) || element === "hibernateLazyInitializer"){
            return;
        }

        if(element === "pk"){
            let pk_object = tableContent[0][element]
            Object.keys(pk_object).forEach((key)=>{
                keys.push(key);
                let td = document.createElement('TD');
                td.innerHTML = key;
                tr.appendChild(td);
                sortIdentifier = key

            })
        }else{
            keys.push(element);
            sortIdentifier = element
            let td = document.createElement('TD');
            td.innerHTML = element;
            if(element.toLowerCase().includes("date") || element.toLowerCase().includes("day")){
                let img = document.createElement('IMG');
                img.setAttribute('src','sort-icon-512x410-1kpy040x.png')
                img.setAttribute('class','sort_img')
                td.setAttribute('style',"display: flex;\n" +
                    "justify-content: space-between;")
                img.setAttribute('id','sort_img_'+sortIdentifier)
                td.appendChild(img)

            }
            tr.appendChild(td);
        }


    
    })

    if(currTableProp.rights.includes("U")){
        let td = document.createElement('TD');
        td.setAttribute("class","optional_header")
        tr.appendChild(td);
    }

    table_header.append(tr);
}

function FillTableBody(){
    let table_body = $("#tbody");

    tableContent.forEach((row,index) =>{
        let tr = document.createElement('TR');


        keys.forEach(key=>{
            let td = document.createElement('TD');

            if("pk" in row && key in row.pk){
                td.innerHTML = row.pk[key].id;
            }
            else{
                if(row[key] != null && typeof row[key] === "object"){
                    var value = row[key].id;

                    if(!value){
                        value =row[key].regNumber
                    }
                    td.innerHTML = value;
                }
                else
                    td.innerHTML = row[key];

            }
            tr.appendChild(td);
        })

        if(currTableProp.rights.includes("U")){


            let td = document.createElement('TD');
            let button = document.createElement("BUTTON")
            button.setAttribute("id","button_"+index)
            button.innerHTML="Изменить";
            button.setAttribute("class","btn btn-outline-secondary update_button")
            button.setAttribute("data-bs-toggle","modal")
            button.setAttribute("data-bs-target","#createModalUpdate")
            td.appendChild(button);
            tr.appendChild(td);
        }


        table_body.append(tr);
    })
}

function onSaveSubmit(event){
    event.preventDefault();
    //alert("bbb")
    let indexed_array={};
    $.map($('#save_form').serializeArray(), function(n, i){
        indexed_array[n['name']] = n['value'];
    });
    modalSave(indexed_array);
    return false;

}

function onSaveSubmitUpdate(event){
    event.preventDefault();
    modalSaveUpdate(getFormData($('#save_formUpdate')));
    return false;

}

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

 async function FillModalWindow() {
    let columnName =[];
    let model_body = $("#modal_body");
    let url = "http://localhost:8080/"+currTableProp.tableName+"/getDto";

    await fetch(url)
        .then ((res)=>{
            return res.json()
        }).then((data)=>{
            console.log(data);
            Object.keys(data).forEach( (key) =>
            columnName.push(key))
            })

    //let form = document.createElement('FORM');
    //form.setAttribute('onSubmit',"onSaveSubmit(event);return false;");
    // $('#save_form').onSubmit = onSaveSubmit;
    //form.setAttribute('id',"save_form");
    let form = $('#save_form')
    columnName.forEach(key =>{
        console.log(key);
        
        let div = document.createElement('DIV');
        div.classList.add("field_div");
        
        let label = document.createElement('LABEL');
        let input = document.createElement('INPUT');
        input.setAttribute('type',"text");
        input.setAttribute('name', key);
        input.setAttribute('id',key);
        label.setAttribute('for',key);
        label.innerHTML = [key];
        div.append(label);
        div.append(input);
        form.append(div);
        
    })


}

function save(){
    $('#save_form').submit();
}



 async function modalSave(body){
    //let modal_save_button = $('#modal_save');
    let url = "http://localhost:8080/"+currTableProp.tableName+"/create";

    let response = fetch(url, {
        method: 'POST',
        headers: {
                  'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(body)
    }).then((response) => {
        if (response.status === 200){
        alert("Запись добавлена")
        setTimeout(() => $("#createModal [data-bs-dismiss=modal]").trigger({ type: "click" }), 100);
        SelectEntity();


        }

    })

 }

async function modalSaveUpdate(body){
    let url = "http://localhost:8080/"+currTableProp.tableName+"/update";
    let response = fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
    }).then((response) => {
        if (response.status === 200){
            alert("Запись обновлена")
            setTimeout(() => $("#createModalUpdate [data-bs-dismiss=modal]").trigger({ type: "click" }), 100);
            SelectEntity();


        }

    })

}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

async function FillModalWindowUpdate(buttonId) {
    let entityIndex = buttonId.replace("button_","")
    let entity = tableContent[entityIndex];

    let form = $('#save_formUpdate')

    keys.forEach(key =>{
        let isDisable=false;
        let value;
        if("pk" in entity && key in entity.pk){
            value = entity.pk[key].id;
            isDisable=true;
        }
        else{
            if(entity[key] != null && typeof entity[key] === "object"){
                let v = entity[key].id;

                if(!v){
                    v =entity[key].regNumber
                }
                value = v;
            }
            else
                value = entity[key];
        }
        if( key === "id" || key === "regNumber")
            isDisable = true;

        let div = document.createElement('DIV');
        div.classList.add("field_div");

        let label = document.createElement('LABEL');
        let input = document.createElement('INPUT');
        input.setAttribute('type',"text");
        input.setAttribute('name', key);
        input.setAttribute('id',key);
        label.setAttribute('for',key);
        label.innerHTML = [key];
        input.value = value;
        if(isDisable)
            input.setAttribute('readonly', true);
        div.append(label);
        div.append(input);
        form.append(div);

    })


}

function update(){
    $('#save_formUpdate').submit();
}
